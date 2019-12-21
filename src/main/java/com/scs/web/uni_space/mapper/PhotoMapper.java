package com.scs.web.uni_space.mapper;


import com.scs.web.uni_space.domain.entity.Photo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 22968
 */

public interface PhotoMapper {


    /**
     * 查找所有照片
     *
     * @param albumId
     * @return List
     * @throws SQLException
     */
    @Select({"SELECT * FROM t_photo WHERE album_id = #{albumId}"})
    List<Photo> findAll(Long albumId) throws SQLException;


    /**
     *  pc端批量插入
     *
     * @param photoList
     * @return int
     * @throws SQLException
     */
    @Insert({
            "<script> ",
            "INSERT into t_photo (album_id, url, create_time) VALUES " ,
            "<foreach collection = 'list' item = 'item' index = 'index' separator = ','> " ,
            "(#{item.albumId}, #{item.url}, #{item.createTime}) " ,
            "</foreach> " ,
            "</script> "
    })
    void batchPcInsertPhoto(@Param(value = "list") List<Photo> photoList) throws SQLException;


    /**
     * 移动端批量插入
     *
     * @param photoList
     * @param albumId
     * @throws SQLException
     */
    @Insert({
            "<script> ",
            "INSERT into t_photo (album_id, url, create_time) VALUES " ,
            "<foreach collection = 'list' item = 'item' index = 'index' separator = ','> " ,
            "(#{albumId}, #{item.url}, #{item.createTime}) " ,
            "</foreach> " ,
            "</script> "
    })
    void batchInsertPhoto(@Param(value = "list") List<Photo> photoList, Long albumId) throws SQLException;


    /**
     * 批量删除某相册中的部分照片
     *
     * @param longList
     * @throws SQLException
     */
    @Delete({
            "<script>",
            "DELETE FROM t_photo WHERE id IN(",
            "<foreach collection = 'list' item = 'item' index = 'index' separator = ','>",
            "#{item}",
            "</foreach>)",
            "</script>"
    })
    void batchDeletePhoto(@Param(value = "list") List<Long> longList) throws SQLException;



}
