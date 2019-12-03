package com.scs.web.uni_space.service;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.util.Result;

/**
 * @author wl
 * @ClassNameUserService
 * @Description TODO
 * @Date 2019/12/2
 * @Version 1.0
 */
public interface UserService {
    /**
     * @Description 登录方法
     * @param userDto
     * @return result
     */
    Result signIn(UserDto userDto) ;

    /**
     * @Description 注册方法
     * @param userDto
     * @return result
     */
    Result signUp(UserDto userDto) ;

    /**
     * @Description 更新用户资料
     * @param user
     * @return int
     */
    int updateUserData(User user)  ;
}
