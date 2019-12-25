package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import com.scs.web.uni_space.domain.dto.PhotoDto;
import com.scs.web.uni_space.domain.entity.Photo;
import com.scs.web.uni_space.mapper.PhotoMapper;
import com.scs.web.uni_space.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author suyuxi
 * @className PhotoImpl
 * @Description 照片服务类
 * @Date 2019/12/17
 * @Version 1.0
 **/

@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {

    @Resource PhotoMapper photoMapper;


//    @Resource CommonMapper commonMapper;
    /**
     * 查找某个相册所以照片
     *
     * @param photoDto
     * @return Result
     */
    @Override
    public Result findAllPhoto(PhotoDto photoDto) {
        //非空判断
        if (photoDto.getAlbumId() != null){
            try {
                //调用查找方法，参数为相册id
                List<Photo> photoList = photoMapper.findAll(photoDto.getAlbumId());
                return Result.success(photoList);
            } catch (SQLException e) {
                log.error("查找所有相册异常");
                return Result.failure(ResultCode.PHOTO_FIND_ALL_ERROR);
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    /**
     * pc批量插入照片
     *
     * @param photos
     * @return Result
     */
    @Override
    public Result batchAddPhoto(Photo[] photos){
        //获取当前时间
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        if (photos.length != 0){
            for (int i = 0; i<photos.length; i++){
                //给每张图片添加时间
                photos[i].setCreateTime(timestamp);            }
        }
        try {
//            commonMapper.returnId("t_photo");
            //调用批量插入方法，把数组装换为集合
            photoMapper.batchPcInsertPhoto(Arrays.asList(photos));
            return Result.success(ResultCode.SUCCESS);
        } catch (SQLException e) {
            log.error("批量出入出现异常");
        }

        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    /**
     * 移动端批量插入
     *
     * @param photoDto
     * @return
     */
    @Override
    public Result addPhoto(PhotoDto photoDto) {
        if (photoDto.getAlbumId() != null && photoDto.getUrlStrings().length != 0){
            //获取当前时间
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            //实例化一个photo类型的集合
            List<Photo> photoList = new ArrayList<>();
            for (int i = 0; i<photoDto.getUrlStrings().length; i++){
                //实例化一个photo对象
                Photo photo = new Photo();
                //把前面获取的数据组成一个对象，放入集合
                photo.setUrl(photoDto.getUrlStrings()[i]);
                photo.setCreateTime(timestamp);
                photoList.add(photo);
            }
            try {
//                commonMapper.returnId("t_photo");
                //调用批量插入方法
                photoMapper.batchInsertPhoto(photoList,photoDto.getAlbumId());
                return Result.success(ResultCode.SUCCESS);
            } catch (SQLException e) {
                log.error("移动端批量插入照片异常");
                return Result.failure(ResultCode.PHOTO_BATCH_ADD_ERROR);
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    /**
     * 批量删除照片
     *
     * @param photoDto
     * @return Result
     */
    @Override
    public Result batchDeletePhoto(PhotoDto photoDto) {
        if (photoDto.getLongList().size() != 0){
            try {
                //获取相片id数组，调用批量删除方法
                photoMapper.batchDeletePhoto(photoDto.getLongList());
                return Result.success(ResultCode.SUCCESS);
            } catch (SQLException e) {
                log.error("批量删除异常");
                return Result.failure(ResultCode.PHOTO_BATCH_DELETE_ERROR);
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


}
