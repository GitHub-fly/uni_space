package com.scs.web.uni_space.domain.dto;

import lombok.*;

/**
 * @author suyuxi
 * @className FriendDto
 * @Description 好友传输类
 * @Date 2019/12/9
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendDto {
    /**
     * 动作发起者id
     */
    private Long fromId;
    /**
     * 被作用对象id
     */
    private Long toId;
    /**
     * 动作触发的条件
     */
    private String keyWords;

    /**
     * 好友访问权限
     */
    private Integer collectionFlag;
}
