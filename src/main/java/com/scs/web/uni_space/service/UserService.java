package com.scs.web.uni_space.service;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.SignDto;
import com.scs.web.uni_space.domain.dto.UserDto;


/**
 * @author wl
 * @ClassNameUserService
 * @Description 用户服务类
 * @Date 2019/12/2
 * @Version 1.0
 */
public interface UserService {

    /**
     * 登录方法
     *
     * @param signDto
     * @return result
     */
    Result signIn(SignDto signDto);

    /**
     * 注册方法
     *
     * @param signDto
     * @return result
     */
    Result signUp(SignDto signDto);

    /**
     * 通过id查找账户
     *
     * @param userDto
     * @return
     */
    Result selectUserById(UserDto userDto);

    /**
     * 更新用户资料
     *
     * @param userDto
     * @return int
     * @Description
     */
    Result updateUserData(UserDto userDto);

    /**
     * 更新用户密码
     *
     * @param userDto
     * @return
     */
    Result updateUserPassword(UserDto userDto);

    /**
     * 根据id修改用户头像
     *
     * @param userDto
     * @return
     */
    Result updateUserAvatar(UserDto userDto);


//    /**
//     * 更改pc端头像
//     *
//     * @param file
//     * @return
//     * @throws Exception
//     */
//    String updatePcAvatar(MultipartFile file) throws Exception;

    /**
     * 检查用户是否登录
     *
     * @return
     * @throws Exception
     */
    Result IsLogin(UserDto userDto);
}