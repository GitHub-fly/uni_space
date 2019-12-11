package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.domain.entity.Journal;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @author wl
 * @ClassNamejournal
 * @Description TODO
 * @Date 2019/12/9
 * @Version 1.0
 */
public interface JournalMapper {
    /**
     * 查询用户所有的日志列表；
     * @param id
     * @return
     */
    @Select("SELECT * FROM t_journal  WHERE user_id =#{id}" )
    List<Journal> findAllJounal(long id)throws SQLException;





}
