package com.scs.web.uni_space.service;


import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.entity.Security;
import com.scs.web.uni_space.common.Result;

/**
 * @author suyuxi
 */
public interface SecurityService {



    /**
     * 添加用户相册密保信息
     * @param security
     * @return Result
     */
    Result addSecurity(Security security);


    /**
     * 查找相册密保信息
     * @param security
     * @return Result
     */
    Result findSecurity(Security security);


    /**
     * 通过id查找密保
     *
     * @param queryDto
     * @return Result
     */
    Result findById(QueryDto queryDto);

    /**
     * 更改相册密保信息
     * @param security
     * @return Result
     */
    Result updateSecurity(Security security);


    /**
     * 删除相册密保信息
     * @param security
     * @return Result
     */
    Result deleteSecurity(Security security);
}
