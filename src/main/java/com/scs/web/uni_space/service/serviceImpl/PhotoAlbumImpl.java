package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import com.scs.web.uni_space.domain.dto.PhotoAlbumDto;
import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.entity.PhotoAlbum;
import com.scs.web.uni_space.domain.vo.PhotoAlbumVo;
import com.scs.web.uni_space.mapper.PhotoAlbumMapper;
import com.scs.web.uni_space.service.PhotoAlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author suyuxi
 * @className PhotoAlbumImpl
 * @Description 相册服务类
 * @Date 2019/12/16
 * @Version 1.0
 **/

@Service
@Slf4j
public class PhotoAlbumImpl implements PhotoAlbumService {

    @Resource
    private PhotoAlbumMapper photoAlbumMapper;


    /**
     * 查找所有相册
     * @param queryDto
     * @return Result
     */
    @Override
    public Result findAllPhotoAlbum(QueryDto queryDto) {
        //数据非空判断
        if (queryDto.getId() != null){
            try {
                //调用查找方法，返回photoAlbumList集合
                List<PhotoAlbumVo> photoAlbumVoList = photoAlbumMapper.findAllPhotoAlbum(queryDto.getId());
                //判断集合是否有数据
                if (photoAlbumVoList.size() != 0){
                    return Result.success(photoAlbumVoList);
                }else {
                    return Result.failure(ResultCode.USER_NOT_PHOTO_ALBUM);
                }
            } catch (SQLException e) {
                log.error("查找相册异常");
                return Result.failure(ResultCode.USER_FIND_PHOTO_ALBUM_ERROR);
            }
        }
        //数据为空返回错误
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    /**
     * 添加相册
     * @param photoAlbumDto
     * @return Result
     */
    @Override
    public Result addPhotoAlbum(PhotoAlbumDto photoAlbumDto) {
        //生成当前时间
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        //前端返回数据非空判断
        if (photoAlbumDto.getUserId() != null && photoAlbumDto.getName() != null ){
            try {
                //调用插入方法
                photoAlbumMapper.addAllPhotoAlbum(photoAlbumDto.getUserId(),photoAlbumDto.getName(),timestamp);
                //成功返回result
                return Result.success(ResultCode.SUCCESS);
            } catch (SQLException e) {
                log.error("添加相册异常");
                return Result.failure(ResultCode.USER_ADD_PHOTO_ALBUM_ERROR);
            }
        }
        //数据为空返回错误
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    /**
     * 修改相册
     * @param photoAlbumDto
     * @return Result
     */
    @Override
    public Result updatePhotoAlbum(PhotoAlbumDto photoAlbumDto) {
        //前端返回数据非空判断
        if (photoAlbumDto.getId() != null && photoAlbumDto.getUserId() != null && photoAlbumDto.getCover() != null && photoAlbumDto.getName() != null && photoAlbumDto.getType() != null && photoAlbumDto.getIntroduction() != null){
            try {
                //调用更改信息方法
                photoAlbumMapper.updateAllPhotoAlbum(photoAlbumDto.getId(),photoAlbumDto.getUserId(),photoAlbumDto.getCover(),photoAlbumDto.getName(),photoAlbumDto.getType(),photoAlbumDto.getIntroduction());
                return Result.success(ResultCode.SUCCESS);
            } catch (SQLException e) {
                log.error("修改相册信息异常");
                return Result.failure(ResultCode.USER_UPDATE_PHOTO_ALBUM_ERROR);
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }

    @Override
    public Result deletePhotoAlbum(PhotoAlbumDto photoAlbumDto) {
        //非空判断
        if (photoAlbumDto.getUserId() != null && photoAlbumDto.getId() != null){
            try {
                //调用删除方法
                photoAlbumMapper.deleteAllPhotoAlbum(photoAlbumDto.getUserId(),photoAlbumDto.getId());
                return Result.success(ResultCode.SUCCESS);
            } catch (SQLException e) {
                log.error("删除相册异常");
                return Result.failure(ResultCode.USER_DELETE_PHOTO_ALBUM_ERROR);
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


}
