package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.service.FriendService;
import com.scs.web.uni_space.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author suyuxi
 * @className FriendController
 * @Description 朋友类
 * @Date 2019/12/4
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/friend")
public class FriendController {

    @Resource
    private FriendService friendService;

    @PostMapping(value = "/addFriend")
    Result addFriend (@RequestBody Friend friend){
        return friendService.addFriend(friend);
    }



}
