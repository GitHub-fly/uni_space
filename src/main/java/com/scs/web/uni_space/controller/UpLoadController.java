package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.util.AliOssUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wl
 * @ClassNameUpDownController
 * @Description 图片上传
 * @Date 2019/12/4
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
@Api(value = "UpLoadController", tags = "文件上传接口")
public class UpLoadController {

    @RequestMapping(value = "/img", method = RequestMethod.POST)
    /**
     *单图上传
     */
    @ResponseBody
    Result uploadSingle(@RequestParam("file") MultipartFile[] sourceFiles) {
        List<String> url = AliOssUtil.upload(sourceFiles);
        return Result.success(url);
    }
}


