package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.entity.User;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;

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
     * @param email
     * @return user
     * @throws SQLException
     * @Description 通过email查找用户
     */
    @ResultMap("user")
    @Select({"SELECT*FROM t_user WHERE email = #{email}"})
    User selectUserByEmail(String email) throws SQLException;

    /**
     * @param mobile
     * @param password
     * @return int
     * @Description 添加用户所用语句
     */
    @Insert({"INSERT INTO t_user (mobile,password) VALUES(#{mobile},#{password})"})
    int insertUser(String mobile, String password);


    /**
     * @param user
     * @return int
     * @throws SQLException
     * @Description 更新用户资料
     */
    @Update({"UPDATE t_user SET nickname=#{nickname},address=#{address},gender=#{gender}," +
            "introduction=#{introduction},constellation=#{constellation},birthday=#{birthday} WHERE id =#{id}"})
    int updateUserData(User user) throws SQLException;
}
