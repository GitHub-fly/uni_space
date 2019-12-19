package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.dto.MusicDto;
import com.scs.web.uni_space.domain.vo.MusicVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName MusicMapper
 * @Description 音乐接口
 * @Author xiaobinggan
 * @Date 2019/12/19 8:31 上午
 * @Version 1.0
 **/
public interface MusicMapper {

    /**
     * 查询所有音乐
     *
     * @return
     * @throws SQLException
     */
    @Select("SELECT id , user_id , name , content , singer  FROM t_music GROUP BY name ORDER BY id ASC")
    List<MusicVo> selectAll() throws SQLException;

    /**
     * 根据个人id查找音乐
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    @Select("SELECT id , user_id , name , content , singer " +
            "FROM t_music " +
            "WHERE user_id = #{userId} ")
    List<MusicVo> selectById(Long userId) throws SQLException;

    /**
     * 个人添加音乐
     *
     * @param musicDto
     * @throws SQLException
     */
    @Insert("INSERT INTO t_music (user_id , name , content , singer ) " +
            "VALUES (#{music.userId} , #{music.name} , #{music.content} , #{music.singer} )")
    void addMusic(@Param("music") MusicDto musicDto) throws SQLException;

    /**
     * @param userId
     * @throws SQLException
     */
    @Delete("Delete FROM t_music WHERE id = #{musicId} AND user_id = #{userId}")
    void deleteMusic(Long userId, Long musicId) throws SQLException;
}
