package com.scs.web.uni_space.domain.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className Like
 * @Description 点赞
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
public class Like {
    private Timestamp createTime;
    private Long id;
    private Long userId;
    private Long journalId;
    private  boolean likestatus;
}
