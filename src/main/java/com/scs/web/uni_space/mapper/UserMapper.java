package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.entity.User;
import org.apache.ibatis.annotations.*;


/**
 * @author 小黑
 * @ClassNameUserMapper
 * @Description TODO
 * @Date 2019/12/2
 * @Version 1.0
 */

public interface UserMapper {





    /**
     * @param mobile
     * @return User
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
    User selectUserByMobile(String mobile);


    @ResultMap("user")
    @Select({"SELECT*FROM t_user WHERE account = #{accout}"})
    User selectUserByAccount(String account);

    @ResultMap("user")
    @Select({"SELECT*FROM t_user WHERE email = #{email}"})
    User selectUserByEmail(String email);


    @Insert({"INSERT INTO t_user (mobile,password) VALUES(#{mobile},#{password})"})
    int insertUser(String mobile, String password);


    @Update({"UPDATE t_user SET avatar=#{avatar} WHERE id=#{id}"})
    int updateUserAvatar(String avatar, Long id);
}
