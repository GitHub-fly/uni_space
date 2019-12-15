package com.scs.web.uni_space.domain.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className Comment
 * @Description 评论
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
public class Comment {
    private Long id;
    private Long userId;
    private Long journalId;
    private String content;
    private Timestamp createTime;
}
