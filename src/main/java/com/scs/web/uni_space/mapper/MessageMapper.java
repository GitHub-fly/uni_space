package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.vo.LikeVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 22968
 */
public interface MessageMapper {


    /**
     * 点赞消息
     *
     * @param queryDto
     * @return List<LikeVo>
     * @throws SQLException
     */
    @Select({"SELECT c.id AS user_id ,c.nickname ,c.avatar, a.id AS journal_id ,a.title , b.create_time " +
            "FROM t_journal a " +
            "LEFT JOIN t_like b " +
            "ON a.id = b.journal_id " +
            "LEFT JOIN t_user c " +
            "ON b.user_id = c.id " +
            "WHERE a.user_id = #{queryDto.id} AND b.create_time IS NOT NULL " +
            "ORDER BY b.create_time DESC "})
    List<LikeVo> findAllLike(@Param(value = "queryDto") QueryDto queryDto) throws SQLException;
}
