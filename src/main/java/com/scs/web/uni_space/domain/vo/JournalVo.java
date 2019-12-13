package com.scs.web.uni_space.domain.Vo;

import com.scs.web.uni_space.domain.entity.Journal;
import com.scs.web.uni_space.domain.entity.JournalPicture;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wl
 * @ClassNameJournal
 * @Description TODO
 * @Date 2019/12/11
 * @Version 1.0
 */
@Builder
@Data
@Null

public class JournalVo {

    private Long userId;
    private String nickname;
    private  String avatar;
    private  String content;
    private String thumbnail;
    private  Long id;
    private  String title;
    private  Integer likes;
    private  Integer comments;
    private Timestamp createTime;
    private Integer CountJournal;

}
