package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.service.UserService;
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
     *
     * @param queryDto
     * @return
     */
    @PostMapping(value = "/signin")
    Result sign_in(@RequestBody QueryDto queryDto) {
        return userService.signIn(queryDto);
    }

    /**
     * 注册
     *
     * @param queryDto
     * @return
     */
    @PostMapping(value = "/signup")
    Result sing_up(@RequestBody QueryDto queryDto) {
        return userService.signUp(queryDto);
    }

    /**
     * 修改个人信息
     *
     * @param userDto
     * @return
     */
    @PutMapping(value = "/data")
    Result updateUserData(@RequestBody UserDto userDto) {
        return userService.updateUserData(userDto);
    }

    /**
     * 修改密码
     *
     * @param userDto
     * @return
     */
    @PutMapping(value = "/password")
    Result updateUserPassword(@RequestBody UserDto userDto) {
        return userService.updateUserPassword(userDto);
    }

    /**
     * 通过id更改头像
     *
     * @param userDto
     * @return
     */
    @PutMapping(value = "/avatar")
    Result updateUserAvatar(@RequestBody UserDto userDto) {
        return userService.updateUserAvatar(userDto);
    }

    /**
     * 通过id查找用户信息
     *
     * @param userDto
     * @return
     */
    @PostMapping(value = "/id")
    Result selectUserById(@RequestBody UserDto userDto) {
        return userService.selectUserById(userDto);
    }

    /**
     * 判断用户是否处于登录状态
     *
     * @param userDto
     * @return
     */
    @PostMapping(value = "/login")
    Result IsLogin(@RequestBody UserDto userDto) {
        return userService.isLogin(userDto);
    }

    /**
     * 统计用户的好友，日志，相册，照片
     *
     * @param queryDto
     * @return Result
     */
    @PostMapping(value = "/sum")
    Result selectSum(@RequestBody QueryDto queryDto) {
        return userService.selectAllSum(queryDto);
    }


    @PutMapping(value = "/security")
    Result updatePassword(@RequestBody UserDto userDto){
        return userService.updatePassword(userDto);
    }
}
