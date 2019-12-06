package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.service.UserService;
import com.scs.web.uni_space.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 小黑
 * @ClassNameUserController
 * @Description 用户控制层
 * @Date 2019/12/2
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * @Description 登录
     * @param userDto
     * @return
     */
    @PostMapping(value = "/sign_in")
    Result sign_in(@RequestBody UserDto userDto) {
        return userService.signIn(userDto);
    }

    /**
     * @Description 注册
     * @param userDto
     * @return
     */
    @PostMapping(value = "/sign_up")
    Result sing_up(@RequestBody UserDto userDto) {
        return userService.signUp(userDto);
    }

    /**
     * @Description 修改个人信息
     * @param user
     * @return
     */

    @PutMapping(value = "/UserData")
    Result updateUserData(@RequestBody User user) {
        return userService.updateUserData(user);
    }

    @PutMapping(value = "/UserPassword")
    Result updateUserPassword(@RequestBody UserDto userDto){

        return  userService.updateUserPassword(userDto);
    }
}