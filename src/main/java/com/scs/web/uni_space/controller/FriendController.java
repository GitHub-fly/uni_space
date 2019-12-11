package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.domain.dto.FriendDto;
import com.scs.web.uni_space.service.FriendService;
import com.scs.web.uni_space.common.Result;
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
public class FriendController {

    @Resource
    private FriendService friendService;

    /**
     * 推荐好友接口
     * @param friendDto
     * @return
     */
    @PostMapping(value = "/recommend")
    Result recommend(@RequestBody FriendDto friendDto) {
        return friendService.recommendFriend(friendDto);
    }

    /**
     * 查询该用户的所有好友列表信息
     * 或者
     * 通过模糊查询好友
     * @param friendDto
     * @return
     */
    @PostMapping(value = "/keyword")
    Result findAllByKey(@RequestBody FriendDto friendDto){
        return friendService.findAllByKey(friendDto);
    }


    /**
     * 模糊查询用户
     * @param friendDto
     * @return Result
     */
    @PostMapping(value = "/keywords")
    Result searchUserByKey(@RequestBody FriendDto friendDto){
        return friendService.searchFriendByKey(friendDto);
    }


    /**
     * 查找日志
     * @param friendDto
     * @return Result
     */
    @PostMapping(value = "/journal/key")
    Result searchJournalById(@RequestBody FriendDto friendDto) {
        return friendService.searchJournal(friendDto);
    }


    /**
     * 添加好友接口
     * @param friendDto
     * @return Result
     */
    @PostMapping(value = "/friend")
    Result addFriend (@RequestBody FriendDto friendDto){
        return friendService.addFriend(friendDto);
    }


    /**
     * 查找所有请求接口
     * @param friendDto
     * @return Result
     */
    @PostMapping(value = "/application")
    Result findAllApplicant(@RequestBody FriendDto friendDto){
        return friendService.findAllApplicant(friendDto);
    }


    /**
     * 同意好友请求接口
     * @param friendDto
     * @return Result
     */
    @PutMapping(value = "/confirm")
    Result confirmAdd(@RequestBody FriendDto friendDto){
        return friendService.confirmAdd(friendDto);
    }


    /**
     * 拒绝好友请求接口
     * @param friendDto
     * @return Result
     */
    @DeleteMapping(value = "/reject")
    Result rejectConfirm(@RequestBody FriendDto friendDto){
        return friendService.rejectConfirm(friendDto);
    }


    /**
     * 删除好友接口
     * @param friendDto
     * @return Result
     */
    @DeleteMapping(value = "/friend")
    Result deleteFriend(@RequestBody FriendDto friendDto){
        return friendService.deleteFriend(friendDto);
    }

}
