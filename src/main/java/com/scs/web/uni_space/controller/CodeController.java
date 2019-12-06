package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.service.RedisService;
import com.scs.web.uni_space.util.ImageUtil;
import com.scs.web.uni_space.util.Result;
import com.scs.web.uni_space.util.ResultCode;
import com.scs.web.uni_space.util.SMSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


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
    private Logger logger = LoggerFactory.getLogger(CodeController.class);
    @Resource
    private RedisService redisService;

    /**
     * @description  获取图形验证码
     * @return
     */
    @GetMapping(value = "/code")
    Result getCodeImage() {
        // 返回base64
        ImageUtil imageUtil = new ImageUtil();
        String base64String = imageUtil.getRandomCodeBase64();
        if (base64String != null) {
            //将图形验证码以String形式存入redis
            String code = "data:image/png;base64," + base64String;
            redisService.set("code", code);
            System.out.println(imageUtil.string.toLowerCase());
            //将图形验证码的具体内容传给前段，有前段判断
            return Result.success(imageUtil.string.toLowerCase());
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }

    /**
     * @description 短信验证码
     * @param mobile
     * @return
     */
    @PostMapping(value = "/sms")
    Result getCodeSMS(@RequestParam("mobile") String mobile) {
        //发送短信给手机
        String sms = SMSUtil.send(mobile);
        System.out.println(sms);
        boolean result;
        //将验证码存入redis
        result = redisService.set(mobile, sms);
        if (result = true) {
            return Result.success(sms);
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }
}
