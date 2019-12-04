package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.mapper.UserMapper;
import com.scs.web.uni_space.service.UserService;
import com.scs.web.uni_space.util.Result;
import com.scs.web.uni_space.util.ResultCode;
import org.apache.commons.codec.digest.DigestUtils;
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
    private RedisServiceImpl redisServiceImpl;

    public Result signIn(UserDto userDto) {
        User user = null;
        try {
            if (userMapper.selectUserByMobile(userDto.getName()) != null) {
                user = userMapper.selectUserByMobile(userDto.getName());
            } else if (userMapper.selectUserByAccount(userDto.getName()) != null) {
                user = userMapper.selectUserByAccount(userDto.getName());
            } else if (userMapper.selectUserByEmail(userDto.getName()) != null) {
                user = userMapper.selectUserByEmail(userDto.getName());
            }
            if (user.getPassword().equals(userDto.getPassword())) {
                return Result.success(user);
            } else {
                return Result.failure(ResultCode.USER_PASSWORD_ERROR);
            }
        } catch (SQLException e) {
            logger.info("登陆失败");
        }
        return Result.failure(ResultCode.USER_ACCOUNT_NOT_EXIST);
    }

    @Override
    public Result signUp(UserDto userDto) {
        User user = null;
        try {
            user = userMapper.selectUserByMobile(userDto.getName());
        } catch (SQLException e) {
            return Result.success(ResultCode.SUCCESS);
        }
        if (user != null) {
            return Result.failure(ResultCode.USER_HAS_EXISTED);
        } else {
            String verifyCode = redisServiceImpl.getValue(userDto.getName(), String.class);
            if (verifyCode.equals(userDto.getVerifyCode())) {
                int result = userMapper.insertUser(userDto.getName(), DigestUtils.md5Hex(userDto.getPassword()));
                if (result != 0) {
                    return Result.success(ResultCode.SUCCESS);
                }
            } else {
                logger.error("验证码错误");
            }
        }
        return Result.success(ResultCode.SUCCESS);
    }

    @Override
    public int updateUserData(User user) {

        int result = 0;
        try {
            result = userMapper.updateUserData(user);
        } catch (SQLException e) {
            logger.info("更新失败");
        }
        return result;
    }


}
