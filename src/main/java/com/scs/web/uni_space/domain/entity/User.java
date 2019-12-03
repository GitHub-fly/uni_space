package com.scs.web.uni_space.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * @author 小黑
 * @ClassNameUser
 * @Description 用户实体类
 * @Date 2019/12/2
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable {
    private long id;
    private String mobile;
    private String account;
    private String password;
    private String nickname;
    private String email;
    private String avatar;
    private String address;
    private String gender;
    private String introduction;
    private String constellation;
    private Timestamp createTime;
    private LocalDate birthday;

}
