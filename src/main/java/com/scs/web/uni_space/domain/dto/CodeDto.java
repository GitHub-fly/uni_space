package com.scs.web.uni_space.domain.dto;

import lombok.Data;

/**
 * @ClassName codeDto
 * @Description 图形验证码
 * @Author xiaobinggan
 * @Date 2019/12/6 11:23 上午
 * @Version 1.0
 **/
@Data
public class CodeDto {
    private String code;
    private String img;
}
