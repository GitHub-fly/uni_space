package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.util.AliOssUtil;
import com.scs.web.uni_space.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author wl
 * @ClassNameUpDownController
 * @Description 文件上传
 * @Date 2019/12/4
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class UploadController {
    /**
     * 上传头像
     *
     * @param img
     * @return
     */
    @PostMapping(value = "/avatar")
    Result unloadAvatar(@RequestParam("img") String img) {
        return Result.success(AliOssUtil.upload(new File(img)));
    }
}


