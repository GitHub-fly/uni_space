package com.scs.web.uni_space;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

<<<<<<< HEAD
=======
import javax.annotation.Resource;

>>>>>>> 5430bd32d9a5c476865ecf5f542444dd81630fbf
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
