package com.scs.web.uni_space.service;


import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.PhotoAlbumDto;
import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.entity.PhotoAlbum;

/**
 * @author suyuxi
 * @className PhotoAlbumService
 * @Description 相册服务类
 * @Date 2019/12/16
 * @Version 1.0
 **/

public interface PhotoAlbumService {


    /**
     * 查找所有相册
     *
     * @param queryDto
     * @return Result
     */
    Result findAllPhotoAlbum(QueryDto queryDto);


    /**
     * 通过相册id查找相册信息
     *
     * @param queryDto
     * @return
     */
    Result findPhotoAlbumById(QueryDto queryDto);


    /**
     * 添加相册
     *
     * @param photoAlbumDto
     * @return Result
     */
    Result addPhotoAlbum(PhotoAlbumDto photoAlbumDto);


    /**
     * 修改相册信息
     * @param photoAlbumDto
     * @return Result
     */
    Result updatePhotoAlbum(PhotoAlbumDto photoAlbumDto);


    /**
     * 删除相册
     * @param photoAlbumDto
     * @return Result
     */
    Result deletePhotoAlbum(PhotoAlbumDto photoAlbumDto);
}
