package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.dto.PhotoAlbumDto;
import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.entity.PhotoAlbum;
import com.scs.web.uni_space.domain.vo.PhotoAlbumVo;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @author suyuxi
 */
public interface PhotoAlbumMapper {

    /**
     * 通过用户id查找该用户所有相册
     * @param userId
     * @return List<PhotoAlbum>
     * @throws SQLException
     */
    @Select({"SELECT a.*  ,c.url ,b.id AS security_id " +
            "FROM t_photo_album a " +
            "LEFT JOIN t_security b " +
            "ON a.id = b.photo_album_id " +
            "LEFT JOIN t_photo c " +
            "ON a.id = c.album_id " +
            "WHERE a.user_id = #{userId} " +
            "GROUP BY a.id HAVING COUNT(a.id) >= 1 ORDER BY (c.create_time) DESC"})
    List<PhotoAlbumVo> findAllPhotoAlbum(Long userId) throws SQLException;

//    @Select({"SELECT a.* ,b.id AS security_id " +
//            "FROM t_photo_album a " +
//            "LEFT JOIN t_security b " +
//            "ON a.id = b.photo_album_id " +
//            "WHERE a.user_id = #{userId} "})
//    List<PhotoAlbumVo> findAllPhotoAlbum(Long userId) throws SQLException;

    /**
     * 通过相册id查找相册信息
     *
     * @param queryDto
     * @return
     * @throws SQLException
     */
    @Select({"SELECT * FROM t_photo_album WHERE id = #{queryDto.id}"})
    PhotoAlbum findPhotoAlbumById(@Param("queryDto") QueryDto queryDto) throws SQLException;


    /**
     * 添加相册
     * @param photoAlbumDto
     * @throws SQLException
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert({"INSERT INTO t_photo_album (user_id,cover,name,type,create_time,introduction) " +
            "VALUE (#{photoAlbumDto.userId}, #{photoAlbumDto.cover}, #{photoAlbumDto.name}, #{photoAlbumDto.type}, #{photoAlbumDto.createTime}, #{photoAlbumDto.introduction}) "})
    void addAllPhotoAlbum(@Param("photoAlbumDto") PhotoAlbumDto photoAlbumDto) throws SQLException;


    /**
     * 更改相册信息
     * @param photoAlbumDto
     * @throws SQLException
     */
    @Update({"UPDATE t_photo_album SET cover = #{photoAlbumDto.cover}, name = #{photoAlbumDto.name}, type = #{photoAlbumDto.type}, " +
            "introduction = #{photoAlbumDto.introduction} " +
            "WHERE user_id = #{photoAlbumDto.userId} AND id = #{photoAlbumDto.id}"})
    void updateAllPhotoAlbum(@Param("photoAlbumDto") PhotoAlbumDto photoAlbumDto) throws SQLException;


    /**
     * 删除相册
     * @param userId
     * @param id
     * @throws SQLException
     */
    @Delete({"DELETE a, b " +
            "FROM t_photo_album a " +
            "LEFT JOIN t_photo b " +
            "ON a.id = b.album_id " +
            "WHERE a.user_id = #{userId} AND a.id = #{id} "})
    void deleteAllPhotoAlbum(Long userId,Long id) throws SQLException;

}
