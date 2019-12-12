package com.scs.web.uni_space.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author wl
 * @ClassNameSignDto
 * @Description TODO
 * @Date 2019/12/8
 * @Version 1.0
 */
@Data
@Builder
public class SignDto {
    private Integer id;
    private String mobile;
    private String account;
    private String email;
    private String Name;
    private String password;
    private String verifyCode;
    private String code;
}
