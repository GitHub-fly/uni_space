package com.scs.web.uni_space.service;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.PhotoDto;
import com.scs.web.uni_space.domain.entity.Photo;


/**
 * @author 22968
 */
public interface PhotoService {


    /**
     * 查找所有照片
     * @param photoDto
     * @return Result
     */
    Result findAllPhoto(PhotoDto photoDto);



    /**
     * pc批量添加
     * @param photos
     * @return Result
     */
    Result batchAddPhoto(Photo[] photos);


    /**
     * 移动端批量添加
     *
     * @param photoDto
     * @return
     */
    Result addPhoto(PhotoDto photoDto);


    /**
     * 删除照片
     *
     * @param photoDto
     * @return Result
     */
    Result batchDeletePhoto(PhotoDto photoDto);
}
