package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import com.scs.web.uni_space.domain.dto.FriendDto;
import com.scs.web.uni_space.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author suyuxi
 * @className FriendController
 * @Description 朋友类
 * @Description 好友控制层
 * @Date 2019/12/4
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/friend")
@Api(value = "FriendController", tags = {"好友模块接口"})
public class FriendController {

    @Resource
    private FriendService friendService;

    /**
     * 推荐好友接口
     *
     * @param friendDto
     * @return
     */
    @ApiOperation(value = "根据用户id推荐非好友用户", notes = "data为用户信息列表")
    @PostMapping(value = "/recommend")
    Result recommend(@RequestBody FriendDto friendDto) {
        return friendService.recommendFriend(friendDto);
    }

    /**
     * 查询该用户的所有好友列表信息
     * 或者
     * 通过模糊查询好友
     *
     * @param friendDto
     * @return
     */
    @ApiOperation(value = "根据用户id查找所有好友或者通过用户id和关键字精确查找好友", notes = "data为好友信息列表")
    @PostMapping(value = "/keyword")
    Result findAllByKey(@RequestBody FriendDto friendDto) {
        return friendService.findAllByKey(friendDto);
    }


    /**
     * 模糊查询用户
     *
     * @param friendDto
     * @return Result
     */
    @ApiOperation(value = "根据关键字模糊搜索用户", notes = "data为用户信息列表")
    @PostMapping(value = "/keywords")
    Result searchUserByKey(@RequestBody FriendDto friendDto) {
        return friendService.searchFriendByKey(friendDto);
    }


    /**
     * 查找个人日志
     *
     * @param friendDto
     * @return Result
     */
    @ApiOperation(value = "根据用户id查找用户所有日志", notes = "data为日志信息列表")
    @PostMapping(value = "/journal/key")
    Result searchJournalById(@RequestBody FriendDto friendDto) {
        return friendService.searchJournal(friendDto);
    }


    /**
     * 添加好友接口
     *
     * @param friendDto
     * @return Result
     */
    @ApiOperation(value = "根据fromId和toId添加好友", notes = "msg为成功，则添加成功")
    @PostMapping(value = "/friend")
    Result addFriend(@RequestBody FriendDto friendDto) {
        return friendService.addFriend(friendDto);
    }


    /**
     * 查找所有请求接口
     *
     * @param friendDto
     * @return Result
     */
    @ApiOperation(value = "通过fromId查找所有添加请求", notes = "data为用户信息列表")
    @PostMapping(value = "/application")
    Result findAllApplication(@RequestBody FriendDto friendDto) {
        return friendService.findAllApplicant(friendDto);
    }


    /**
     * 同意好友请求接口
     *
     * @param friendDto
     * @return Result
     */
    @ApiOperation(value = "根据fromId和toId同意添加请求", notes = "msg为成功，则添加成功")
    @PutMapping(value = "/confirm")
    Result confirmAdd(@RequestBody FriendDto friendDto) {
        if (friendDto.getFromId() != null && friendDto.getToId() != null) {
            return friendService.confirmAdd(friendDto);
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    /**
     * 拒绝好友请求接口
     *
     * @param friendDto
     * @return Result
     */
    @ApiOperation(value = "根据fromId和toId拒绝添加请求", notes = "msg为成功，则拒绝成功")
    @DeleteMapping(value = "/reject")
    Result rejectConfirm(@RequestBody FriendDto friendDto) {
        return friendService.rejectConfirm(friendDto);
    }


    /**
     * 删除好友接口
     *
     * @param friendDto
     * @return Result
     */
    @ApiOperation(value = "根据fromId和toId删除好友", notes = "msg为成功，则删除成功")
    @DeleteMapping(value = "/friend")
    Result deleteFriend(@RequestBody FriendDto friendDto) {
        return friendService.deleteFriend(friendDto);
    }


    @ApiOperation(value = "根据fromId,toId和collectionFlag修改好友权限", notes = "msg为成功，则修改成功")
    @PutMapping(value = "/collection")
    Result updateFriendCollection(@RequestBody FriendDto friendDto) {
        return friendService.updateFriendCollectionFlag(friendDto);
    }

}
