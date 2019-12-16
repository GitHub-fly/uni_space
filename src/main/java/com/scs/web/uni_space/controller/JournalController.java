package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.JournalDto;
import com.scs.web.uni_space.domain.dto.LikeDto;
import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.service.JournalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * @author wl
 * @ClassNameJournalController
 * @Description 日志接口
 * @Date 2019/12/9
 * @Version 1.0
 */
@RestController
@Api(description = "日志接口")
@RequestMapping(value = "/api/journal")

public class JournalController {

    @Resource
    private JournalService journalService;

    /**
     * 首页数据展示
     *
     * @param userDto
     * @return
     */
    @ApiOperation(value = "通过id查找好友的所有日志")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "这个参数是自己的id", dataType = "Integer"),
            @ApiImplicitParam(name = "birthday", value = "把birthday删了，不许给我传这个参数再问就是死", required = false)})
    @PostMapping(value = "/index/data")
    Result findIndexData(@RequestBody UserDto userDto) {

        return journalService.findIndexData(userDto);
    }

    @ApiOperation(value = "通过id推荐好友所有日志")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "这个参数是自己的id", dataType = "Integer"),
            @ApiImplicitParam(name = "birthday", value = "把birthday删了，不许给我传这个参数再问就是死")})
    @PostMapping(value = "/recoomendJournal/data")
    Result recommendJournal(@RequestBody UserDto userDto) {

        return journalService.recommendFriendJournal(userDto);
    }

    /**
     * 通过指定用户id查找改用户的所有日志信息
     *
     * @param userDto
     * @return
     */
    @ApiOperation(value = "通过id查找自己的所有日志")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "参数是自己的id", dataType = "Integer"),
            @ApiImplicitParam(name = "birthday", value = "把birthday删了，不许给我传这个参数再问就是死", required = false)})
    @PostMapping(value = "/user/data")
    Result selectById(@RequestBody UserDto userDto) {
        return journalService.selectById(userDto);
    }

    /**
     * 查找某片日志全部图片
     *
     * @param journalDto
     * @return
     */
    @ApiOperation(value = "通过id查找好友的所有日志的照片")
    @ApiImplicitParam(name = "id", value = "参数是文章id", dataType = "Integer")

    @PostMapping(value = "/user/journalpicture")
    Result selectJournalPictureById(@RequestBody JournalDto journalDto) {
        return journalService.selectJournalPictureById(journalDto);
    }

    /**
     * 查找一篇文章评论数
     *
     * @param journalDto
     * @return
     */
    @ApiOperation(value = "通过id查找文章评论")
    @ApiImplicitParam(name = "id", value = "传参是文章id", dataType = "Integer")
    @PostMapping(value = "/user/comment")
    Result selectCommentById(@RequestBody JournalDto journalDto) {
        return journalService.selectCommentById(journalDto);
    }

    /**
     * 通过日志id查找详情
     *
     * @param id
     * @return
     */

    @ApiOperation(value = "通过日志id查找日志详情")
    @ApiImplicitParam(name = "id", value = "传参数文章id", dataType = "Integer")
    @PostMapping(value = "/user/journaldetail/{id}")
    Result selectUserJournalDetailById(@PathVariable("id") @Valid int id) {
        return journalService.selectJournalDetailById((long) id);
    }


    @ApiOperation(value = "通过参数 判断是否点赞")
    @ApiImplicitParam(name = "id", value = "传参数日志id,user_id", dataType = "Integer")
    @PostMapping(value = "/user/journal/concernLike")
    Result concernLike(@RequestBody LikeDto likeDto) {
        return journalService.concernJournalLikes(likeDto);
    }
    /**
     * 点赞功能
     *
     * @param likeDto
     * @return
     */
    @ApiOperation(value = "通过日志id与user_id 进行点赞")
    @ApiImplicitParam(name = "id", value = "传参数日志id,user_id", dataType = "Integer")
    @PutMapping(value = "/user/journal/addlike")
    Result addJournalLike(@RequestBody LikeDto likeDto) {
        return journalService.clickLikes(likeDto);
    }

    /**
     * 取消点赞功能
     *
     * @param likeDto
     * @return
     */


    @ApiOperation(value = "通过日志id与user_id 取消点赞")
    @ApiImplicitParam(name = "id", value = "传参数日志id,user_id", dataType = "Integer")
    @DeleteMapping(value = "/user/journal/cancellike")
    Result cancelJournalLike(@RequestBody LikeDto likeDto) {
        return journalService.cancelLike(likeDto);
    }
}
