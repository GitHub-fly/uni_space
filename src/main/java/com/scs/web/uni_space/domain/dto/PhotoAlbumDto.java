package com.scs.web.uni_space.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className PhotoAlbumDto
 * @Description TODO
 * @Date 2019/12/16
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoAlbumDto {
    private Long id;
    private Long userId;
    private String cover;
    private String name;
    private String type;
    private String introduction;
}
