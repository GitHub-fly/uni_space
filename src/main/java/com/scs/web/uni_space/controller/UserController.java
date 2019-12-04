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
 * @Description TODO
 * @Date 2019/12/2
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Resource
    private UserService userService;


    @PostMapping(value = "/sign_in")
    Result sign_in(@RequestBody UserDto userDto) {
        /**
         * 将前端的JSON对象 转换为 后端的userDto
         */
        Result result = userService.signIn(userDto);

        return result;

    }

    @PostMapping(value = "/sign_up")
    Result sing_up(@RequestBody UserDto userDto) {

        return userService.signUp(userDto);
    }

    @PutMapping(value = "/updateUserData")
    int updateUserData(@RequestBody User user) {
        int result = userService.updateUserData(user);
        return result;
    }

}
