package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.MusicDto;
import com.scs.web.uni_space.service.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName MusicController
 * @Description 音乐接口
 * @Author xiaobinggan
 * @Date 2019/12/19 8:52 上午
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/api/music")
@Api(value = "MusicController", tags = {"音乐接口"})
public class MusicController {
    @Resource
    private MusicService musicService;

    /**
     * 获取所有音乐
     *
     * @return Result
     */
    @ApiOperation(value = "获取所有音乐")
    @GetMapping(value = "/all")
    Result selectAll() {
        return Result.success(musicService.selectAll());
    }

    /**
     * 根据ID查询音乐
     *
     * @return
     */
    @ApiOperation(value = "根据ID查询音乐")
    @PostMapping(value = "/detail")
    Result selectById(@RequestBody MusicDto musicDto) {
        return Result.success(musicService.selectById(musicDto));
    }

    /**
     * 添加个人喜欢音乐
     *
     * @param musicDto
     * @return
     */
    @ApiOperation(value = "添加个人喜欢音乐")
    @PutMapping(value = "/add")
    Result addMusic(@RequestBody MusicDto musicDto) {
        return Result.success(musicService.addMusic(musicDto));
    }

    /**
     * 移除个人喜欢音乐
     *
     * @param musicDto
     * @return
     */
    @ApiOperation(value = "移除个人喜欢音乐")
    @DeleteMapping(value = "/delete")
    Result deleteMusic(@RequestBody MusicDto musicDto) {
        return Result.success(musicService.deleteMusic(musicDto));
    }
}
