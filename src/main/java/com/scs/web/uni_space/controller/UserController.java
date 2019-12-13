package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.service.UserService;
import com.scs.web.uni_space.common.Result;
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
     * 登录
     * @param userDto
     * @return
     */
    @PostMapping(value = "/sign_in")
    Result sign_in(@RequestBody UserDto userDto) {
        return userService.signIn(userDto);
    }

    /**
     * 注册
     * @param userDto
     * @return
     */
    @PostMapping(value = "/sign_up")
    Result sing_up(@RequestBody UserDto userDto) {
        return userService.signUp(userDto);
    }

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    @PutMapping(value = "/userData")
    Result updateUserData(@RequestBody User user) {
        return userService.updateUserData(user);
    }

    /**
     * 修改密码
     * @param userDto
     * @return
     */
    @PutMapping(value = "/userPassword")
    Result updateUserPassword(@RequestBody UserDto userDto) {
        return userService.updateUserPassword(userDto);
    }

    /**
     * 通过id更改头像
     *
     * @param userDto
     * @return
     */
    @PutMapping(value = "/userAvatar")
    Result updateUserAvatar(@RequestBody UserDto userDto) {
        return userService.updateUserAvatar(userDto);
    }

    /**
     * 通过id查找用户信息
     * @param id
     * @return
     */
    @PostMapping(value = "/userid")
    Result selectUserById(@RequestParam Long id) {
        return userService.selectUserById((long) id);
    }


    /**
     * 统计用户的好友，日志，相册，照片
     * @param userDto
     * @return Result
     */
    @PostMapping(value = "/sum")
    Result selectSum(@RequestBody UserDto userDto){
        return userService.selectAllSum(userDto);
    }
}