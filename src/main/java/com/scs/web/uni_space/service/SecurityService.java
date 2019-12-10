package com.scs.web.uni_space.service;


import com.scs.web.uni_space.domain.entity.Security;
import com.scs.web.uni_space.common.Result;

/**
 * @author suyuxi
 */
public interface SecurityService {

    /**
     * 添加账号密保信息
     * @param security
     * @return Result
     */
    Result addAccountSecurity(Security security);

    /**
     * 添加用户相册密保信息
     * @param security
     * @return Result
     */
    Result addPhotoAlbumSecurity(Security security);

    /**
     * 查找帐号密保信息
     * @param security
     * @return Result
     */
    Result findAccountSecurity(Security security);

    /**
     * 查找相册密保信息
     * @param security
     * @return Result
     */
    Result findPhotoAlbumSecurity(Security security);

    /**
     * 更改帐号密保信息
     * @param security
     * @return Result
     */
    Result updateAccountSecurity(Security security);


    /**
     * 更改相册密保信息
     * @param security
     * @return Result
     */
    Result updatePhotoAlbumSecurity(Security security);

    /**
     * 删除帐号密保信息
     * @param security
     * @return Result
     */
    Result deleteAccountSecurity(Security security);


    /**
     * 删除相册密保信息
     * @param security
     * @return Result
     */
    Result deletePhotoAlbumSecurity(Security security);
}
