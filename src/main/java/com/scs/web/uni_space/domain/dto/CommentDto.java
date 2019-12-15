package com.scs.web.uni_space.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @ClassName CommentDto
 * @Description 评论传输对象
 * @Author xiaobinggan
 * @Date 2019/12/15 1:11 下午
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    /**
     * 评论者的ID
     */
    private Long userId;
    /**
     * 评论文章的ID
     */
    private Long journalId;
    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
