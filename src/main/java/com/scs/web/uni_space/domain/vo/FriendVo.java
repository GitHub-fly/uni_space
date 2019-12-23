package com.scs.web.uni_space.domain.vo;

import lombok.Data;

/**
 * @author suyuxi
 * @className friendVo
 * @Description TODO
 * @Date 2019/12/22
 * @Version 1.0
 **/
@Data
public class FriendVo {
    private Long id;
    private String nickname;
    private String avatar;
    private String address;
    private String introduction;
    private Integer friendFlag;
    private Integer collectionFlag;
    private Integer collection;
}
