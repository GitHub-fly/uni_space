package com.scs.web.uni_space.domain.dto;

import lombok.Data;

/**
 * @author suyuxi
 * @className QueryDto
 * @Description TODO
 * @Date 2019/12/10
 * @Version 1.0
 **/
@Data
public class QueryDto {
    private Long formId;
    private Long toId;
    private String keyWords;
}
