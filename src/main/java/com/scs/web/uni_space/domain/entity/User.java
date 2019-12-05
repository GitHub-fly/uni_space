package com.scs.web.uni_space.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * @author 小黑
 * @author suyuxi
 * @Description 用户实体类
 * @className User
 * @Description 用户
 * @Date 2019/12/2
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Long id;
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
    private Long skinId;
    private Timestamp createTime;
    private LocalDate birthday;

}
