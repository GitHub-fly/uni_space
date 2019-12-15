package com.scs.web.uni_space.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 阿里云OOS上传文件工具类
 *
 * @author mqxu
 */
@Slf4j
public class AliOssUtil {

    public static List<String> upload(MultipartFile[] sourceFiles) {
        List<String> tempFiles = tempFiles = new ArrayList<>(10);
        for (MultipartFile sourceFile : sourceFiles) {
            System.out.println(sourceFile);
            // 获取文件名
            String fileName = sourceFile.getOriginalFilename();
            //uuid生成主文件名
            String prefix = UUID.randomUUID().toString();
            assert fileName != null;
            //源文件的扩展名
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            //创建File类型的临时文件
            File tempFile;
            try {
                tempFile = File.createTempFile(prefix, suffix);
                // 将MultipartFile转换成File
                sourceFile.transferTo(tempFile);
                tempFiles.add(upload(tempFile));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return tempFiles;
    }


    public static String upload(File file) {
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAI4FqFoaZ1cHStok8RfGK5";
        String accessKeySecret = "xz1hFNQiY0RAf9oOfMo9neajw0j6aD";
        String bucketName = "niit-soft";
        String filePath = "soft1821/";
        String fileName = file.getName();
        String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.indexOf("."));
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件到指定位置，并使用UUID更名
        ossClient.putObject(bucketName, filePath + newFileName, file);
        // 拼接URL
        String url = "https://niit-soft.oss-cn-hangzhou.aliyuncs.com/" + filePath + newFileName;
        ossClient.shutdown();
        return url;
    }
}
