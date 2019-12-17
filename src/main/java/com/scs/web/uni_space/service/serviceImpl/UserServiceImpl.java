package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.domain.vo.UserVo;
import com.scs.web.uni_space.mapper.CommonMapper;
import com.scs.web.uni_space.mapper.UserMapper;
import com.scs.web.uni_space.service.RedisService;
import com.scs.web.uni_space.service.UserService;
import com.scs.web.uni_space.util.OSSClientUtil;
import com.scs.web.uni_space.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 小黑
 * @ClassNameUserServiceImpl
 * @Description 用户服务类
 * @Date 2019/12/2
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private CommonMapper commonMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private OSSClientUtil ossClient = new OSSClientUtil();

    @Override
    public Result signIn(QueryDto queryDto) {
        User user;
        UserVo userVo;
        //取得短信验证码
        String verifyCode = redisService.getValue(queryDto.getEqualsString(), String.class);
        try {
            userVo = userMapper.selectUserBy(queryDto);
            //判断数据库中是否有该用户
            if (userVo != null) {
                //判断用户使用密码登录
                if (queryDto.getPassword() != null) {
                    if (userVo.getPassword().equals(DigestUtils.md5Hex(queryDto.getPassword()))) {
                        String token = DigestUtils.sha3_256Hex(userVo.getCode());
                        //token存入redis，时效24小时，客户端拿到token，会变为登录状态
                        redisService.set(userVo.getCode(), token, 60 * 24L);
                        return Result.success(userVo);
                    } else {
                        return Result.failure(ResultCode.USER_PASSWORD_ERROR);
                    }
                }

                //判断是否已发送验证码
                if (verifyCode == null) {
                    return Result.failure(ResultCode.USER_VERIFY_CODE_NULL);
                } else {
                    //判断验证码是否正确
                    if (queryDto.getKeyWords().equals(verifyCode)) {
                        return Result.success(userVo);
                    } else {
                        return Result.failure(ResultCode.USER_VERIFY_CODE_ERROR);
                    }
                }
            } else {
                if (verifyCode == null) {
                    return Result.failure(ResultCode.USER_NOT_EXIST);
                } else {
                    //判断短信验证码为空或者是否正确
                    if (queryDto.getKeyWords().equals(verifyCode)) {
                        //直接注册
                        user = User.builder()
                                .code(StringUtil.getRandomCode())
                                .mobile(queryDto.getEqualsString())
                                .password("96e79218965eb72c92a549dd5a330112")
                                .avatar("https://niit-soft.oss-cn-hangzhou.aliyuncs.com/soft1821/2290a88b-137e-46ed-bea2-7ed2c12b18c5.jpeg")
                                .createTime(Timestamp.valueOf(LocalDateTime.now()))
                                .birthday(LocalDate.now()).build();
                        try {
                            //插入数据库中
                            commonMapper.returnId("t_user");
                            userMapper.insertUser(user);
                            return Result.success(user);
                        } catch (SQLException e) {
                            log.error("插入数据库失败");
                            return Result.failure(ResultCode.USER_ADD_FAILURE);
                        }
                    } else {
                        return Result.failure(ResultCode.USER_VERIFY_CODE_ERROR);
                    }
                }
            }
        } catch (SQLException e) {
            log.error("根据账号查找失败");
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
    }

    @Override
    public Result signUp(QueryDto queryDto) {
        User user;
        UserVo userVo;
        try {
            //查找数据库中是否有该用户
            userVo = userMapper.selectUserBy(queryDto);
        } catch (SQLException e) {
            log.error("查找指定手机号码出错");
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
        if (userVo != null) {
            return Result.failure(ResultCode.USER_HAS_EXISTED);
        } else {
            //取得到短信验证码
            String verifyCode = redisService.getValue(queryDto.getEqualsString(), String.class);
            if (verifyCode == null) {
                return Result.failure(ResultCode.USER_VERIFY_CODE_NULL);
            } else {
                //短信验证码已发送情况下，取出验证码比对用户输入
                if (queryDto.getKeyWords().equals(verifyCode)) {
                    user = User.builder()
                            .code(StringUtil.getRandomCode())
                            .mobile(queryDto.getEqualsString())
                            .password("96e79218965eb72c92a549dd5a330112")
                            .avatar("https://niit-soft.oss-cn-hangzhou.aliyuncs.com/soft1821/2290a88b-137e-46ed-bea2-7ed2c12b18c5.jpeg")
                            .createTime(Timestamp.valueOf(LocalDateTime.now()))
                            .birthday(LocalDate.now()).build();
                    try {
                        //比对成功后插入数据库中
                        commonMapper.returnId("t_user");
                        userMapper.insertUser(user);
                        return Result.success();
                    } catch (SQLException e) {
                        log.error("插入数据库失败");
                        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
                    }
                } else {
                    return Result.failure(ResultCode.USER_VERIFY_CODE_ERROR);
                }
            }
        }
    }

    @Override

    public Result updateUserData(UserDto userDto) {
        try {
            userMapper.updateUserData(userDto);
        } catch (SQLException e) {
            log.error("更新失败");
        }
        return Result.success(userDto);
    }

    @Override
    public Result updateUserPassword(UserDto userDto) {
        User user = null;
        UserVo userVo;
        QueryDto queryDto = QueryDto.builder().equalsString(userDto.getMobile()).build();
        //获得短信验证码
        String verifyCode = redisService.getValue(userDto.getMobile(), String.class);
        try {
            //判断数据库中是否有该用户
            userVo = userMapper.selectUserBy(queryDto);
            if (userVo != null) {
                if (verifyCode == null) {
                    return Result.failure(ResultCode.USER_VERIFY_CODE_NULL);
                } else {
                    if (verifyCode.equals(userDto.getVerifyCode())) {
                        //验证码正确
                        if (user.getPassword().equals(DigestUtils.md5Hex(userDto.getPassword()))) {
                            //更改后的密码不能和原来相同
                            return Result.failure(ResultCode.USER_PASSWORD_REPEAT);
                        } else {
                            userMapper.updateUserPassword(userDto);
                            return Result.success();
                        }
                    } else {
                        return Result.failure(ResultCode.USER_VERIFY_CODE_ERROR);
                    }
                }
            } else {
                return Result.failure(ResultCode.USER_NOT_EXIST);
            }
        } catch (SQLException e) {
            log.info("失败");
        }
        return Result.success("更新成功");
    }

    @Override
    public Result updateUserAvatar(UserDto userDto) {
        if (userDto.getAvatar() != null) {
            try {
                userMapper.updateUserAvatar(userDto);
                return Result.success();
            } catch (SQLException e) {
                log.error("数据库修改错误");
                return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
            }
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result selectUserById(UserDto userDto) {
        UserVo userVo = null;
        try {
            userVo = userMapper.selectUserById(userDto.getId());
        } catch (SQLException e) {
            log.info("查找失败");
        }
        return Result.success(userVo);
    }

    @Override
    public Result isLogin(UserDto userDto) {
        try {
            UserVo userVo = userMapper.selectUserById(userDto.getId());
            String code = redisService.getValue(userVo.getCode(), String.class);
            if (code.equals(DigestUtils.sha3_256Hex(userVo.getCode()))
            ) {
                return Result.success();
            }
        } catch (SQLException e) {
            log.error("查询用户错误");
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
        return Result.failure(ResultCode.USER_LOGIN_FAILURE);
    }

    @Override
    public Result selectAllSum(QueryDto queryDto) {
        if (queryDto.getId() != null) {
            try {
                UserVo userVo = userMapper.selectSum(queryDto.getId());
                return Result.success(userVo);
            } catch (SQLException e) {
                log.error("数据统计异常");
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }
}