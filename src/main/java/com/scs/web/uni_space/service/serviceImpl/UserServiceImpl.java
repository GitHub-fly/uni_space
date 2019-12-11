package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.domain.vo.UserVo;
import com.scs.web.uni_space.mapper.CommonMapper;
import com.scs.web.uni_space.mapper.UserMapper;
import com.scs.web.uni_space.service.RedisService;
import com.scs.web.uni_space.service.UserService;
import com.scs.web.uni_space.util.OSSClientUtil;
import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.sql.Date;
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
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private CommonMapper commonMapper;
    @Resource
    private RedisService redisServiceImpl;

    @Override
    public Result signIn(UserDto userDto) {
        User user = null;
        String verifyCode = redisServiceImpl.getValue(userDto.getName(), String.class);

        try {
            if (userMapper.selectUserByMobile(userDto.getName()) != null) {
                user = userMapper.selectUserByMobile(userDto.getName());
            } else if (userMapper.selectUserByAccount(userDto.getName()) != null) {
                user = userMapper.selectUserByAccount(userDto.getName());
            } else if (userMapper.selectUserByEmail(userDto.getName()) != null) {
                user = userMapper.selectUserByEmail(userDto.getName());
            } else {
                return Result.failure(ResultCode.USER_MOBILE_NOT_EXIST);
            }
        } catch (SQLException e) {
            logger.info("根据账号查找失败");
        }
        while (userDto.getPassword() != null) {
            if (user.getPassword().equals(DigestUtils.md5Hex(userDto.getPassword()))) {
                return Result.success(user);
            } else {
                return Result.failure(ResultCode.USER_PASSWORD_ERROR);
            }
        }

        while (userDto.getVerifyCode() != null) {
            if (verifyCode == null) {
                return Result.failure(ResultCode.USER_VERIFY_CODE_null);
            } else {
                if (verifyCode.equalsIgnoreCase(userDto.getVerifyCode())) {
                    return Result.success(user);
                } else {
                    return Result.failure(ResultCode.USER_VERIFY_CODE_ERROR);
                }
            }
        }
        return Result.failure(ResultCode.USER_ACCOUNT_NOT_EXIST);


    }

    @Override
    public Result signUp(UserDto userDto) {
        User user = null;
        try {
            user = userMapper.selectUserByMobile(userDto.getName());
        } catch (SQLException e) {

            logger.info("查找错误 ");

            logger.error("查找指定手机号码出错");

        }
        if (user != null) {
            return Result.failure(ResultCode.USER_HAS_EXISTED);
        } else {
            String verifyCode = redisServiceImpl.getValue(userDto.getName(), String.class);
            if (verifyCode == null) {
                return Result.failure(ResultCode.USER_VERIFY_CODE_null);
            } else {
                if (userDto.getVerifyCode().equals(verifyCode)) {
                    String avatar = "https://upload.jianshu.io/users/upload_avatars/19576582/c2ccea8c-aac7-402f-8537-b63550d9301c.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/180/h/180";
                    Timestamp createTime = Timestamp.valueOf(LocalDateTime.now());
                    Date birthday = Date.valueOf(LocalDate.now());
                    try {
                        commonMapper.returnid("t_user");
                        int result = userMapper.insertUser(userDto.getName(), DigestUtils.md5Hex(userDto.getPassword()), avatar, createTime, birthday);
                        return Result.success(ResultCode.SUCCESS);
                    } catch (SQLException e) {
                        return Result.failure(ResultCode.USER_ADD_FAILURE);
                    }
                } else {
                    return Result.failure(ResultCode.USER_VERIFY_CODE_ERROR);

                }
            }
        }

    }

    @Override
    public Result updateUserData(User user) {


        try {
            userMapper.updateUserData(user);

        } catch (SQLException e) {
            logger.info("更新失败");
        }
        return Result.success(user);
    }

    @Override
    public Result updateUserPassword(UserDto userDto) {
        User user = null;
        String verifyCode = redisServiceImpl.getValue(userDto.getName(), String.class);
        try {
            user = userMapper.selectUserByMobile(userDto.getName());
            if (user != null) {
                if (verifyCode.equals(userDto.getVerifyCode())) {
                    if (verifyCode == null) {
                        return Result.failure(ResultCode.USER_VERIFY_CODE_null);
                    } else {
                        if (user.getPassword().equals(DigestUtils.md5Hex(userDto.getPassword()))) {
                            return Result.failure(ResultCode.USER_PASSWORD_REPEAT);
                        } else {
                            userMapper.updateUserPassword(userDto.getName(), DigestUtils.md5Hex(userDto.getPassword()));
                            return Result.success(user);
                        }
                    }
                } else {
                    return Result.failure(ResultCode.USER_VERIFY_CODE_ERROR);
                }
            } else {
                return Result.failure(ResultCode.USER_NOT_EXIST);
            }
        } catch (SQLException e) {
            logger.info("失败");
        }
        return Result.success("更新成功");
    }


    @Override
    public Result updateUserAvatar(UserDto userDto) {
        if (userDto.getAvatar() != null) {
            try {
                return Result.success(userMapper.updateUserAvatar(userDto.getAvatar(), (long) userDto.getId()));
            } catch (SQLException e) {
                return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
            }
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result selectUserById(Long id) {
        UserVo userVo = null;
        try {
            userVo = userMapper.selectUserById((long) id);


        } catch (SQLException e) {
            logger.info("查找失败");
        }
        return Result.success(userVo);
    }

    @Resource
    private OSSClientUtil ossClient = new OSSClientUtil();

    @Override
    public String updatePcAvatar(MultipartFile file) throws Exception {
        if (file == null || file.getSize() <= 0) {
            throw new Exception("头像不能为空");
        }
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        System.out.println(imgUrl);
        //userDao.updateHead(userId, imgUrl);//只是本地上传使用的
        return imgUrl;
    }
}