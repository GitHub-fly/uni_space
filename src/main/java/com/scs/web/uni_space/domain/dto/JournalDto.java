package com.scs.web.uni_space.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author wl
 * @ClassNameasdsa
 * @Description 日志传输类
 * @Date 2019/12/14
 * @Version 1.0
 */
@NotNull
@Data

@AllArgsConstructor
@NoArgsConstructor
public class JournalDto {
       private  Long id;
       private Long userId;
       private  String title;
       private String thumbnail;
       private String content;
       /**
        * 日志中图片的地址集合
        */
       private String[] urls;
       private Timestamp createTime;
       private Long JournalPictureNum;
       private List<Long> longList;
}
