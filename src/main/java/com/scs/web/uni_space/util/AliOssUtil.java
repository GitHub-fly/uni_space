package com.scs.web.uni_space.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 阿里云OOS上传文件工具类
 *
 * @author mqxu
 */
@Slf4j
public class AliOssUtil {

    public static String upload(MultipartFile[] sourceFile) {
        for (int i=0 ;i<sourceFile.length;i++) {
            // 获取文件名
            String fileName = sourceFile[i].getOriginalFilename();
            //uuid生成主文件名
            String prefix = UUID.randomUUID().toString();
            assert fileName != null;
            //源文件的扩展名
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            //创建File类型的临时文件
            File tempFile = null;
            try {
                tempFile = File.createTempFile(prefix, suffix);
                // 将MultipartFile转换成File
                sourceFile[i].transferTo(tempFile);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            assert tempFile != null;
            return upload(tempFile);
        }
        return sourceFile.toString();
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

    public static void main(String[] args) {
        String url = AliOssUtil.upload(new File("E:\\图库\\轮滑社团日活动照片\\十月图片2.jpg"));
        System.out.println(url);
    }
}
