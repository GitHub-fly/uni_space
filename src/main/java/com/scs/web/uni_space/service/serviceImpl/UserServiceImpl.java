package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.mapper.UserMapper;
import com.scs.web.uni_space.service.UserService;
import com.scs.web.uni_space.util.Result;
import com.scs.web.uni_space.util.ResultCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 小黑
 * @ClassNameUserServiceImpl
 * @Description TODO
 * @Date 2019/12/2
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Result signIn(UserDto userDto) {
        User user = null;
        if (userDto.getMobile() != null) {
            user = userMapper.selectUserByMobile(userDto.getMobile());
            if (user == null) {
                return Result.failure(ResultCode.USER_MOBILE_NOT_EXIST);
            }

        } else if (userDto.getAccount() != null) {
            user = userMapper.selectUserByAccount(userDto.getAccount());
            if (user == null) {
                return Result.failure(ResultCode.USER_ACCOUNT_NOT_EXIST);
            }
        } else if (userDto.getEmail() != null) {
            user = userMapper.selectUserByEmail(userDto.getEmail());
            if (user == null) {
                return Result.failure(ResultCode.USER_EMAIL_NOT_EXIST);
            }
        }
        if (user != null) {
            if (user.getPassword().equals(userDto.getPassword())) {
                return Result.success(user);
            } else {
                return Result.failure(ResultCode.USER_PASSWORD_ERROR);
            }

        }
        return Result.success(ResultCode.SUCCESS);
    }

    @Override
    public Result signUp(UserDto userDto) {
        User user = null;
        user = userMapper.selectUserByMobile(userDto.getMobile());
        if (user != null) {
            return Result.failure(ResultCode.USER_HAS_EXISTED);
        } else {
            int result = userMapper.insertUser(userDto.getMobile(), userDto.getPassword());
            if (result != 0) {
                return Result.success(ResultCode.SUCCESS);
            }

        }
        return Result.success(ResultCode.SUCCESS);
    }

    @Override
    public Result updateUserAvatar(User user) {
        int i = 0;
        i = userMapper.updateUserAvatar(user.getAvatar(), user.getId());
        if (i != 0){
            return Result.failure(ResultCode.SUCCESS);
        }else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }


}
