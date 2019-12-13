package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.Vo.JournalVo;
import com.scs.web.uni_space.domain.entity.Journal;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @author wl
 * @ClassNamejournal
 * @Description TODO
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
    List<Journal> findAllJournal(long id) throws SQLException;


    /**
     * 查找首页日志所需要数据 不包括评论数
     *
     * @param formId
     * @return
     * @throws SQLException
     */

    @Select("SELECT a.to_id AS user_id,b.nickname,b.avatar,c.content,c.thumbnail,c.id,c.title,c.likes,c.comments,c.create_time\n" +
            "FROM  t_friend  a LEFT  JOIN  t_user b  \n" +
            "ON    a.to_id = b.id\n" +
            "LEFT  JOIN t_journal c \n" +
            "ON b.id=c.user_id\n" +
            "WHERE a.from_id = #{fromId} AND a.friend_flag = 1\n" +
            " ORDER BY c.create_time DESC")
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
            "WHERE user_id = 1 ")
    List<Journal> selectById(Long id) throws SQLException;

}
