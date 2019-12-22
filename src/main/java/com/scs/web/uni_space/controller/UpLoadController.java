package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.util.FileUtil;
import com.scs.web.uni_space.util.ThreadPool;
import com.scs.web.uni_space.util.UploadTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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
    @Resource
    private UploadTask uploadTask;
    @Resource
    private ThreadPool threadPool;


    /**
     * 文件上传
     */
    @RequestMapping(value = "/img", method = RequestMethod.POST)
    @ApiOperation(value = "文件上传可以多文件", notes = "注意文件格式要为form/data")
    Result uploadSingle(@RequestParam("file") MultipartFile[] sourceFiles) {
        uploadTask.setFileList(FileUtil.getFiles(sourceFiles));
        return Result.success(threadPool.getResult());
    }
}


