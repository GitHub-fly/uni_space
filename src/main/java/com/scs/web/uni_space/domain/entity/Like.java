package com.scs.web.uni_space.domain.entity;

import lombok.Data;

/**
 * @author suyuxi
 * @className Like
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
public class Like {
    private Long id;
    private Long userId;
    private Long journalId;
}
