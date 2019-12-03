package com.scs.web.uni_space.service;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.util.Result;

/**
 * @author 小黑
 * @ClassNameUserService
 * @Description TODO
 * @Date 2019/12/2
 * @Version 1.0
 */
public interface UserService {
    Result signIn(UserDto userDto);
    Result signUp(UserDto userDto);
}
