package com.scs.web.uni_space.domain.dto;

import lombok.Data;

/**
 * @ClassName MusicDto
 * @Description 音乐传输对象
 * @Author xiaobinggan
 * @Date 2019/12/19 3:45 下午
 * @Version 1.0
 **/
@Data
public class MusicDto {
    private Long id;
    private Long userId;
    private String name;
    private String content;
    private String singer;
}
