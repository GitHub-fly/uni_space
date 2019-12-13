package com.scs.web.uni_space.domain.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className journal
 * @Description 日志
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
public class Journal {
    private Long id;
    private Long userId;
    private String content;
    private String thumbnail;
    private String title;
    private Integer likes;
    private Integer comments;
    private Timestamp createTime;
    private Short collectionFlag;
    private Long JournalPictureNum;
}
