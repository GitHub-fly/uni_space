package com.scs.web.uni_space;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest(classes = UniSpaceApplication.class)
class UniSpaceApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("user", "user1");
        String user = (String) redisTemplate.opsForValue().get("user");
        System.out.println(user);
    }

}