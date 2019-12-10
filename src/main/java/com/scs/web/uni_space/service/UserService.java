package com.scs.web.uni_space.service;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.User;
import org.springframework.web.multipart.MultipartFile;

import com.scs.web.uni_space.common.Result;


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
     * @param userDto
     * @return result
     */
    Result signIn(UserDto userDto);

    /**
     * 注册方法
     * @param userDto
     * @return result
     */
    Result signUp(UserDto userDto);

    /**
     * 更新用户资料
     * @param user
     * @return int
     * @Description
     */
    Result updateUserData(User user);

    /**
     * 更新用户密码
     * @param userDto
     * @return
     */
    Result updateUserPassword(UserDto userDto);

    /**
     * 根据id修改用户头像
     * @param userDto
     * @return
     */
    Result updateUserAvatar(UserDto userDto);

    /**
     * 通过id查找账户
     * @param id
     * @return
     */
    Result selectUserById(Long id);


    /**
     * 更改pc端头像
     * @param file
     * @return
     * @throws Exception
     */
    String updatePcAvatar(MultipartFile file) throws Exception;
}