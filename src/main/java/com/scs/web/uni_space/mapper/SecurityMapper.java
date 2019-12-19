package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.entity.Security;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;

/**
 * @author suyuxi
 */
public interface SecurityMapper {


    /**
     * 根据userId和photoAlbumId查找密保信息
     *
     * @param userId
     * @param photoAlbumId
     * @return Security
     * @throws SQLException
     */
    @Select({"SELECT * FROM t_security WHERE user_id = #{userId} AND photo_album_id = #{photoAlbumId} "})
    Security findSecurity(Long userId, Long photoAlbumId) throws SQLException;


    /**
     * 根据userId和photoAlbumId添加密保
     *
     * @param userId
     * @param photoAlbumId
     * @param question
     * @param answer
     * @return int
     * @throws SQLException
     */
    @Insert({"INSERT INTO t_security (user_id,photo_album_id,question,answer) VALUES (#{userId},#{photoAlbumId},#{question},#{answer}) "})
    void addSecurity(Long userId, Long photoAlbumId, String question, String answer) throws SQLException;


    /**
     * 根据userId和photoAlbumId更改密保信息
     *
     * @param userId
     * @param photoAlbumId
     * @param question
     * @param answer
     * @return int
     * @throws SQLException
     */
    @Update({"UPDATE t_security SET question = #{question}, answer = #{answer} WHERE photo_album_id = #{photoAlbumId} AND user_id = #{userId} "})
    void updateSecurity(Long userId, Long photoAlbumId, String question, String answer) throws SQLException;


    /**
     * 根据userId和photoAlbumId删除密保
     *
     * @param userId
     * @param photoAlbumId
     * @return int
     * @throws SQLException
     */
    @Delete({"DELETE FROM t_security WHERE photo_album_id = #{photoAlbumId} AND user_id = #{userId} "})
    void deleteSecurity(Long userId, Long photoAlbumId) throws SQLException;



}
