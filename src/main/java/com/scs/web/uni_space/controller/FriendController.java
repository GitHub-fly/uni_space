package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.service.FriendService;
import com.scs.web.uni_space.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author suyuxi
 * @className FriendController
<<<<<<< Updated upstream
 * @Description 朋友类
 * @Description 好友控制层
=======
 * @Description 好友控制
>>>>>>> Stashed changes
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
     * @param friend
     * @return Result
     */
    @PostMapping(value = "/addFriend")
    Result addFriend (@RequestBody Friend friend){
        return friendService.addFriend(friend);
    }

    /**
     * 查找所有好友接口
     * @param friend
     * @return Result
     */
    @GetMapping(value = "/findAllFriend")
    Result findAllFriend(Friend friend){
        return friendService.findAllFriend(friend);
    }

    /**
     * 查找所有请求接口
     * @param friend
     * @return Result
     */
    @GetMapping(value = "/findAllApplicant")
    Result findAllApplicant( Friend friend){
        return friendService.findAllApplicant(friend);
    }


    /**
     * 同意好友请求接口
     * @param friend
     * @return return
     */
    @PutMapping(value = "/confirmAdd")
    Result confirmAdd(@RequestBody Friend friend){
        return friendService.confirmAdd(friend);
    }

    /**
     * 拒绝好友请求接口
     * @param friend
     * @return Result
     */
    @DeleteMapping(value = "/rejectConfirm")
    Result rejectConfirm(@RequestBody Friend friend){
        return friendService.rejectConfirm(friend);
    }

    /**
     * 删除好友接口
     * @param friend
     * @return Result
     */
    @DeleteMapping(value = "/deleteFriend")
    Result deleteFriend(@RequestBody Friend friend){
        return friendService.deleteFriend(friend);
    }

}
