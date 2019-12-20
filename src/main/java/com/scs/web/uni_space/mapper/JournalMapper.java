package com.scs.web.uni_space.mapper;


import com.scs.web.uni_space.domain.dto.LikeDto;
import com.scs.web.uni_space.domain.entity.Journal;
import com.scs.web.uni_space.domain.entity.JournalPicture;
import com.scs.web.uni_space.domain.entity.Like;
import com.scs.web.uni_space.domain.vo.JournalVo;
import com.scs.web.uni_space.domain.vo.RecommendVo;
import com.scs.web.uni_space.domain.vo.UserCommentVo;
import org.apache.ibatis.annotations.*;

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
     * 批量插入日志中照片数据
     *
     * @param list
     * @throws SQLException
     */
    @Insert({"<script>" +
            "INSERT INTO t_photo_journal(journal_id, create_time, url) VALUES" +
            "<foreach collection='list' item='item' index='index' separator=','> " +
            "(#{item.journalId}, #{item.createTime}, #{item.url}) " +
            "</foreach> ",
            "</script> "
    })
    void batchInsertJournalOfPhoto(List<JournalPicture> list) throws SQLException;


    /**
     * 删除指定id日志中的所有照片
     *
     * @param journalId
     */
    void batchDeletePhotos(Long journalId);

    /**
     * 查询用户所有的日志列表；
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM t_journal  WHERE user_id =#{id}")
    List<Journal> findAllJournal(Long id) throws SQLException;

    /**
     * 查找首页日志所需要数据 不包括评论数
     *
     * @param fromId
     * @return
     * @throws SQLException
     */

    @Select("SELECT DISTINCTROW a.to_id AS user_id, b.nickname, b.avatar,c.id,c.title, c.content, c.thumbnail,  c.likes, c.comments, c.create_time,\n" +
            "c.journal_picture_num ,d.create_time AS LikeStatus\n" +
            "            FROM t_friend a\n" +
            "\t\t\t\t\t\tLEFT JOIN t_user b \n" +
            "            ON a.to_id = b.id \n" +
            "            LEFT JOIN t_journal c \n" +
            "            ON b.id=c.user_id \n" +
            "\t\t\t\t\t\tLEFT JOIN t_like d\n" +
            "\t\t\t\t\t  ON\tc.id =d.journal_id AND a.from_id = d.user_id\n" +
            "            WHERE a.from_id = #{fromId} AND a.friend_flag = 1 AND  c.content IS NOT NULL\n" +
            "\t\t\t\t\t   GROUP BY title\t\n" +
            "            ORDER BY c.create_time DESC")
    List<JournalVo> findFriendJournal(Long fromId) throws SQLException;


    /**
     * 推荐日志所需要的mapper
     *
     * @param fromId
     * @return
     * @throws SQLException
     */
    @Select("SELECT DISTINCT a.to_id AS user_id,  c.id, c.title, c.content,  c.thumbnail,  c.likes, c.create_time \n" +
            "            FROM t_friend a LEFT JOIN t_user b \n" +
            "            ON a.to_id = b.id \n" +
            "            LEFT JOIN t_journal c \n" +
            "            ON b.id=c.user_id \n" +
<<<<<<< HEAD
            "            WHERE a.from_id = #{fromId} AND a.friend_flag = 1  AND  c.content IS NOT NULL \n" +
=======
            "            WHERE a.from_id = #{fromId} AND a.friend_flag = 1 AND  c.content IS NOT NULL \n" +
>>>>>>> 0fdbd6e775d931751a6a90fa19b8995e5f2eedcd
            "            ORDER BY c.likes DESC")
    List<RecommendVo> recommendJournal(Long fromId) throws SQLException;

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
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Select("SELECT id ,journal_id,url " +
            "FROM t_journal_picture " +
            " WHERE journal_id = #{id}")
    List<JournalPicture> selectJournalPictureById(Long id) throws SQLException;

    /**
     * 通过日志id找到评论内容
     *
     * @param id
     * @return
     * @throws SQLException
     */

    @Select("SELECT a.journal_id,a.content,a.create_time ,a.user_id,b.nickname,b.avatar\n" +
            "FROM t_comment a\n" +
            "LEFT JOIN t_user b\n" +
            "ON a.user_id =b.id\n" +
            "WHERE a.journal_id=#{id}\n" +
            "ORDER BY a.create_time DESC      ")
    List<UserCommentVo> selectCommentById(Long id) throws SQLException;


    /**
     * 通过日志id查看文章详情
     *
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


    /**
     * 对于文章点赞数查询 查询是否有一条记录
     *
     * @param userId
     * @param journalId
     * @return
     * @throws SQLException
     */


    @Select("SELECT *\n" +
            "FROM t_like \n" +
            "WHERE user_id=#{userId} AND journal_id =#{journalId}")
    Like concernJournalLike(Long userId, Long journalId) throws SQLException;

    /**
     * 对于未点赞就插入数据一条数据
     *
     * @param userId
     * @param journalId
     * @throws SQLException
     */

    @Insert("INSERT INTO t_like (user_id,journal_id) VALUES(#{userId},#{journalId})\t")
    void insertLike(Long userId, Long journalId) throws SQLException;

    /**
     * 更新日志点赞数
     *
     * @param journalId
     * @throws SQLException
     */
    @Update("UPDATE t_journal SET likes=(SELECT COUNT(journal_id)  FROM t_like \n" +
            "            WHERE journal_id =#{journalId}) WHERE id =#{journalId}\n")
    void updateLikes(Long journalId) throws SQLException;

    /**
     * 统计点赞总数
     *
     * @param journalId
     * @return
     * @throws SQLException
     */
    @Select("SELECT journal_id,COUNT(journal_id) AS likes  FROM t_like\n" +
            "                WHERE journal_id =#{journalId}")
    LikeDto countLike(Long journalId) throws SQLException;


    /**
     * 删除点赞
     *
     * @param userId
     * @param journalId
     * @throws SQLException
     */
    @Delete("DELETE FROM t_like WHERE user_id =#{userId} AND journal_id =#{journalId}\n")
    void deleteLike(Long userId, Long journalId) throws SQLException;

    /**
     * 新增日志
     *
     * @param journal
     * @throws SQLException
     */

    @Insert("INSERT  INTO t_journal (user_id ,content,thumbnail,title , " +
            "create_time,journal_picture_num )VALUES(#{journal.userId},#{journal.content}, " +
            "#{journal.thumbnail},#{journal.title},#{journal.createTime},#{journal.JournalPictureNum}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertJournal(@Param("journal") Journal journal) throws SQLException;


    /**
     * 新增日志中将日志中的照片提取出来插进去
     *
     * @param journalPictures
     * @throws SQLException
     */
    @Insert({
            "<script> ",
            "INSERT into  (journal_id,create_time,url) VALUES ",
            "<foreach collection = 'list' item = 'item' index = 'index' separator = ','> ",
            "(#{item.journalId},#{item.createTime},#{item.url}) ",
            "</foreach> ",
            "</script> "
    })
    void batchInsertJournal(@Param(value = "list") List<JournalPicture> journalPictures) throws SQLException;

    /**
     * t_journal_photo
     * 批量删除日志
     *
     * @param longList
     * @throws SQLException
     */

    @Delete({
            "<script> ",
            "DELETE FROM t_journal WHERE id IN( ",
            "<foreach collection = 'list' item = 'item' index ='index' separator =','> ",
            "#{item} ",
            "</foreach>) ",
            "</script>"
    })
    void batchDeleteJournal(@Param(value = "list") List<Long> longList) throws SQLException;

    /**
     * 批量删除照片
     *
     * @param longList
     * @throws SQLException
     */
    @Delete({
            "<script> ",
            "DELETE FROM t_journal_photo WHERE journal_id IN( ",
            "<foreach collection = 'list' item = 'item' index ='index' separator =','> ",
            "#{item} ",
            "</foreach>) ",
            "</script>"
    })
    void batchDeleteJournalPicture(@Param(value = "list") List<Long> longList) throws SQLException;

}