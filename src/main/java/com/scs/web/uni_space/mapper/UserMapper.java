package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.entity.*;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author wl
 * @ClassNameUserMapper
 * @Description 用户sql
 * @Date 2019/12/2
 * @Version 1.0
 */

public interface UserMapper {

    /***
     * @Description 通过手机查询用户
     * @param mobile
     * @return user
     * @throws SQLException
     */
    @Select({"SELECT*FROM t_user WHERE mobile = #{mobile}"})
    @Results(id = "user", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "account", column = "account"),
            @Result(property = "password", column = "password"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "email", column = "email"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "address", column = "address"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "constellation", column = "constellation"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "birthday", column = "birthday"),
    })
    User selectUserByMobile(String mobile) throws SQLException;

    /**
     * @param account
     * @return user
     * @throws SQLException
     * @Description 通过账户查找用户
     */
    @ResultMap("user")
    @Select({"SELECT*FROM t_user WHERE account = #{account}"})
    User selectUserByAccount(String account) throws SQLException;

    /**
     * 通过id查用户
     * @param id
     * @return
     * @throws SQLException
     */
    @ResultMap("user")
    @Select({"SELECT*FROM t_user WHERE id = #{id}"})
    User selectUserById(long id)throws SQLException;
    /**
     * @param email
     * @return user
     * @throws SQLException
     * @Description 通过email查找用户
     */
    @ResultMap("user")
    @Select({"SELECT*FROM t_user WHERE email = #{email}"})
    User selectUserByEmail(String email) throws SQLException;

    /**
     * 添加用户所用语句
     *
     * @param mobile
     * @param password
     * @param avatar
     * @param createTime
     * @param birthday
     * @return
     */
    @Insert({"INSERT INTO t_user (mobile,password,avatar,create_time,birthday) VALUES(#{mobile},#{password},#{avatar},#{createTime},#{birthday})"})
    int insertUser(String mobile, String password, String avatar, Timestamp createTime, Date birthday);


    /**
     * 根据id修改头像
     *
     * @param img
     * @param id
     * @return
     * @throws SQLException
     */
    @Update({"UPDATE t_user SET avatar=#{img} WHERE id = #{id}"})
    int updateUserAvatar(String img, Long id) throws SQLException;

    @Update({"UPDATE t_user SET password=#{password} WHERE mobile=#{mobile}"})
    int updateUserPassword(String mobile, String password) throws SQLException;

    /**
     * 更新用户资料
     *
     * @param user
     * @return
     * @throws SQLException
     */
    @Update({"UPDATE t_user SET nickname=#{nickname},address=#{address},gender=#{gender}," +
            "introduction=#{introduction},constellation=#{constellation},birthday=#{birthday}," +
            "avatar=#{avatar} WHERE id =#{id}"})
    int updateUserData(User user) throws SQLException;


    /**
     * 批量插入用户信息
     *
     * @param list
     */
    void batchInsertUser(List<User> list);

    /**
     * 批量添加好友信息
     *
     * @param friends
     */
    void batchInsertFriend(List<Friend> friends);

    /**
     * 批量添加点赞信息
     *
     * @param list
     */
    void batchInsertLike(List<Like> list);

    /**
     * 批量插入日志信息
     *
     * @param list
     */
    void batchInsertJournal(List<Journal> list);

    /**
     * 批量插入照片信息
     *
     * @param list
     */
    void batchInsertPhoto(List<Photo> list);

    /**
     * 批量插入相册信息
     *
     * @param list
     */
    void batchInsertPhotoAlbum(List<PhotoAlbum> list);


}