package com.scs.web.uni_space.domain.entity;

import lombok.Data;

/**
 * @author xunmi
 * @ClassName RegionMapper
 * @Description 全国省市区地址Region实体类
 * @Date 2019/12/3
 * @Version 1.0
 **/
@Data
public class Region {
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer level;
    private String cityCode;
    private String postCode;
    private String mergeName;
}
