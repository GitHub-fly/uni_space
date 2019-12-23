package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.dto.SkinDto;
import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "UserController", tags = "用户模块接口")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 登录
     *
     * @param queryDto
     * @return
     */
    @ApiOperation(value = "登录", notes = "短信验证码登录传equalsString为手机号，keywords为短信验证码，密码登录password为密码，未注册用户登录自动注册")
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
    @ApiOperation(value = "注册", notes = "短信密码注册传equalsString为手机号，keywords为短信验证码")
    @PostMapping(value = "/signup")
    Result sing_up(@RequestBody QueryDto queryDto) {
        return userService.signUp(queryDto);
    }

    /**
     * 换皮肤
     *
     * @param skinDto
     * @return
     */
    @ApiOperation(value = "修改皮肤", notes = "id为用户id ，skin_id 为皮肤id")
    @PostMapping(value = "/skin")
    Result sing_up(@RequestBody SkinDto skinDto) {
        return userService.updateUserSkinID(skinDto);
    }


    /**
     * 修改个人信息
     *
     * @param userDto
     * @return
     */
    @ApiOperation(value = "修改个人信息", notes = "id必须要传，其余可以传nickname，address，gender，introduction，constellation，birthday,email,acount ")
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
    @ApiOperation(value = "修改密码", notes = "传equalsString为手机号，keywords为短信验证码，以及password")
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
    @ApiOperation(value = "通过id更改头像", notes = "传id以及avatar")
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
    @ApiOperation(value = "通过id查找用户信息", notes = "传id")
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
    @ApiOperation(value = "判断用户是否处于登录状态", notes = "传用户id")
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
    @ApiOperation(value = "统计用户的好友，日志，相册，照片", notes = "传用户id")
    @PostMapping(value = "/sum")
    Result selectSum(@RequestBody QueryDto queryDto) {
        return userService.selectAllSum(queryDto);
    }

    /**
     * 直接更改密码
     *
     * @param userDto
     * @return
     */
    @ApiOperation(value = "直接更改密码", notes = "传用户id,password")
    @PutMapping(value = "/security")
    Result updatePassword(@RequestBody UserDto userDto) {
        return userService.updatePassword(userDto);
    }
}
