package com.scs.web.uni_space.domain.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author suyuxi
 * @className LikeVo
 * @Description 点赞消息视图
 * @Date 2019/12/21
 * @Version 1.0
 **/
@Data
public class LikeVo {

    private Long userId;
    private String nickname;
    private String avatar;
    private Long journalId;
    private String title;
    private Timestamp createTime;

}
