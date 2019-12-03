package com.scs.web.uni_space.domain.entity;

import lombok.Data;

/**
 * @author suyuxi
 * @className JournalPicture
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/

@Data
public class JournalPicture {
    private Long id;
    private Long journalId;
    private String url;
}
