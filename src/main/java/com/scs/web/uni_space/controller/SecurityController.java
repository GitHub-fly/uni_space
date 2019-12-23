package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.QueryDto;
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
@Api(value = "SecurityController", tags = {"密保模块接口"})
public class SecurityController {
    @Resource
    private SecurityService securityService;


    /**
     * 用户添加密保接口
     *
     * @param security
     * @return Result
     */
    @ApiOperation(value = "根据userId，PhotoAlbumId，question和answer添加密保", notes = "msg为成功，则添加成功")
    @PostMapping(value = "/add")
    Result addPhotoAlbumSecurity(@RequestBody Security security) {
        return securityService.addSecurity(security);
    }


    /**
     * 用户查找密保接口
     *
     * @param security
     * @return Result
     */
    @ApiOperation(value = "根据userId和PhotoAlbumId查看密保", notes = "data为密保信息")
    @PostMapping(value = "/search")
    Result findSecurity(@RequestBody Security security) {
        return securityService.findSecurity(security);
    }

    /**
     * 根据密保id查看密保
     *
     * @param queryDto
     * @return
     */

    @ApiOperation(value = "根据密保id查看密保", notes = "data为密保信息")
    @PostMapping(value = "/find")
    Result findSecurityById(@RequestBody QueryDto queryDto) {
        return securityService.findById(queryDto);
    }

    /**
     * 用户更改密保信息接口
     *
     * @param security
     * @return Result
     */
    @ApiOperation(value = "根据userId，PhotoAlbumId，question和answer更改密保", notes = "msg为成功，则更改成功")
    @PutMapping(value = "/revamp")
    Result updatePhotoAlbumSecurity(@RequestBody Security security) {
        return securityService.updateSecurity(security);
    }


    /**
     * 用户删除相册密保信息
     *
     * @param security
     * @return Result
     */
    @ApiOperation(value = "根据userId和PhotoAlbumId删除密保", notes = "msg为成功，则删除成功")
    @DeleteMapping(value = "/del")
    Result deletePhotoAlbumSecurity(@RequestBody Security security) {
        return securityService.deleteSecurity(security);
    }
}
