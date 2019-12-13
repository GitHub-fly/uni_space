package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.entity.Security;
import com.scs.web.uni_space.service.SecurityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author suyuxi
 * @className SecurityController
 * @Description 密保接口
 * @Date 2019/12/5
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/security")
public class SecurityController {
    @Resource
    private SecurityService securityService;

    /**
     * 用户添加帐号密保接口
     *
     * @param security
     * @return Result
     */
    @PostMapping(value = "/account")
    Result addAccountSecurity(@RequestBody Security security) {
        return securityService.addAccountSecurity(security);
    }

    /**
     * 用户添加相册密保接口
     *
     * @param security
     * @return Result
     */
    @PostMapping(value = "/photoalbum")
    Result addPhotoAlbumSecurity(@RequestBody Security security) {
        return securityService.addPhotoAlbumSecurity(security);
    }

    /**
     * 用户查找账号密保接口
     *
     * @param security
     * @return Result
     */
    @PostMapping(value = "accountsecurity")
    Result findAccountSecurity(@RequestBody Security security) {
        return securityService.findAccountSecurity(security);
    }

    /**
     * 用户查找相册密保接口
     *
     * @param security
     * @return Result
     */
    @PostMapping(value = "/photoalbumsecurity")
    Result findPhotoAlbumSecurity(@RequestBody Security security) {
        return securityService.findPhotoAlbumSecurity(security);
    }

    /**
     * 用户更改账号密保信息接口
     *
     * @param security
     * @return Result
     */
    @PutMapping(value = "/accountsecurity")
    Result updateAccountSecurity(@RequestBody Security security) {
        return securityService.updateAccountSecurity(security);
    }

    /**
     * 用户更改相册密保信息接口
     *
     * @param security
     * @return Result
     */
    @PutMapping(value = "/photoalbumsecurity")
    Result updatePhotoAlbumSecurity(@RequestBody Security security) {
        return securityService.updatePhotoAlbumSecurity(security);
    }

    /**
     * 用户删除账号密保信息
     *
     * @param security
     * @return Result
     */
    @DeleteMapping(value = "/account")
    Result deleteAccountSecurity(@RequestBody Security security) {
        return securityService.deleteAccountSecurity(security);
    }

    /**
     * 用户删除相册密保信息
     *
     * @param security
     * @return Result
     */
    @DeleteMapping(value = "/photoalbum")
    Result deletePhotoAlbumSecurity(@RequestBody Security security) {
        return securityService.deletePhotoAlbumSecurity(security);
    }
}
