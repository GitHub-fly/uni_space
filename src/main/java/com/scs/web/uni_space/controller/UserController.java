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

    //登录
    @PostMapping(value = "/sign_in")
    Result sign_in(@RequestBody UserDto userDto) {
        return userService.signIn(userDto);
    }

    //注册
    @PostMapping(value = "/sign_up")
    Result sing_up(@RequestBody UserDto userDto) {
        return userService.signUp(userDto);
    }

    //修改个人信息
    @PutMapping(value = "/updateUserData")
    int updateUserData(@RequestBody User user) {
        return userService.updateUserData(user);
    }

}