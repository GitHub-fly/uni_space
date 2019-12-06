package com.scs.web.uni_space.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author suyuxi
 * @className friend
 * @Description 好友
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    private Long id;
    private Long fromId;
    private Long toId;
    private Short friendFlag;
    private Short collectionFlag;
}
