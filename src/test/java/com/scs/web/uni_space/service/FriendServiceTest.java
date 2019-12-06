package com.scs.web.uni_space.service;

import com.scs.web.uni_space.UniSpaceApplication;
import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.service.serviceImpl.FriendServiceImpl;
import com.scs.web.uni_space.util.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;



@SpringBootTest(classes = UniSpaceApplication.class)
class FriendServiceTest {

    @Resource
    private FriendServiceImpl friendServiceImpl;

    private Result result = null;
    private Friend friend = new Friend();

    @Test
    void selectAllFriend() {
        friend.setFromId((long)2);
        result = friendServiceImpl.findAllFriend(friend);
        System.out.println(result);
    }

    @Test
    void insertFriend() {
        friend.setFromId((long)11);
        friend.setToId((long)14);
        result = friendServiceImpl.addFriend(friend);
        System.out.println(result);
    }

    @Test
    void findAllApplicant() {
        friend.setToId((long) 4);
        result = friendServiceImpl.findAllApplicant(friend);
        System.out.println(result.getData());

    }

    @Test
    void confirmAdd() {
        friend.setFromId((long)1);
        friend.setToId((long)4);
        result = friendServiceImpl.confirmAdd(friend);
        System.out.println(result);
    }


    @Test
    void deleteFriend() {
        friend.setFromId((long)4);
        friend.setToId((long)1);
        result = friendServiceImpl.deleteFriend(friend);
        System.out.println(result);
    }


    @Test
    void rejectConfirm() {
        friend.setFromId((long)1);
        friend.setToId((long)4);
        result = friendServiceImpl.rejectConfirm(friend);
        System.out.println(result);
    }
}