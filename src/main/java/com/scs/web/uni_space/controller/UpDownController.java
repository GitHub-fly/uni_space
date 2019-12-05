package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.service.UpDownService;
import com.scs.web.uni_space.util.OSSClientUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wl
 * @ClassNameUpDownController
 * @Description 文件上传
 * @Date 2019/12/4
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class UpDownController {

    @Resource
    private UpDownService upDownService;
    @Resource
    private OSSClientUtil ossClientUtil;

    //处理文件上传
    @RequestMapping(value = "/testuploadimg", method = RequestMethod.POST)
    @ResponseBody
    /*public  String uploadImg(@RequestParam("file") MultipartFile file,
                             HttpServletRequest request) {*/
    public Map<String, Object> headImgUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("success", true);
        value.put("errorCode", 0);
        value.put("errorMsg", "");
        try {
            String head = upDownService.updateHead(file, 4);//此处是调用上传服务接口，4是需要更新的userId 测试数据。
            value.put("data", head);

        } catch (IOException e) {
            e.printStackTrace();
            value.put("success", false);
            value.put("errorCode", 200);
            value.put("errorMsg", "图片上传失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}


