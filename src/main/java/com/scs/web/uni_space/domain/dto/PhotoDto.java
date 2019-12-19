package com.scs.web.uni_space.domain.dto;

import com.scs.web.uni_space.domain.entity.Photo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author suyuxi
 * @className PhotoDto
 * @Description 照片传输对象
 * @Date 2019/12/17
 * @Version 1.0
 **/
@Data
@Builder
public class PhotoDto {
    private Long albumId;
    private List<Long> longList;
}
