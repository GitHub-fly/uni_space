package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.UniSpaceApplication;
import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest(classes = UniSpaceApplication.class)
class FriendMapperTest {


    @Resource
    private FriendMapper friendMapper;




    @Test
    void selectAll() {

        List<Friend> friends;
        friends = friendMapper.selectAll();
        friends.forEach(System.out::println);
    }


    @Test
    void selectByFromId() {
        List<User> users;
        users = friendMapper.selectByFromId((long) 1);
        users.forEach(System.out::println);
    }


    @Test
    void selectByToId() {
        List<User> users;
        users = friendMapper.selectByToId((long)2);
        users.forEach(System.out::println);
    }

    @Test
    void insertOther() {
        int i = friendMapper.insertOther((long)1,(long)5);
        System.out.println(i);
    }

    @Test
    void insertEachOther() {
        int i = friendMapper.insertEachOther((long)5,(long)1);
        System.out.println(i);
    }


    @Test
    void updateFriendFlag() {
        int i = friendMapper.updateFriendFlag((long)2,(long)3);
        System.out.println(i);
    }

    @Test
    void deleteFriend() {
        int i = friendMapper.deleteFriend((long)1,(long)4);
        System.out.println(i);
    }

    @Test
    void selectFriendFlag() {
        Friend friend = friendMapper.selectFriendFlag((long)2,(long)3);
        System.out.println(friend);
    }


    @Test
    void deleteReject() {
        int i = friendMapper.deleteReject((long)3,(long)2);
        System.out.println(i);
    }

}