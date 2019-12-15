package com.scs.web.uni_space.domain.vo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Null;
import java.sql.Timestamp;

/**
 * @author wl
 * @ClassNameJournal
 * @Description 日志视图
 * @Date 2019/12/11
 * @Version 1.0
 */
@Data

@Null
@Builder
public class JournalVo {
    /**
     * mapper层查出来数据要与实体类对应
     */
    private Long userId;
    private String nickname;
    private String avatar;
    private Long id;
    private String title;
    private String content;
    private String thumbnail;
    private Integer likes;
    private Integer comments;
    private Timestamp createTime;
    private String journalPictureNum;


}
