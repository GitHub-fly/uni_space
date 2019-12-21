package com.scs.web.uni_space.domain.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className PhotoAlbumVo
 * @Description 相册视图
 * @Date 2019/12/16
 * @Version 1.0
 **/

@Data
public class PhotoAlbumVo {
    private Long id;
    private Long userId;
    private String cover;
    private String name;
    private String type;
    private Timestamp createTime;
    private String introduction;
    private Integer securityId;
    private String url;
}
