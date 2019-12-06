package com.scs.web.uni_space.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.File;
import java.util.UUID;

/**
 * 阿里云OOS上传文件工具类
 *
 * @author mqxu
 */
public class AliOssUtil {
    public static String upload(File file) {
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAI4FqFoaZ1cHStok8RfGK5";
        String accessKeySecret = "xz1hFNQiY0RAf9oOfMo9neajw0j6aD";
        String bucketName = "niit-soft";
        String filePath = "avatar/";
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

    public static void main(String[] args) {
        String url = AliOssUtil.upload(new File("/Users/xiaobinggan/Pictures/下载.jpeg"));
        System.out.println(url);
    }
}
