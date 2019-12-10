package com.scs.web.uni_space.domain.dto;

import lombok.Data;

/**
 * @author suyuxi
 * @className FriendDto
 * @Description 好友传输类
 * @Date 2019/12/9
 * @Version 1.0
 **/
@Data
public class FriendDto {
    private Long fromId;
    private Long toId;
}
