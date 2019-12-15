package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.dto.CommentDto;
import com.scs.web.uni_space.domain.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName CommentMapper
 * @Description 评论sql
 * @Author xiaobinggan
 * @Date 2019/12/15 11:04 上午
 * @Version 1.0
 **/
public interface CommentMapper {
    /**
     * 根据文章ID查出评论者的ID，评论内容，和评论时间
     *
     * @param journalId
     * @return
     * @throws SQLException
     */
    @Select("SELECT id , user_id , journal_id , content , create_time FROM t_comment WHERE journal_id = #{journalId} ")
    List<Comment> selectCommentById(Long journalId) throws SQLException;

    /**
     * 添加一条评论
     *
     * @param commentDto
     * @throws SQLException
     */
    @Select("INSERT INTO t_comment (user_id , journal_id , content , create_time) VALUES (#{commentDto.userId} , #{commentDto.journalId} , #{commentDto.content} , #{commentDto.createTime}) ")
    void insertComment(@Param("commentDto") CommentDto commentDto) throws SQLException;

    /**
     * 删除一条评论
     *
     * @param id
     * @throws SQLException
     */
    @Select("DELETE FROM t_comment WHERE id = #{id} ")
    void deleteComment(Long id) throws SQLException;
}
