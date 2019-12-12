package com.scs.web.uni_space.service;

import com.scs.web.uni_space.UniSpaceApplication;
import com.scs.web.uni_space.domain.dto.FriendDto;
import com.scs.web.uni_space.service.serviceImpl.FriendServiceImpl;
import com.scs.web.uni_space.common.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Queue;


@SpringBootTest(classes = UniSpaceApplication.class)
class FriendServiceTest {

    @Resource
    private FriendServiceImpl friendServiceImpl;

    private Result result = null;
    private FriendDto friendDto = new FriendDto();


    @Test
    void findAllByKey() {

        friendDto.setFromId(1L);
        friendDto.setKeyWords("");
        result = friendServiceImpl.findAllByKey(friendDto);
        System.out.println(result);
    }

    @Test
    void searchJournal() {

        friendDto.setFromId(1L);
        System.out.println(friendServiceImpl.searchJournal(friendDto).getData());
    }


    @Test
    void insertFriend() {
        friendDto.setFromId((long)11);
        friendDto.setToId((long)14);
        result = friendServiceImpl.addFriend(friendDto);
        System.out.println(result);
    }

    @Test
    void findAllApplicant() {
        friendDto.setToId((long) 4);
        result = friendServiceImpl.findAllApplicant(friendDto);
        System.out.println(result.getData());

    }

    @Test
    void confirmAdd() {
        friendDto.setFromId((long)1);
        friendDto.setToId((long)10);
        result = friendServiceImpl.confirmAdd(friendDto);
        System.out.println(result);
    }


    @Test
    void deleteFriend() {
        friendDto.setFromId((long)4);
        friendDto.setToId((long)1);
        result = friendServiceImpl.deleteFriend(friendDto);
        System.out.println(result);
    }


    @Test
    void rejectConfirm() {
        friendDto.setFromId((long)1);
        friendDto.setToId((long)4);
        result = friendServiceImpl.rejectConfirm(friendDto);
        System.out.println(result);
    }


    @Test
    void recommendFriend() {
        friendDto.setFromId(1L);
        result = friendServiceImpl.recommendFriend(friendDto);
        System.out.println(result.getData());
    }
}