package com.scs.web.uni_space.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author wl
 * @ClassNameSignDto
 * @Description 用户传输对象
 * @Date 2019/12/8
 * @Version 1.0
 */
@Data
@Builder
public class UserDto {
    private Long id;
    //单独修改方法
    private String avatar;
    //单独修改方法
    private String password;
    //统一修改
    private String nickname;
    private String address;
    private String gender;
    private String introduction;
    private String constellation;
    private LocalDate birthday;
    //手机号
    private String mobile;
    //更改密码发送的短信
    private String verifyCode;
}
