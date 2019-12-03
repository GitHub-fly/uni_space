package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.service.UserService;
import com.scs.web.uni_space.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小黑
 * @ClassNameUserController
 * @Description TODO
 * @Date 2019/12/2
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Resource
    private UserService userService;


    @PostMapping(value = "/sign-in")
    Result sign_in(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        return userService.signIn(userDto);

    }

    @PostMapping(value = "/sign-up")
    Result sing_up(@RequestBody UserDto userDto) {
        return userService.signUp(userDto);
    }

}
