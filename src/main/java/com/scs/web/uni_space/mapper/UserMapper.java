package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.dto.SignDto;
import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.*;
import com.scs.web.uni_space.domain.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
     * 通过手机查询用户
     *
     * @param mobile
     * @return user
     * @throws SQLException
     */
    @Select("SELECT * FROM t_user WHERE mobile = #{mobile}")
    User selectUserByMobile(String mobile) throws SQLException;

    @Select({"<script>",
            "SELECT * FROM t_user ",
            "WHERE 1 =1 ",
            "<when test ='signDto.mobile!=null'> ",
            "AND mobile=#{signDto.mobile} ",
            "</when> ",
            "<when test ='signDto.account!=null'> ",
            "AND account=#{signDto.account} ",
            "</when> ",
            "<when test ='signDto.email!=null'> ",
            "AND email=#{signDto.email} ",
            "</when> ",
            "<when test ='signDto.id!=null'> ",
            "AND id=#{signDto.id} ",
            "</when>",
            "</script>"
    })
    User findUserBy(@Param("signDto") SignDto signDto) throws SQLException;



    /**
     * 通过账户查找用户
     *
     * @param account
     * @return user
     * @throws SQLException
     */
    @Select("SELECT * FROM t_user WHERE account = #{account}")
    User selectUserByAccount(String account) throws SQLException;


    /**
     * 通过id查用户
     *
     * @param id
     * @return User
     * @throws SQLException
     */
    @Select("SELECT a.*, COUNT(b.user_id) AS num " +
            "FROM t_user a " +
            "LEFT JOIN t_journal b " +
            "ON a.id = b.user_id " +
            "WHERE a.id = #{id} ")
    UserVo selectUserById(long id) throws SQLException;


    /**
     * 通过email查找用户
     *
     * @param email
     * @return user
     * @throws SQLException
     */
    @Select({"SELECT * FROM t_user WHERE email = #{email}"})
    User selectUserByEmail(String email) throws SQLException;


    /**
     * 添加用户
     *
     * @param mobile
     * @param password
     * @param avatar
     * @param createTime
     * @param birthday
     * @return
     */
    @Insert({"INSERT INTO t_user (code,mobile,password,avatar,create_time,birthday) VALUES(#{code},#{mobile},#{password},#{avatar},#{createTime},#{birthday})"})
    void insertUser(String code, String mobile, String password, String avatar, Timestamp createTime, LocalDate birthday) throws SQLException;

    /**
     * 根据id修改头像
     *
     * @param img
     * @param id
     * @return
     * @throws SQLException
     */
    @Update({"UPDATE t_user SET avatar=#{img} WHERE id = #{id}"})
    void updateUserAvatar(String img, Long id) throws SQLException;










    @Select("SELECT * FROM t_user WHERE mobile =#{userDto.keywords}")

    User selectUserByMobile1(@Param("userDto") UserDto userDto) throws SQLException;

    /**
     * 修改密码
     *
     * @param mobile
     * @param password
     * @return
     * @throws SQLException
     */
    @Update({"UPDATE t_user SET password = #{password} WHERE mobile=#{mobile}"})
    void updateUserPassword(String mobile, String password) throws SQLException;

    /**
     * 更新用户资料
     *
     * @param userDto
     * @return
     * @throws SQLException
     */
    @Update({"UPDATE t_user SET nickname=#{nickname},address=#{address},gender=#{gender}," +
            "introduction=#{introduction},constellation=#{constellation},birthday=#{birthday}" +

            "WHERE id =#{id}"})
    void updateUserData(UserDto userDto) throws SQLException;



    /**
     * 显示个人信息和统计好友，日志，相册，相片总数
     * @param id
     * @return UserVo
     * @throws SQLException
     */
    @Select({"SELECT d.*, a.journalSum,b.photoAlbumSum,c.photoSum " +
            "FROM( " +
            "(SELECT COUNT(f.user_id) AS journalSum FROM t_journal f " +
            "WHERE f.user_id = #{id} " +
            ")a, " +
            "(SELECT COUNT(d.user_id) AS photoAlbumSum " +
            "FROM t_photo_album d " +
            "WHERE d.user_id = #{id} " +
            ")b, " +
            "(SELECT  COUNT(e.id) AS photoSum " +
            "FROM t_photo_album d " +
            "LEFT JOIN t_photo e " +
            "ON d.id=e.album_id " +
            "WHERE d.user_id = #{id} " +
            ")c, " +
            "(SELECT e.*, COUNT(d.from_id) AS friendSum " +
            "FROM t_friend d " +
            "LEFT JOIN t_user e " +
            "ON d.from_id=e.id AND d.friend_flag = 1 " +
            "WHERE e.id = #{id} " +
            ")d " +
            ") "})
     UserVo selectSum (Long id) throws SQLException;


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
     * @param list
     */
    void batchInsertPhotoAlbum(List<PhotoAlbum> list);


}