package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.UniSpaceApplication;
import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.util.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = UniSpaceApplication.class)
class UserServiceImplTest {

    @Resource
    private UserServiceImpl userServiceImpl;

    @Test
    void signIn() {
        UserDto userDto = new UserDto();
        userDto.setName("18094246920");
        userDto.setPassword("1");
        Result result = userServiceImpl.signIn(userDto);
        System.out.println(result);
    }
}