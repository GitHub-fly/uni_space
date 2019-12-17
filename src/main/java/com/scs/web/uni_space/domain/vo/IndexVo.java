package com.scs.web.uni_space.domain.vo;

import com.scs.web.uni_space.domain.entity.JournalPicture;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName IndexVo
 * @Description 首页视图
 * @Author xiaobinggan
 * @Date 2019/12/11 10:43 上午
 * @Version 1.0
 **/

@Data
public class IndexVo {
    private String FriendAvatar;
    private String UserAvatar;
    private String title;
    private LocalDateTime createTime;
    private List<JournalPicture> journalPictureList;
}
