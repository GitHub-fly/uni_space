package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import com.scs.web.uni_space.domain.dto.CommentDto;
import com.scs.web.uni_space.domain.entity.Comment;
import com.scs.web.uni_space.mapper.CommentMapper;
import com.scs.web.uni_space.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Description 评论操作实现类
 * @Author xiaobinggan
 * @Date 2019/12/15 3:26 下午
 * @Version 1.0
 **/
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
//    @Resource
//    CommonMapper commonMapper;

    @Resource
    CommentMapper commentMapper;


    @Override
    public Result selectCommentById(CommentDto commentDto) {
        List<Comment> commentList = new ArrayList<>(10);
        try {
            commentList = commentMapper.selectCommentById(commentDto.getJournalId());
        } catch (SQLException e) {
            log.error("查询失败");
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.success(commentList);
    }

    @Override
    public Result insertComment(CommentDto commentDto) {
        try {
//            commonMapper.returnId("t_friend");
            commentDto.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            commentMapper.insertComment(commentDto);
            commentMapper.addComment(commentDto.getJournalId());
        } catch (SQLException e) {
            log.error("插入失败");
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.success();
    }

    @Override
    public Result deleteComment(CommentDto commentDto) {
        try {
//            commentMapper.deleteComment(commentDto.getId());
            commentMapper.decreaseComments(commentDto.getJournalId());
        } catch (SQLException e) {
            log.error("删除失败");
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.success();
    }
}
