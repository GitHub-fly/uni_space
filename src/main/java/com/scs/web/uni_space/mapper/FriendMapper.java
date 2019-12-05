package com.scs.web.uni_space.mapper;


import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.domain.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 22968
 */
public interface FriendMapper {



    /**
     * 查找好友表所有数据
     * @param
     * @return list
     */
    @Results(id = "friend", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "fromId", column = "from_id"),
            @Result(property = "toId", column = "to_id"),
            @Result(property = "friendFlag", column = "friend_flag"),
            @Result(property = "collectionFlag", column = "collection_flag"),
    })
    @Select({"SELECT * FROM t_friend"})
    List<Friend> selectAll();

    /**
     * 通过from_id查找所有好友信息
     * @param fromId
     * @return list
     */
    @Select({"SELECT * FROM t_user WHERE id IN ( SELECT to_id FROM t_friend WHERE friend_flag = 1 AND from_id = #{fromId})"})
    List<User> selectByFromId(Long fromId);

    /**
     * 通过to_id查找所有请求者信息
     * @param toId
     * @return List
     */
    @Select({"SELECT * FROM t_user WHERE id IN  ( SELECT from_id FROM t_friend WHERE friend_flag = 0 AND to_id = #{toId})"})
    List<User> selectByToId(Long toId);


    /**
     * 查看对方是否为好友
     * @param fromId
     * @param toId
     * @return Friend
     */
    @Select({"SELECT * FROM t_friend WHERE from_id = #{fromId} AND to_id = #{toId}"})
    Friend selectFriendFlag(Long fromId,Long toId);


    /**
     * 添加好友，状态为0
     * @param fromId
     * @param  toId
     * @return int
     */
    @Insert({"INSERT INTO t_friend (from_id,to_id ,friend_flag) VALUES (#{fromId},#{toId},0)"})
    int insertOther(Long fromId,Long toId);


    /**
     * 更改好友状态，0变1
     * @param fromId
     * @param toId
     * @return
     */
    @Update({"UPDATE t_friend SET friend_flag = 1 WHERE from_id = #{fromId} AND to_id = #{toId}"})
    int updateFriendFlag(Long fromId,Long toId);


    /**
     * 添加好友，状态为1
     * @param fromId
     * @param toId
     * @return int
     */
    @Insert({"INSERT INTO t_friend (from_id,to_id ,friend_flag) VALUES (#{toId},#{fromId},1)"})
    int insertEachOther(Long fromId,Long toId);

    /**
     * 删除请求
     * @param fromId
     * @param toId
     * @return int
     */
    @Delete({"DELETE FROM t_friend WHERE from_id = #{fromId} AND to_id = #{toId}"})
    int deleteReject(Long fromId,Long toId);


    /**
     * 删除好友
     * @param fromId
     * @param toId
     * @return int
     */
    @Delete({"DELETE FROM t_friend WHERE from_id = #{fromId} AND to_id = #{toId} OR from_id = #{toId} AND to_id = #{fromId}"})
    int deleteFriend(Long fromId,Long toId);

}
