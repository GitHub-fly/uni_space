package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.UniSpaceApplication;
import com.scs.web.uni_space.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resource;
import java.sql.SQLException;

@SpringBootTest(classes = UniSpaceApplication.class)
class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    void selectUserByMobile() throws SQLException {
        User user = userMapper.selectUserByMobile("13222222222");
        System.out.println(user);
    }

    @Test
    void selectUserByAccount() {
    }

    @Test
    void selectUserByEmail() {
    }

    @Test
    void insertUser() {
    }

    @Test
    void updateUserData() {
    }
}