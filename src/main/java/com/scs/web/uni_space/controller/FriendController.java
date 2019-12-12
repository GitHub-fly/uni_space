package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.FriendDto;
import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.service.FriendService;
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
     * 添加好友接口
     *
     * @param friendDto
     * @return Result
     */
    @PostMapping(value = "/friend")
    Result addFriend(@RequestBody FriendDto friendDto) {
        return friendService.addFriend(friendDto);
    }

//    /**
//     * 查找所有好友接口
//     * @param friendDto
//     * @return Result
//     */
//    @GetMapping(value = "/all")
//    Result findAllFriend(FriendDto friendDto){
//        return friendService.findAllFriend(friendDto);
//    }

    /**
     * 查找所有请求接口
     *
     * @param friendDto
     * @return Result
     */
    @GetMapping(value = "/application")
    Result findAllApplicant(FriendDto friendDto) {
        return friendService.findAllApplicant(friendDto);
    }


    /**
     * 同意好友请求接口
     *
     * @param friendDto
     * @return Result
     */
    @PutMapping(value = "/confirm")
    Result confirmAdd(@RequestBody FriendDto friendDto) {
        return friendService.confirmAdd(friendDto);
    }

    /**
     * 拒绝好友请求接口
     *
     * @param friendDto
     * @return Result
     */
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
    @DeleteMapping(value = "/friend")
    Result deleteFriend(@RequestBody FriendDto friendDto) {
        return friendService.deleteFriend(friendDto);
    }

    /**
     * 查询该用户的所有好友列表信息
     * 或者
     * 通过模糊查询好友
     *
     * @param queryDto
     * @return
     */
    @PostMapping(value = "/keyword")
    Result findAllByKey(@RequestBody QueryDto queryDto) {
        return friendService.findAllByKey(queryDto);
    }
}
