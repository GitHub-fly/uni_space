package com.scs.web.uni_space.domain.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className PhotoAlbum
 * @Description 相册
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
public class PhotoAlbum {
    private Long id;
    private Long userId;
    private String cover;
    private String name;
    private String type;
    private Timestamp createTime;
    private String introduction;
}
