package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.CommentDto;
import com.scs.web.uni_space.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName CommentController
 * @Description 评论控制层
 * @Author xiaobinggan
 * @Date 2019/12/15 3:51 下午
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/comment")
@Api(value = "CommentController", tags = "评论接口")
public class CommentController {
    @Resource
    private CommentService commentService;

    @ApiOperation(value = "根据文章ID查出评论者的ID，评论内容，和评论时间", notes = "data为评论的集合")
    @PostMapping(value = "/")
    Result selectCommentById(@RequestBody CommentDto commentDto) {
        return commentService.selectCommentById(commentDto);
    }

    @ApiOperation(value = "添加一条评论", notes = "传递参数为userId,journalId,content , msg为成功，则添加成功")
    @PostMapping(value = "/add")
    Result insertComment(@RequestBody CommentDto commentDto) {
        return commentService.insertComment(commentDto);
    }

    @ApiOperation(value = "删除一条评论", notes = "传递参数为id , journalId , msg为成功，则添加成功")
    @PostMapping(value = "/delete")
    Result deleteComment(@RequestBody CommentDto commentDto) {
        return commentService.deleteComment(commentDto);
    }
}
