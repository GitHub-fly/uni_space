package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.util.AliOssUtil;
import com.scs.web.uni_space.util.Result;
import com.scs.web.uni_space.util.ResultCode;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author wl
 * @ClassNameUpDownController
 * @Description 文件上传
 * @Date 2019/12/4
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class UploadController {
    /**
     * 上传头像
     *
     * @param img
     * @return
     */
    @PostMapping(value = "/avatar")
    Result unloadAvatar(@RequestParam("img") String img) {
        return Result.success(AliOssUtil.upload(new File(img)));
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload( MultipartFile file, HttpServletRequest request) throws IOException {
//        File file = new File(uploadFile)
//        String path = session.getServletContext().getRealPath("");
        String path = request.getSession().getServletContext().getRealPath("");
        File f = new File(ResourceUtils.getURL(path).getPath());
        return Result.success(path);
    }
}


