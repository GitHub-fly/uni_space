package com.scs.web.uni_space.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;

/**
 * @author wl
 * @ClassNamesad
 * @Description TODO
 * @Date 2019/12/10
 * @Version 1.0
 */
public interface CommonMapper {
    /**
     * 重置指定数据表的主键自增，使之连续
     *
     * @throws SQLException
     */
    @Update("ALTER TABLE ${tableName} AUTO_INCREMENT = 1 ")
    void returnid (@Param("tableName") String tableName) throws SQLException;
}