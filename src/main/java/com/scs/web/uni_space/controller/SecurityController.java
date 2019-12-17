package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.entity.Security;
import com.scs.web.uni_space.service.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author suyuxi
 * @className SecurityController
 * @Description 密保控制层
 * @Date 2019/12/5
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/security")
@Api(value = "NoteController", tags = {"密保模块接口"})
public class SecurityController {
    @Resource
    private SecurityService securityService;

    /**
     * 用户添加帐号密保接口
     *
     * @param security
     * @return Result
     */
    @ApiOperation(value = "根据userId，question和answer添加账号密保", notes = "msg为成功，则添加成功")
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
    @ApiOperation(value = "根据userId，PhotoAlbumId，question和answer添加相册密保", notes = "msg为成功，则添加成功")
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
    @ApiOperation(value = "根据userId查找账号密保", notes = "data为账号密保信息")
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
    @ApiOperation(value = "根据userId和PhotoAlbumId查看相册密保", notes = "data为相册密保信息")
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
    @ApiOperation(value = "根据userId，question和answer更改账号密保", notes = "msg为成功，则更改成功")
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
    @ApiOperation(value = "根据userId，PhotoAlbumId，question和answer更改相册密保", notes = "msg为成功，则更改成功")
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
    @ApiOperation(value = "根据userId删除账号密保", notes = "msg为成功，则删除成功")
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
    @ApiOperation(value = "根据userId和PhotoAlbumId删除相册密保", notes = "msg为成功，则删除成功")
    @DeleteMapping(value = "/photoalbum")
    Result deletePhotoAlbumSecurity(@RequestBody Security security) {
        return securityService.deletePhotoAlbumSecurity(security);
    }
}
