package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.entity.Security;
import org.apache.ibatis.annotations.*;

/**
 * @author suyuxi
 */
public interface SecurityMapper {


    @Results(id = "security", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "photoAlbumId", column = "photo_album_id"),
            @Result(property = "question", column = "question"),
            @Result(property = "answer", column = "answer"),
    })

    /**
     * 根据用户id查找个人帐号密保信息
     * @param userId
     * @return Security
     */
    @Select({"SELECT * FROM t_security WHERE photo_album_id = 0 AND user_id = #{userId}"})
    Security findAccountSecurity(Long userId) ;

    /**
     * 根据userId和photoAlbumId查找相册密保信息
     * @param userId
     * @param photoAlbumId
     * @return
     */
    @Select({"SELECT * FROM t_security WHERE user_id = #{userId} AND photo_album_id = #{photoAlbumId}"})
    Security findPhotoAlbumSecurity(Long userId,Long photoAlbumId);


    /**
     * 根据userId添加个人帐号密保信息
     * @param userId
     * @param question
     * @param answer
     * @return int
     */
    @Insert({"INSERT INTO t_security (user_id,question,answer) VALUES (#{userId},#{question},#{answer})"})
    int addAccountSecurity(Long userId,String question,String answer);


    /**
     * 根据userId和photoAlbumId添加相册密保
     * @param userId
     * @param photoAlbumId
     * @param question
     * @param answer
     * @return int
     */
    @Insert({"INSERT INTO t_security (user_id,photo_album_id,question,answer) VALUES (#{userId},#{photoAlbumId},#{question},#{answer})"})
    int addPhotoAlbumSecurity(Long userId,Long photoAlbumId,String question,String answer);

    /**
     * 根据userId更改账号密保信息
     * @param userId
     * @param question
     * @param answer
     * @return int
     */
    @Update({"UPDATE t_security SET question = #{question}, answer = #{answer} WHERE photo_album_id = 0 AND user_id = #{userId}"})
    int updateAccountSecurity(Long userId,String question,String answer);


    /**
     * 根据userId和photoAlbumId更改相册密保信息
     * @param userId
     * @param photoAlbumId
     * @param question
     * @param answer
     * @return
     */
    @Update({"UPDATE t_security SET question = #{question}, answer = #{answer} WHERE photo_album_id = #{photoAlbumId} AND user_id = #{userId}"})
    int updatePhotoAlbumSecurity(Long userId,Long photoAlbumId,String question,String answer);

    /**
     * 根据userId删除账号密保
     * @param userId
     * @return int
     */
    @Delete({"DELETE FROM t_security WHERE photo_album_id = 0 AND user_id = #{userId}"})
    int deleteAccountSecurity(Long userId);

    /**
     * 根据userId和photoAlbumId删除相册密保
     * @param userId
     * @param photoAlbumId
     * @return int
     */
    @Delete({"DELETE FROM t_security WHERE photo_album_id = #{photoAlbumId} AND user_id = #{userId}"})
    int deletePhotoAlbumSecurity(Long userId,Long photoAlbumId);
}
