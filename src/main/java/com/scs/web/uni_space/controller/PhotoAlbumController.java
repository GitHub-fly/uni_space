package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.PhotoAlbumDto;
import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.entity.PhotoAlbum;
import com.scs.web.uni_space.service.PhotoAlbumService;
import com.scs.web.uni_space.service.serviceImpl.PhotoAlbumImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author suyuxi
 * @className PhotoAlbumController
 * @Description 相册控制层
 * @Date 2019/12/16
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/photoalbum")
@Api(value = "NoteController", tags = {"相册模块接口"})
public class PhotoAlbumController {

    @Resource PhotoAlbumService photoAlbumService;


    /**
     * 根据用户id查找用户所有相册信息
     * @param queryDto
     * @return Result
     */
    @ApiOperation(value = "根据用户id查找所有相册", notes = "data为相册信息列表")
    @PostMapping(value = "/all")
    Result findAllPhotoAlbum(@RequestBody QueryDto queryDto){
        return photoAlbumService.findAllPhotoAlbum(queryDto);
    }


    /**
     * 通过用户id，name创建相册
     * @param photoAlbumDto
     * @return Result
     */
    @ApiOperation(value = "通过用户id，name创建相册", notes = "msg成功，则添加成功")
    @PostMapping(value = "/apa")
    Result addPhotoAlbum(@RequestBody PhotoAlbumDto photoAlbumDto){
        return photoAlbumService.addPhotoAlbum(photoAlbumDto);
    }


    /**
     * 更改相册信息
     * @param photoAlbumDto
     * @return Result
     */
    @ApiOperation(value = "通过photoAlbumDto更改相册信息", notes = "msg成功，则修改成功")
    @PutMapping(value = "upa")
    Result updatePhotoAlbum(@RequestBody PhotoAlbumDto photoAlbumDto){
        return photoAlbumService.updatePhotoAlbum(photoAlbumDto);
    }


    /**
     * 通过用户id和相册id删除相册
     * @param photoAlbumDto
     * @return Result
     */
    @ApiOperation(value = "通过用户id和相册id删除相册", notes = "msg成功，则删除成功")
    @DeleteMapping(value = "dpa")
    Result deletePhotoAlbum(@RequestBody PhotoAlbumDto photoAlbumDto){
        return photoAlbumService.deletePhotoAlbum(photoAlbumDto);
    }


}
