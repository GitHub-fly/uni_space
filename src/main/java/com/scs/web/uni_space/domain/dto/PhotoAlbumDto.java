package com.scs.web.uni_space.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className PhotoAlbumDto
 * @Description 相册传输类
 * @Date 2019/12/16
 * @Version 1.0
 **/
@Data
@Builder
public class PhotoAlbumDto {
    private Long id;
    private Long userId;
    private String cover;
    private String name;
    private String type;
    private String introduction;
    private Timestamp createTime;
}
