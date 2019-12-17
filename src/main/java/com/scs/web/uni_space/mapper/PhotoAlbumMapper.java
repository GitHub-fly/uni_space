package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.entity.PhotoAlbum;
import com.scs.web.uni_space.domain.vo.PhotoAlbumVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.sql.Timestamp;
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
    @Select({"SELECT a.* ,b.id AS security_id " +
            "FROM t_photo_album a " +
            "LEFT JOIN t_security b " +
            "ON a.id = b.photo_album_id " +
            "WHERE a.user_id = #{userId} "})
    List<PhotoAlbumVo> findAllPhotoAlbum(Long userId) throws SQLException;


    /**
     * 添加相册
     * @param userId
     * @param name
     * @param createTime
     * @throws SQLException
     */
    @Insert({"INSERT INTO t_photo_album (user_id,cover,name,type,create_time,introduction) " +
            "VALUE (#{userId}, #{cover}, #{name}, #{type}, #{createTime}, #{introduction}) "})
    void addAllPhotoAlbum(Long userId,  String name, Timestamp createTime) throws SQLException;


    /**
     * 更改相册信息
     * @param id
     * @param userId
     * @param cover
     * @param name
     * @param type
     * @param introduction
     * @throws SQLException
     */
    @Update({"UPDATE t_photo_album SET cover = #{cover}, name = #{name}, type = #{type}, introduction = #{introduction} " +
            "WHERE user_id = #{userId} AND id = #{id}"})
    void updateAllPhotoAlbum(Long id, Long userId, String cover, String name, String type, String introduction) throws SQLException;


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
