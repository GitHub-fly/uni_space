package com.scs.web.uni_space.mapper;


import com.scs.web.uni_space.domain.entity.Journal;
import com.scs.web.uni_space.domain.entity.JournalPicture;
import com.scs.web.uni_space.domain.vo.JournalVo;
import com.scs.web.uni_space.domain.vo.UserCommentVo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @author wl
 * @ClassNamejournal
 * @Description 日志sql
 * @Date 2019/12/9
 * @Version 1.0
 */
public interface JournalMapper {

    /**
     * 查询用户所有的日志列表；
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM t_journal  WHERE user_id =#{id}")
    List<Journal> findAllJournal(Long id) throws SQLException;

    /**
     * 查找首页日志所需要数据 不包括评论数
     *
     * @param formId
     * @return
     * @throws SQLException
     */

    @Select("SELECT a.to_id AS user_id, b.nickname, b.avatar,c.id , c.title, c.content, c.thumbnail,  c.likes, c.comments, c.create_time,c.journal_picture_num " +
            "FROM t_friend a LEFT JOIN t_user b " +
            "ON a.to_id = b.id " +
            "LEFT JOIN t_journal c " +
            "ON b.id=c.user_id " +
            "WHERE a.from_id = #{formId} AND a.friend_flag = 1 " +
            "ORDER BY c.create_time DESC ")
    List<JournalVo> findFriendJournal(Long formId) throws SQLException;


    /**
     * 查找指定id用户的所有日志信息
     * 包括日志id/标题/正文/缩略图/喜欢度/评论数/表发时间
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Select("SELECT id, content, thumbnail, title, likes, comments, create_time " +
            "FROM t_journal " +
            "WHERE user_id = #{id} ")
    List<Journal> selectById(Long id) throws SQLException;

    /**
     * 通过日志id查询用户日志里面的相册
     */
    @Select("SELECT id ,journal_id,url " +
            "FROM t_journal_picture " +
            " WHERE journal_id = #{id}")
    List<JournalPicture> selectJournalPictureById(Long id) throws SQLException;

    /*
     * 通过日志id找到评论内容
     * */

    @Select("SELECT a.journal_id,a.content,a.create_time ,a.user_id,b.nickname,b.avatar\n" +
            "FROM t_comment a\n" +
            "LEFT JOIN t_user b\n" +
            "ON a.user_id =b.id\n" +
            "WHERE a.journal_id=#{id}\n" +
            "ORDER BY a.create_time DESC      ")
    @Results(value = {
            @Result(property = "journalId", column = "journal_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "avatar", column = "avatar"),

    })
    List<UserCommentVo> selectCommentById(Long id) throws SQLException;


    /**
     *通过日志id查看文章详情
     * @param id
     * @return
     * @throws SQLException
     */

    @Select("SELECT a.user_id,b.nickname,b.avatar,a.id,a.title,a.content,a.thumbnail,a.likes,a.comments,a.create_time,a.journal_picture_num\n" +
            "FROM t_journal a\n" +
            "LEFT JOIN t_user b\n" +
            "ON a.user_id =b.id\n" +
            "WHERE a.id=#{id}")
    JournalVo selectJournalById(Long id) throws SQLException;

}
