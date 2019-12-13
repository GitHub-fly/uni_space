package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.service.serviceImpl.UpDownServiceImpl;
import com.scs.web.uni_space.util.AliOssUtil;
import com.scs.web.uni_space.util.OSSClientUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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

    @Resource
    private UpDownServiceImpl upDownServiceImpl;
    @Resource
    private OSSClientUtil ossClientUtil;

    @RequestMapping(value="/img", method = RequestMethod.POST)
    @ResponseBody
    Result uploadSingle(@RequestParam("file") MultipartFile[] sourceFile) {
        String url = AliOssUtil.upload(sourceFile);
        return Result.success(url);
    }








}


