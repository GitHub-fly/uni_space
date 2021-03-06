package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.JournalDto;
import com.scs.web.uni_space.domain.dto.LikeDto;
import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.service.JournalService;
import io.swagger.annotations.Api;
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
@Api(value = "JournalController", tags = {"日志接口"})
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
    @ApiOperation(value = "通过id查找好友的所有日志", notes = "这个参数是自己的id,把birthday删了，不许给我传这个参数再问就是死")
    @PostMapping(value = "/index/data")
    Result findIndexData(@RequestBody UserDto userDto) {

        return journalService.findIndexData(userDto);
    }

    /**
     * 推荐日志
     *
     * @param userDto
     * @return
     */
    @ApiOperation(value = "通过id推荐好友的所有日志", notes = "这个参数是自己的id,把birthday删了，不许给我传这个参数再问就是死")
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
    @ApiOperation(value = "通过id自己好友的所有日志", notes = "这个参数是自己的id,把birthday删了，不许给我传这个参数再问就是死")
    @PostMapping(value = "/user/data")
    Result selectById(@RequestBody UserDto userDto) {
        return journalService.selectById(userDto);
    }

    /**
     * 查找某篇日志全部图片
     *
     * @param journalDto
     * @return
     */
    @ApiOperation(value = "通过id查找好友的某篇日志的照片", notes = "这个参数是文章的id")
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
    @ApiOperation(value = "通过id查找文章评论", notes = "传参是文章id")
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

    @ApiOperation(value = "通过日志id查找日志详情", notes = "传参数文章id")
    @PostMapping(value = "/user/journaldetail/{id}")
    Result selectUserJournalDetailById(@PathVariable("id") @Valid int id) {
        return journalService.selectJournalDetailById((long) id);
    }

    /**
     * 点赞接口判断
     *
     * @param likeDto
     * @return
     */

    @ApiOperation(value = "通过参数 判断是否点赞", notes = "传参数日志id,user_id")
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
    @ApiOperation(value = "通过日志id与user_id 进行点赞", notes = "传参数日志id,user_id")
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


    @ApiOperation(value = "通过日志id与user_id 取消点赞", notes = "传参数日志id,user_id")
    @DeleteMapping(value = "/user/journal/cancellike")
    Result cancelJournalLike(@RequestBody LikeDto likeDto) {
        return journalService.cancelLike(likeDto);
    }

    /**
     * pc新增日志
     *
     * @param journalDto
     * @return
     */

    @ApiOperation(value = "通过日志内容提取日志图片 和 user_id 新增日志", notes = "传参用户user_id,content")
    @PutMapping(value = "/user/journal/pcjournal")
    Result addJournal(@RequestBody JournalDto journalDto) {
        return journalService.addJournal(journalDto);
    }


    @ApiOperation(value = "通过日志内容 （图片数组） 和 user_id 新增日志", notes = "传参用户user_id,content,urls")
    @PutMapping(value = "/user/journal/mobilejournal")
    Result addMObileJounal(@RequestBody JournalDto journalDto) {
        return journalService.addMobileJournal(journalDto);
    }


    @ApiOperation(value = "批量删除日志", notes = "传日志id数组")
    @DeleteMapping(value = "/user/journal")
    Result deleteJournal(@RequestBody JournalDto journalDto) {
        return journalService.deleteJournal(journalDto);
    }
}
