package com.scs.web.uni_space.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 小黑
 * @ClassNameUserDto
 * @Description 登录时的对象
 * @Date 2019/12/2
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {
    private int id;
    //包含三种信息分别为 手机号，账号，邮箱
    private String Name;
    //短信验证码
    private String verifyCode;
    private String password;
    private String avatar;

}
