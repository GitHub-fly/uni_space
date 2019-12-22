package com.scs.web.uni_space.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @ClassName UploadTask
 * @Description 上传文件线程工作
 * @Author xiaobinggan
 * @Date 2019/12/22 2:23 下午
 * @Version 1.0
 **/
@Data
@Component
public class UploadTask implements Callable<List<String>> {
    private List<File> fileList;
    private List<String> tempFiles;

    @Override
    public List<String> call() {
        tempFiles = new ArrayList<>();
        fileList.forEach(System.out::println);
        for (File file : fileList) {
            //调用单文件上传
            String url = AliOssUtil.upload(file);
            //添加到临时文件数组中
            tempFiles.add(url);
        }
        return tempFiles;
    }
}
