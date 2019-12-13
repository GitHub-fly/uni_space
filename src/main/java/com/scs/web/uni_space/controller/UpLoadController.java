package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.util.AliOssUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wl
 * @ClassNameUpDownController
 * @Description TODO
 * @Date 2019/12/4
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class UpLoadController {

    @RequestMapping(value = "/img", method = RequestMethod.POST)

    /**
     *单图上传
     */
    @ResponseBody
    Result uploadSingle(@RequestParam("file") MultipartFile sourceFile) {
        String url = AliOssUtil.upload(sourceFile);
        return Result.success(url);
    }


}


