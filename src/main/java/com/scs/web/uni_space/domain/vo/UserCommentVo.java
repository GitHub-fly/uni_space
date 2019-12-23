package com.scs.web.uni_space.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author wl
 * @ClassNameUserCommentVo
 * @Description TODO
 * @Date 2019/12/14
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCommentVo {
    private Long journalId;
    private String content;
    private Timestamp createTime;
    private Long userId;
    private String nickname;
    private String avatar;
}
