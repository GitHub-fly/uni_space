package com.scs.web.uni_space.service;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.dto.SkinDto;
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
     * @param queryDto
     * @return result
     */
    Result signIn(QueryDto queryDto);

    /**
     * 注册方法
     *
     * @param queryDto
     * @return result
     */
    Result signUp(QueryDto queryDto);

    /**
     * 通过id查找账户
     *
     * @param userDto
     * @return
     */
    Result selectUserById(UserDto userDto);

    /**
     * 更换皮肤
     * @param skinDto
     * @return
     */

    Result updateUserSkinID(SkinDto skinDto);
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

    /**
     * 显示个人信息和统计好友，日志，相册，相片总数
     * @param queryDto
     * @return Result
     */
    Result selectAllSum(QueryDto queryDto);

    /**
     * 判断用户是否处于登录状态
     * @param userDto
     * @return
     */
    Result isLogin(UserDto userDto);


    /**
     * 根据用户id修改密码
     *
     * @param userDto
     * @return Result
     */
    Result updatePassword(UserDto userDto);
}