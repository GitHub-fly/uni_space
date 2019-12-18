package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.*;
import com.scs.web.uni_space.domain.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;


/**
 * @author wl
 * @ClassNameUserMapper
 * @Description 用户sql
 * @Date 2019/12/2
 * @Version 1.0
 */

public interface UserMapper {

    /**
     * 查询用户
     *
     * @param queryDto
     * @return user
     * @throws SQLException
     */
    @Select("SELECT * FROM t_user WHERE id = #{queryDto.id} " +
            "OR  mobile = #{queryDto.equalsString} " +
            "OR  account = #{queryDto.equalsString} " +
            "OR  email = #{queryDto.equalsString} ")
    UserVo selectUserBy(@Param("queryDto") QueryDto queryDto) throws SQLException;

    /**
     * 通过id查用户的日志总量及其信息
     *
     * @param id
     * @return User
     * @throws SQLException
     */
    @Select("SELECT a.*, COUNT(b.user_id) AS journalSum " +
            "FROM t_user a " +
            "LEFT JOIN t_journal b " +
            "ON a.id = b.user_id " +
            "WHERE a.id = #{id} ")
    UserVo selectUserById(Long id) throws SQLException;

    /**
     * 根据id修改头像
     *
     * @param userDto
     * @return
     * @throws SQLException
     */
    @Update({"UPDATE t_user SET avatar=#{userDto.avatar} WHERE id = #{userDto.id}"})
    void updateUserAvatar(@Param("userDto") UserDto userDto) throws SQLException;

    /**
     * 更新用户资料
     *
     * @param userDto
     * @return
     * @throws SQLException
     */
    @Update({
            "<script>",
            "UPDATE t_user",
            "<set>",
            "<if test = 'userDto.nickname != null'>",
            "nickname = #{userDto.nickname}, ",
            "</if>",
            "<if test = 'userDto.address != null'>",
            "address = #{userDto.address}, ",
            "</if>",
            "<if test = 'userDto.gender != null'>",
            "gender = #{userDto.gender}, ",
            "</if>",
            "<if test = 'userDto.introduction != null'>",
            "introduction = #{userDto.introduction}, ",
            "</if>",
            "<if test = 'userDto.constellation != null'>",
            "constellation = #{userDto.constellation}, ",
            "</if>",
            "<if test = 'userDto.birthday != null'>",
            "birthday = #{userDto.birthday}, ",
            "</if>",
            "</set>",
            "where id = #{userDto.id}",
            "</script>"
            })
    void updateUserData(@Param("userDto") UserDto userDto) throws SQLException;

    /**
     * 通过手机号短信验证修改密码
     *
     * @param userDto
     * @return
     * @throws SQLException
     */
    @Update({"UPDATE t_user SET password = #{userDto.password} WHERE mobile=#{userDto.mobile}"})
    void updateUserPassword(@Param("userDto") UserDto userDto) throws SQLException;

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Insert({"INSERT INTO t_user (code,mobile,password,avatar,create_time,birthday) VALUES(#{user.code},#{user.mobile},#{user.password},#{user.avatar},#{user.createTime},#{user.birthday})"})
    void insertUser(@Param("user") User user) throws SQLException;

    /**
     * 显示个人信息和统计好友，日志，相册，相片总数
     *
     * @param id
     * @return UserVo
     * @throws SQLException
     */
    @Select({"SELECT d.*, a.journal_sum,b.photo_album_sum,c.photo_sum " +
            "FROM( " +
            "(SELECT COUNT(f.user_id)AS journal_sum " +
            "FROM t_journal f " +
            "WHERE f.user_id = 4 " +
            ")a, " +
            "(SELECT COUNT(d.user_id) AS photo_album_sum " +
            "FROM t_photo_album d " +
            "WHERE d.user_id = 4 " +
            ")b, " +
            "(SELECT  COUNT(e.id)AS photo_sum " +
            "FROM t_photo_album d " +
            "LEFT JOIN t_photo e " +
            "ON d.id=e.album_id " +
            "WHERE d.user_id = 4 " +
            ")c, " +
            "(SELECT e.*, COUNT(d.from_id) AS friend_sum " +
            "FROM t_friend d " +
            "LEFT JOIN t_user e " +
            "ON d.from_id=e.id AND d.friend_flag=1 " +
            "WHERE e.id = 4 " +
            ")d " +
            ") "})
    UserVo selectSum(Long id) throws SQLException;


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