package com.scs.web.uni_space.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName QueryDto
 * @Description 查询传输对象
 * @Author xiaobinggan
 * @Date 2019/12/13 5:21 下午
 * @Version 1.0
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryDto {
    private Long id;
    private String equalsString;
    private String keyWords;
    private String password;
}
