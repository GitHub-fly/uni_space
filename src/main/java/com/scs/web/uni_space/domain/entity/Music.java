package com.scs.web.uni_space.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className Music
 * @Description 音乐
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
@Builder
public class Music {
    private Long id;
    private Long userId;
    private String name;
    private String content;
    private String singer;
    private Timestamp createTime;
}
