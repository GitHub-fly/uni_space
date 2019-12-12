package com.scs.web.uni_space.domain.vo;

import com.scs.web.uni_space.domain.entity.Friend;
import lombok.Data;

/**
 * @author suyuxi
 * @className FriendVo
 * @Description 好友信息视图
 * @Date 2019/12/10
 * @Version 1.0
 **/
@Data
public class FriendVo {
    private Friend friend;
    private Integer num;
}
