package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.service.UpDownService;
import com.scs.web.uni_space.util.OSSClientUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author wl
 * @ClassNameUpDownServiceImpl
 * @Description TODO
 * @Date 2019/12/4
 * @Version 1.0
 */
@Service
public class UpDownServiceImpl implements UpDownService {
    @Resource
    private OSSClientUtil ossClient=new OSSClientUtil();
    @Override
    public String updateHead(MultipartFile file, long userId) throws Exception {
        if (file == null || file.getSize() <= 0) {
            throw new Exception("头像不能为空");
        }
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        System.out.println(imgUrl);
        //userDao.updateHead(userId, imgUrl);//只是本地上传使用的
        return imgUrl;
    }

}
