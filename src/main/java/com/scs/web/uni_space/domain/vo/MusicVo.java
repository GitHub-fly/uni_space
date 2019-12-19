package com.scs.web.uni_space.domain.vo;

import lombok.Data;

/**
 * @ClassName MusicVo
 * @Description 音乐视图
 * @Author xiaobinggan
 * @Date 2019/12/19 8:32 上午
 * @Version 1.0
 **/
@Data
public class MusicVo {
    private Long id;
    private Long userId;
    private String name;
    private String content;
    private String singer;
}
