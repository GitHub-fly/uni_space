package com.scs.web.uni_space.domain.entity;

import lombok.Data;

/**
 * @author suyuxi
 * @className Security
 * @Description 密保
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
public class Security {
    private Long id;
    private Long userId;
    private Long photoAlbumId;
    private String question;
    private String answer;
}
