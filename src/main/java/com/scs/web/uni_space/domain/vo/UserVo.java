package com.scs.web.uni_space.domain.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * @author suyuxi
 * @className FriendVo
 * @Description 好友信息视图
 * @Date 2019/12/10
 * @Version 1.0
 **/
@Data
public class UserVo {
    private Long id;
    private String mobile;
    private String account;
    private String nickname;
    private String email;
    private String avatar;
    private String address;
    private String gender;
    private String introduction;
    private String constellation;
    private Long skinId;
    private Timestamp createTime;
    private LocalDate birthday;
    private Integer friendSum;
    private Integer journalSum;
    private Integer photoAlbumSum;
    private Integer photoSum;
}
