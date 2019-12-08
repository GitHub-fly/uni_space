package com.scs.web.uni_space.util;

import com.aliyun.oss.OSSClient;


import com.aliyun.oss.model.ObjectMetadata;


import com.aliyun.oss.model.PutObjectResult;


import org.slf4j.Logger;


import org.slf4j.LoggerFactory;


import org.springframework.stereotype.Component;


import org.springframework.util.StringUtils;


import org.springframework.web.multipart.MultipartFile;


import java.io.*;


import java.net.URL;


import java.util.Date;


import java.util.Random;


/**
 * @ClassName OSSClientUtil
 * @Description 图片上传工具
 * @Author xiaobinggan
 * @Date 2019/12/5 9:27 上午
 * @Version 1.0
 **/


@Component


public class OSSClientUtil {
    // endpoint以杭州为例，其它region请按实际情况填写
    protected static String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    protected static String accessKeyId = "LTAI4FmPBAPTY2P2x9YFpV8h";
    protected static String accessKeySecret = "tNP7CUhbkEUlI8vTuqvFx30K3Ny6fU";
    protected static String bucketName = "space1821";
    //文件存储目录 阿里云服务器最好创建一个文件夹 专门放图片
    private String filedir = "UserAvatar/";
    private Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);
    private OSSClient ossClient;
    public OSSClientUtil() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }


    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */


    public static String getContentType(String FilenameExtension) {


        if (FilenameExtension.equalsIgnoreCase(".bmp")) {


            return "image/bmp";


        }


        if (FilenameExtension.equalsIgnoreCase(".gif")) {


            return "image/gif";


        }


        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||


                FilenameExtension.equalsIgnoreCase(".jpg") ||


                FilenameExtension.equalsIgnoreCase(".png")) {


            return "image/jpeg";


        }


        if (FilenameExtension.equalsIgnoreCase(".html")) {


            return "text/html";


        }


        if (FilenameExtension.equalsIgnoreCase(".txt")) {


            return "text/plain";


        }


        if (FilenameExtension.equalsIgnoreCase(".vsd")) {


            return "application/vnd.visio";


        }


        if (FilenameExtension.equalsIgnoreCase(".pptx") ||


                FilenameExtension.equalsIgnoreCase(".ppt")) {


            return "application/vnd.ms-powerpoint";


        }


        if (FilenameExtension.equalsIgnoreCase(".docx") ||


                FilenameExtension.equalsIgnoreCase(".doc")) {


            return "application/msword";


        }


        if (FilenameExtension.equalsIgnoreCase(".xml")) {


            return "text/xml";


        }


        return "image/jpeg";


    }


    /**
     * 初始化
     */


    public void init() {


        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);


    }


    /**
     * 销毁
     */


    public void destory() {


        ossClient.shutdown();


    }


    public void uploadImg2Oss(String url) throws Exception {


        File fileOnServer = new File(url);


        FileInputStream fin;


        try {


            fin = new FileInputStream(fileOnServer);


            String[] split = url.split("/");


            this.uploadFile2OSS(fin, split[split.length - 1]);


        } catch (FileNotFoundException e) {


            throw new Exception("图片上传失败01");


        }


    }


    public String uploadImg2Oss(MultipartFile file) throws Exception {


        if (file.getSize() > 1024 * 1024) {


            throw new Exception("上传图片大小不能超过1M！");


        }


        String originalFilename = file.getOriginalFilename();


        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();


        Random random = new Random();


        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;


        try {


            InputStream inputStream = file.getInputStream();


            this.uploadFile2OSS(inputStream, name);


            return name;


        } catch (Exception e) {


            throw new Exception("图片上传失败02");


        }


    }


    /**
     * 获得图片路径
     *
     * @param fileUrl
     * @return
     */


    public String getImgUrl(String fileUrl) {


        if (!StringUtils.isEmpty(fileUrl)) {


            String[] split = fileUrl.split("/");


            return this.getUrl(this.filedir + split[split.length - 1]);


        }


        return null;


    }


    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */


    public String uploadFile2OSS(InputStream instream, String fileName) {


        String ret = "";


        try {


            //创建上传Object的Metadata


            ObjectMetadata objectMetadata = new ObjectMetadata();


            objectMetadata.setContentLength(instream.available());


            objectMetadata.setCacheControl("no-cache");


            objectMetadata.setHeader("Pragma", "no-cache");


            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));


            objectMetadata.setContentDisposition("inline;filename=" + fileName);


            //上传文件


            PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);


            ret = putResult.getETag();


        } catch (IOException e) {


            logger.error(e.getMessage(), e);


        } finally {


            try {


                if (instream != null) {


                    instream.close();


                }


            } catch (IOException e) {


                e.printStackTrace();


            }


        }


        return ret;


    }


    /**
     * 获得url链接
     *
     * @param key
     * @return
     */


    public String getUrl(String key) {


        // 设置URL过期时间为10年  3600l* 1000*24*365*10


        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);


        // 生成URL


        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);


        if (url != null) {


            return url.toString();


        }


        return null;


    }


}



