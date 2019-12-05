package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.UniSpaceApplication;
import com.scs.web.uni_space.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = UniSpaceApplication.class)
class RedisServiceImplTest {
    @Resource
    private RedisServiceImpl redisServiceImpl;

    @Test
    void set() {
        User user = new User();
        user.setNickname("小王");
        boolean result = false;
        result = redisServiceImpl.set("user2", user);
        System.out.println(result);
    }

    @Test
    void getValue() {
        User user = new User();
        user = redisServiceImpl.getValue("user1", User.class);
        System.out.println(user);
    }

    @Test
    void removeKey() {
        redisServiceImpl.removeKey("user2");
    }

    @Test
    void existsKey() {
        System.out.println(redisServiceImpl.existsKey("user1"));
    }
}