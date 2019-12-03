package com.scs.web.uni_space.domain.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className PhotoAlbum
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
public class PhotoAlbum {
    private Long id;
    private Long albumId;
    private String url;
    private String description;
    private Timestamp createTime;
}
