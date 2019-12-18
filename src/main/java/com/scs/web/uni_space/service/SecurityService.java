package com.scs.web.uni_space.service;


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
