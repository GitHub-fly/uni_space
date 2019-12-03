package com.scs.web.uni_space.domain.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className Music
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
public class Music {
    private Long id;
    private String name;
    private String content;
    private String singer;
    private Timestamp createTime;
}
