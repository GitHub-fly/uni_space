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
     * 添加好友，状态为0
     * @param fromId
     * @param  toId
     * @return int
     */
    @Insert({"INSERT INTO t_friend (from_id,to_id ,friend_flag) VALUES (#{fromId},#{toId},0)"})
    int insertOther(Long fromId,Long toId);


    /**
     * 添加好友，状态为1
     * @param fromId
     * @param toId
     * @return int
     */
    @Insert({"INSERT INTO t_friend (from_id,to_id ,friend_flag) VALUES (#{fromId},#{toId},1)"})
    int insertEachOther(Long fromId,Long toId);


}
