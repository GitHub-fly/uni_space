package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import com.scs.web.uni_space.domain.dto.CodeDto;
import com.scs.web.uni_space.service.RedisService;
import com.scs.web.uni_space.util.ImageUtil;
import com.scs.web.uni_space.util.SMSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "CodeController", tags = "验证码接口")
public class CodeController {
    @Resource
    private RedisService redisService;

    /**
     * @return
     * @description 获取图形验证码
     */
    @ApiOperation(value = "获取图形验证码", notes = "返回值为四位随机字符")
    @GetMapping(value = "/code")
    Result getCodeImage() {
        // 返回base64
        ImageUtil imageUtil = new ImageUtil();
        String base64String = imageUtil.getRandomCodeBase64();
        if (base64String != null) {
            CodeDto codeDto = new CodeDto();
            //将图形验证码以String形式存入redis
            String img = "data:image/png;base64," + base64String;
            codeDto.setImg(img);
            System.out.println(imageUtil.string.toLowerCase());
            codeDto.setCode(imageUtil.string.toLowerCase());
            //将图形验证码的具体内容传给前段，有前段判断
            return Result.success(codeDto);
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }

    /**
     * @param mobile
     * @return
     * @description 短信验证码
     */
    @ApiOperation(value = "获取短信验证码", notes = "输入值为手机号 返回值为6位数字验证码")
    @PostMapping(value = "/sms")
    Result getCodeSMS(@RequestParam("mobile") String mobile) {
        //发送短信给手机
        String sms = SMSUtil.send(mobile);
        System.out.println(sms);
        boolean result;
        //将验证码存入redis，时效为一分钟
        result = redisService.set(mobile, sms, 1L);
        if (result = true) {
            return Result.success(sms);
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }
}
