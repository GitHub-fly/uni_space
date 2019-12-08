package com.scs.web.uni_space.service;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.util.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wl
 * @ClassNameUserService
 * @Description 用户服务类
 * @Date 2019/12/2
 * @Version 1.0
 */
public interface UserService {
    /**
     * @param userDto
     * @return result
     * @Description 登录方法
     */
    Result signIn(UserDto userDto);

    /**
     * @param userDto
     * @return result
     * @Description 注册方法
     */
    Result signUp(UserDto userDto);

    /**
     * @param user
     * @return int
     * @Description 更新用户资料
     */
    Result updateUserData(User user);

    /**
     * @param userDto
     * @return
     */
    /**
     * @param
     * @return int
     * @Description 更新用户密码
     */

    Result updateUserPassword(UserDto userDto);

    /**
     *
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