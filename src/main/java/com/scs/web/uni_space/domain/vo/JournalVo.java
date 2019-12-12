package com.scs.web.uni_space.domain.vo;

import lombok.Data;

/**
 * @ClassName JournalVo
 * @Description 日志视图
 * @Author xiaobinggan
 * @Date 2019/12/11 9:10 上午
 * @Version 1.0
 **/
@Data
public class JournalVo {
    private Long id;
    private Long journalId;
    private String url;
}
