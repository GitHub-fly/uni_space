package com.scs.web.uni_space.domain.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className Photo
 * @Description 照片
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
public class Photo {
    private Long id;
    private Long albumId;
    private String url;
    private String description;
    private Timestamp createTime;
}
