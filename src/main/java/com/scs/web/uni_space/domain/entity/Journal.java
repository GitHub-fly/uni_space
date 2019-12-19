package com.scs.web.uni_space.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className journal
 * @Description 日志
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Journal {
    private Long id;
    private Long userId;
    private  String introduction;
    private String content;
    private String thumbnail;
    private String title;
    private Integer likes;
    private Integer comments;
    private Timestamp createTime;
    private Short collectionFlag;
    private Long JournalPictureNum;
}
