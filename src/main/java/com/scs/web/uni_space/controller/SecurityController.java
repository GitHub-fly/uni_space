package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.domain.entity.Security;
import com.scs.web.uni_space.service.SecurityService;
import com.scs.web.uni_space.util.Result;
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
     * @param security
     * @return Result
     */
    @PostMapping(value = "/addAccountSecurity")
    Result addAccountSecurity(@RequestBody Security security){
        return securityService.addAccountSecurity(security);
    }

    /**
     * 用户添加相册密保接口
     * @param security
     * @return Result
     */
    @PostMapping(value = "/addPhotoAlbumSecurity")
    Result addPhotoAlbumSecurity(@RequestBody Security security){
        return securityService.addPhotoAlbumSecurity(security);
    }

    /**
     * 用户查找账号密保接口
     * @param security
     * @return Result
     */
    @GetMapping(value = "/findAccountSecurity")
    Result findAccountSecurity(Security security){
        return securityService.findAccountSecurity(security);
    }

    /**
     * 用户查找相册密保接口
     * @param security
     * @return Result
     */
    @GetMapping(value = "/findPhotoAlbumSecurity")
    Result findPhotoAlbumSecurity(Security security){
        return securityService.findPhotoAlbumSecurity(security);
    }

    /**
     * 用户更改账号密保信息接口
     * @param security
     * @return Result
     */
    @PutMapping(value = "/updateAccountSecurity")
    Result updateAccountSecurity(@RequestBody Security security){
        return securityService.updateAccountSecurity(security);
    }

    /**
     * 用户更改相册密保信息接口
     * @param security
     * @return Result
     */
    @PutMapping(value = "/updatePhotoAlbumSecurity")
    Result updatePhotoAlbumSecurity(@RequestBody Security security){
        return securityService.updatePhotoAlbumSecurity(security);
    }

    /**
     * 用户删除账号密保信息
     * @param security
     * @return Result
     */
    @DeleteMapping(value = "/deleteAccountSecurity")
    Result deleteAccountSecurity(@RequestBody Security security){
        return securityService.deleteAccountSecurity(security);
    }

    /**
     * 用户删除相册密保信息
     * @param security
     * @return Result
     */
    @DeleteMapping(value = "/deletePhotoAlbumSecurity")
    Result deletePhotoAlbumSecurity(@RequestBody Security security){
        return securityService.deletePhotoAlbumSecurity(security);
    }
}
