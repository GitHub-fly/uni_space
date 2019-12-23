package com.scs.web.uni_space.mapper;


import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.domain.entity.Journal;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.domain.vo.FriendVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;

/**
 * @author suyuxi
 */
public interface FriendMapper {
    /**
     * 查找好友表所有数据
     * 或者通过关键字模糊查询好友信息
     * @param fromId
     * @param key
     * @return list
     * @throws SQLException
     */
    @Select({"SELECT c.id,c.avatar,c.nickname,c.address,c.introduction,a.collection_flag,b.collection_flag AS collection " +
            "FROM t_friend a " +
            "LEFT JOIN t_user c " +
            "ON a.to_id=c.id " +
            "LEFT JOIN t_friend b " +
            "ON b.from_id = c.id AND b.to_id = a.from_id AND b.friend_flag = 1 " +
            "WHERE (a.from_id = #{fromId} " +
            "AND a.friend_flag = 1) " +
            "AND (c.mobile LIKE CONCAT('%', #{key}, '%') " +
            "OR c.account LIKE CONCAT('%', #{key}, '%') " +
            "OR c.email LIKE CONCAT('%', #{key}, '%') " +
            "OR c.nickname LIKE CONCAT('%', #{key}, '%') " +
            "OR c.introduction LIKE  CONCAT('%', #{key}, '%') ) "})
    List<FriendVo> selectAll(Long fromId, String key) throws SQLException;
//    @Select({"SELECT c.*,COUNT(b.user_id) AS journalSum " +
//            "FROM t_friend a " +
//            "LEFT JOIN t_user c " +
//            "ON a.to_id=c.id " +
//            "LEFT JOIN t_journal b " +
//            "ON c.id=b.user_id  " +
//            "WHERE (a.from_id = #{fromId} " +
//            "AND a.friend_flag = 1) " +
//            "AND (c.mobile LIKE CONCAT('%', #{key}, '%') " +
//            "OR c.account LIKE CONCAT('%', #{key}, '%') " +
//            "OR c.email LIKE CONCAT('%', #{key}, '%') " +
//            "OR c.nickname LIKE CONCAT('%', #{key}, '%') " +
//            "OR c.introduction LIKE CONCAT('%', #{key}, '%')) " +
//            "GROUP BY b.user_id HAVING COUNT(b.user_id) >= 1 ORDER BY COUNT(b.user_id) DESC "})
//    List<UserVo> selectAll(Long fromId, String key) throws SQLException;

    /**
     * 通过指定用户id查找日志信息
     *
     * @param userId
     * @return List
     * @throws SQLException
     */
    @Select("SELECT b.id, b.user_id, b.content, b.thumbnail, b.title, " +
            "b.likes, b.comments, b.create_time, b.collection_flag " +
            "FROM t_user a " +
            "LEFT JOIN t_journal b " +
            "ON a.id = b.user_id " +
            "WHERE a.id = #{userId} " +
            "ORDER BY b.create_time DESC ")
    List<Journal> searchJournalByUserId(Long userId) throws SQLException;

    /**
     * 通过关键字进行搜索用户信息
     *
     * @param fromId
     * @param key
     * @return List
     * @throws SQLException
     */
    @Select({"SELECT a.id,a.avatar,a.address,a.nickname,a.introduction,b.friend_flag " +
            "FROM t_user a " +
            "LEFT JOIN t_friend b " +
            "ON a.id = b.to_id AND b.from_id = #{fromId} " +
            "WHERE  a.mobile LIKE CONCAT('%', #{key}, '%') " +
            "OR a.account LIKE  CONCAT('%', #{key}, '%') " +
            "OR a.email LIKE CONCAT('%', #{key}, '%') " +
            "OR a.nickname LIKE CONCAT('%', #{key}, '%') " +
            "OR a.introduction LIKE  CONCAT('%', #{key}, '%')  "})
    List<FriendVo> searchUserByKey(Long fromId, String key) throws SQLException;

//    @Select({"SELECT COUNT(b.user_id) AS journal_sum, a.*,c.friend_flag " +
//            "FROM t_user a " +
//            "LEFT JOIN t_journal b " +
//            "ON a.id = b.user_id " +
//            "LEFT JOIN t_friend c " +
//            "ON a.id = c.to_id AND c.from_id = #{fromId} " +
//            "WHERE  a.mobile LIKE CONCAT('%', #{key}, '%') " +
//            "OR a.account LIKE  CONCAT('%', #{key}, '%') " +
//            "OR a.email LIKE CONCAT('%', #{key}, '%') " +
//            "OR a.nickname LIKE CONCAT('%', #{key}, '%') " +
//            "OR a.introduction LIKE  CONCAT('%', #{key}, '%') " +
//            "GROUP BY b.user_id HAVING COUNT(b.user_id) >= 1 ORDER BY COUNT(b.user_id) DESC  "})
//    List<UserVo> searchUserByKey(Long fromId, String key) throws SQLException;


    /**
     * 通过to_id查找所有请求者信息
     *
     * @param fromId
     * @return List
     * @throws SQLException
     */
    @Select({"SELECT * FROM t_user WHERE id IN  ( SELECT from_id FROM t_friend WHERE friend_flag = 0 AND to_id = #{fromId}) "})
    List<User> selectByToId(Long fromId) throws SQLException;


    /**
     * 查看双方是否有关联数据
     *
     * @param fromId
     * @param toId
     * @return Friend
     * @throws SQLException
     */
    @Select({"SELECT * FROM t_friend WHERE from_id = #{fromId} AND to_id = #{toId} "})
    Friend selectFriendFlag(Long fromId, Long toId) throws SQLException;

    /**
     * 添加好友，状态为0
     *
     * @param fromId
     * @param toId
     * @return int
     * @throws SQLException
     */
    @Insert({"INSERT INTO t_friend (from_id,to_id ,friend_flag) VALUES (#{fromId},#{toId},0) "})
    void insertOther(Long fromId, Long toId) throws SQLException;

    /**
     * 更改好友状态，0变1
     *
     * @param fromId
     * @param toId
     * @return int
     * @throws SQLException
     */
    @Update({"UPDATE t_friend SET friend_flag = 1 WHERE from_id = #{toId} AND to_id = #{fromId}"})
    void updateFriendFlag(Long fromId, Long toId) throws SQLException;

    /**
     * 添加好友，状态为1
     *
     * @param fromId
     * @param toId
     * @return int
     * @throws SQLException
     */
    @Insert({"INSERT INTO t_friend (from_id,to_id ,friend_flag) VALUES (#{fromId},#{toId},1) "})
    void insertEachOther(Long fromId, Long toId) throws SQLException;

    /**
     * 删除请求
     *
     * @param fromId
     * @param toId
     * @return int
     * @throws SQLException
     */
    @Delete({"DELETE FROM t_friend WHERE from_id = #{toId} AND to_id = #{fromId} "})
    void deleteReject(Long fromId, Long toId) throws SQLException;

    /**
     * 删除好友
     *
     * @param fromId
     * @param toId
     * @return int
     * @throws SQLException
     */
    @Delete({"DELETE FROM t_friend WHERE from_id = #{fromId} AND to_id = #{toId} OR from_id = #{toId} AND to_id = #{fromId} "})
    void deleteFriend(Long fromId, Long toId) throws SQLException;


    /**
     * 更改好友访问权限
     *
     * @param fromId
     * @param toId
     * @param collectionFlag
     * @throws SQLException
     */
    @Update({"UPDATE t_friend SET collection_flag = #{collectionFlag} WHERE from_id = #{fromId} AND to_id = #{toId} "})
    void updateCollectionFlag(Long fromId, Long toId, Integer collectionFlag) throws SQLException;

    /**
     * 查找出所有用户的id
     *
     * @return list<Long>
     * @throws SQLException
     */
    @Select({"SELECT id FROM t_user "})
    List<Long> selectAllId() throws SQLException;
}
