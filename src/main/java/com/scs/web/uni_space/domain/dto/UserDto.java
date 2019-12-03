package com.scs.web.uni_space.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 小黑
 * @ClassNameUserDto
 * @Description TODO
 * @Date 2019/12/2
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private String mobile;
    private String account;
    private String email;
    private String password;
}
