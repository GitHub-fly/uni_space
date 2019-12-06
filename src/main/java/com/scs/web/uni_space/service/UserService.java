package com.scs.web.uni_space.service;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.util.Result;

import java.sql.SQLException;

/**
 * @author wl
 * @ClassNameUserService
 * @Description TODO
 * @Date 2019/12/2
 * @Version 1.0
 */
public interface UserService {
    /**
     * @param userDto
     * @return result
     * @Description 登录方法
     */
    Result signIn(UserDto userDto) ;

    /**
     * @param userDto
     * @return result
     * @Description 注册方法
     */
    Result signUp(UserDto userDto);

    /**
     * @param user
     * @return int
     * @Description 更新用户资料
     */
    Result updateUserData(User user);
}