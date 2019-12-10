package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.UniSpaceApplication;
import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.domain.vo.FriendVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;


@SpringBootTest(classes = UniSpaceApplication.class)
class FriendMapperTest {


    @Resource
    private FriendMapper friendMapper;




    @Test
    void selectAll() throws SQLException{
        List<FriendVo> friends;
        friends = friendMapper.selectAll(1L,"小");
        friends.forEach(System.out::println);
    }

//
//    @Test
//    void selectByFromId() throws SQLException {
//        List<FriendVo> friendVos;
//        friendVos = friendMapper.selectByFromId((long) 1);
//        friendVos.forEach(System.out::println);
//    }


    @Test
    void selectByToId() throws SQLException {
        List<User> users;
        users = friendMapper.selectByToId((long)2);
        users.forEach(System.out::println);
    }

    @Test
    void insertOther() throws SQLException {
        int i = friendMapper.insertOther((long)1,(long)5);
        System.out.println(i);
    }

    @Test
    void insertEachOther() throws SQLException {
        int i = friendMapper.insertEachOther((long)5,(long)1);
        System.out.println(i);
    }


    @Test
    void updateFriendFlag() throws SQLException {
        int i = friendMapper.updateFriendFlag((long)2,(long)3);
        System.out.println(i);
    }

    @Test
    void deleteFriend() throws SQLException {
        int i = friendMapper.deleteFriend((long)1,(long)4);
        System.out.println(i);
    }

    @Test
    void selectFriendFlag() throws SQLException {
        Friend friend = friendMapper.selectFriendFlag((long)2,(long)3);
        System.out.println(friend);
    }


    @Test
    void deleteReject() throws SQLException {
        int i = friendMapper.deleteReject((long)3,(long)2);
        System.out.println(i);
    }

    @Test
    void updateCollectionFlag() throws SQLException {
        int i = friendMapper.updateCollectionFlag((long)2,(long)1);
        System.out.println(i);
    }

    @Test
    void searchFriendByKey() throws SQLException {

    }
}