package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.PhotoDto;
import com.scs.web.uni_space.domain.entity.Photo;
import com.scs.web.uni_space.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author suyuxi
 * @className PhotoController
 * @Description 照片控制层
 * @Date 2019/12/17
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/photo")
@Api(value = "PhotoController", tags = {"照片模块接口"})
public class PhotoController {

    @Resource PhotoService photoService;


    @ApiOperation(value = "根据相册id查找该相册所有照片", notes = "data为照片信息列表")
    @PostMapping(value = "/all")
    Result findAllPhoto(@RequestBody PhotoDto photoDto){
        return photoService.findAllPhoto(photoDto);
    }



    @ApiOperation(value = "移动端通过相册id和url数组批量插入图片", notes = "传递参数为albumId , urlStrings[] , msg成功,则删除成功")
    @PostMapping(value = "/add")
    Result addPhoto(@RequestBody PhotoDto photoDto){
        return photoService.addPhoto(photoDto);
    }


    @ApiOperation(value = "PC端批量插入照片,相册id，URL不能为空", notes = "传递参数为photo数组 ， msg成功,则删除成功")
    @PostMapping(value = "/batch")
    Result batchAddPhoto(@RequestBody Photo[] photos){
        return photoService.batchAddPhoto(photos);
    }

    @ApiOperation(value = "通过list<Long> (照片id集合)删除照片", notes = "msg成功,则删除成功")
    @DeleteMapping(value = "/del")
    Result batchDelete(@RequestBody PhotoDto photoDto){
        return photoService.batchDeletePhoto(photoDto);
    }
}
