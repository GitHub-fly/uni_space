package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.mapper.UserMapper;
import com.scs.web.uni_space.service.RedisService;
import com.scs.web.uni_space.service.UserService;
import com.scs.web.uni_space.util.Result;
import com.scs.web.uni_space.util.ResultCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

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
            if (verifyCode.equals(userDto.getVerifyCode())) {
                return Result.success(user);
            } else {
                return Result.failure(ResultCode.USER_VERIFY_CODE_ERROR);
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
<<<<<<< Updated upstream
            logger.info("查找错误 ");
=======
            logger.error("查找指定手机号码出错");
>>>>>>> Stashed changes
        }
        if (user != null) {
            return Result.failure(ResultCode.USER_HAS_EXISTED);
        } else {
            String verifyCode = redisServiceImpl.getValue(userDto.getName(), String.class);
            if (verifyCode.equals(userDto.getVerifyCode())) {
                int result = userMapper.insertUser(userDto.getName(), DigestUtils.md5Hex(userDto.getPassword()));
                if (result != 0) {
                    return Result.success(ResultCode.SUCCESS);
                } else {
                    return Result.failure(ResultCode.USER_ADD_FALURE);
                }
            }
        }
        return Result.success(ResultCode.SUCCESS);
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
        User user =null;
        String verifyCode = redisServiceImpl.getValue(userDto.getName(), String.class);
        try {
            user =userMapper.selectUserByMobile(userDto.getName());
            if (user!=null){
    if (verifyCode.equals(userDto.getVerifyCode())) {
        if (user.getPassword().equals(userDto.getPassword())) {
            return Result.failure(ResultCode.USER_PASSWORD_REPIT);

        } else {

            userMapper.updateUserPassword(userDto.getName());

        }
    }else {
        return  Result.failure(ResultCode.USER_VERIFY_CODE_ERROR);
    }
            }else {


                return Result.failure(ResultCode.USER_NOT_EXIST);
            }
        } catch (SQLException e) {
            logger.info("失败");
        }
          return Result.success("更新成功");

    }


}