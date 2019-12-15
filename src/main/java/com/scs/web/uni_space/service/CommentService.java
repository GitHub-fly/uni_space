package com.scs.web.uni_space.service;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.CommentDto;

/**
 * @ClassName CommentService
 * @Description 评论操作接口
 * @Author xiaobinggan
 * @Date 2019/12/15 3:19 下午
 * @Version 1.0
 **/
public interface CommentService {
    /**
     * 根据文章ID查出评论者的ID，评论内容，和评论时间
     *
     * @param commentDto
     * @return
     */
    Result selectCommentById(CommentDto commentDto);

    /**
     * 添加一条评论
     *
     * @param commentDto
     * @return
     */
    Result insertComment(CommentDto commentDto);

    /**
     * 删除一条评论
     *
     * @param commentDto
     * @return
     */
    Result deleteComment(CommentDto commentDto);
}
