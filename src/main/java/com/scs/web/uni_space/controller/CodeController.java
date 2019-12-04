package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.service.serviceImpl.RedisServiceImpl;
import com.scs.web.uni_space.util.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;

/**
 * @ClassName CodeController
 * @Description 验证码
 * @Author xiaobinggan
 * @Date 2019/12/3 9:59 上午
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/api")
public class CodeController {
    @Resource
    private RedisServiceImpl redisServiceImpl;

    @PostMapping(value = "/code")
    void getCodeImage() {
        //获取随机验证码
        String code = StringUtil.getRandomCode();
        boolean result;
        result = redisServiceImpl.set("code", code);
        BufferedImage img = ImageUtil.getImage(code, 200, 100);
    }

    @PostMapping(value = "/sms")
    Result getCodeSMS(@RequestParam("mobile") String mobile) {
        String sms = SMSUtil.send(mobile);
        System.out.println(sms);
        boolean result;
        result = redisServiceImpl.set(mobile, sms);
        if (result = true) {
            return Result.success(sms);
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }
}
