package com.scs.web.uni_space.service;


import com.scs.web.uni_space.domain.dto.FriendDto;
import com.scs.web.uni_space.common.Result;

/**
 * @author suyuxi
 * @className FriendService
 * @Description 好友操作接口
 * @Date 2019/12/3
 * @Version 1.0
 **/
public interface FriendService  {


    /**
     * 推荐好友
     * @param friendDto
     * @return Result
     */
    Result recommendFriend(FriendDto friendDto);

    /**
     * 查询该用户的所有好友信息
     * 或者
     * 通过电话号码、email、账号，昵称、简介模糊查询好友信息，进行添加好友操作
     * @param friendDto
     * @return Result
     */
    Result findAllByKey(FriendDto friendDto);


    /**
     * 通过关键字查询用户信息（目的：进行添加好友）
     * @param friendDto
     * @return Result
     */
    Result searchFriendByKey(FriendDto friendDto);

    /**
     * 通过指定用户id查找对应的日志列表信息
     * @param friendDto
     * @return Result
     */
    Result searchJournal(FriendDto friendDto);


    /**
     * 发起添加好友请求
     * @param friendDto
     * @return Result
     */
    Result addFriend(FriendDto friendDto);


    /**
     * 查找所有申请信息
     * @param friendDto
     * @return Result
     */
    Result findAllApplicant(FriendDto friendDto);

    /**
     * 确认请求
     * @param friendDto
     * @return Result
     */
    Result confirmAdd(FriendDto friendDto);

    /**
     * 拒绝请求
     * @param friendDto
     * @return Result
     */
    Result rejectConfirm(FriendDto friendDto);

    /**
     * 删除好友
     * @param friendDto
     * @return Result
     */
    Result deleteFriend(FriendDto friendDto);


    /**
     * 修改好友权限
     *
     * @param friendDto
     * @return Result
     */
    Result updateFriendCollectionFlag(FriendDto friendDto);
}