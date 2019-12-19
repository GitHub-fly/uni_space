package com.scs.web.uni_space.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className JournalPicture
 * @Description 日志照片
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalPicture {
    private Long id;
    private Long journalId;
    private Timestamp createTime;
    private String url;
}
