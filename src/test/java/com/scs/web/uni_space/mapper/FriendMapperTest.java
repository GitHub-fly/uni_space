package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.UniSpaceApplication;
import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.domain.entity.Journal;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.domain.vo.UserVo;
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
        List<UserVo> friends;
        friends = friendMapper.selectAll(1L,"小");
        friends.forEach(System.out::println);
    }

    @Test
    void searchFriendByKey() throws SQLException {
        List<UserVo> list = friendMapper.searchUserByKey(1L, "猴子");
        list.forEach(System.out::println);
    }

    @Test
    void searchJournalByUserId() throws SQLException {
        List<Journal> list = friendMapper.searchJournalByUserId(1L);
        list.forEach(System.out::println);
    }

    @Test
    void selectByToId() throws SQLException {
        List<User> users;
        users = friendMapper.selectByToId((long)2);
        users.forEach(System.out::println);
    }

    @Test
    void insertOther() throws SQLException {
        friendMapper.insertOther((long)1,(long)5);
        System.out.println("成功");
    }

    @Test
    void insertEachOther() throws SQLException {
        friendMapper.insertEachOther((long)5,(long)1);
        System.out.println("成功");
    }


    @Test
    void updateFriendFlag() {
        try {
            friendMapper.updateFriendFlag(2L,1L);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("异常");
        }
    }

    @Test
    void deleteFriend() throws SQLException {
        friendMapper.deleteFriend((long)1,(long)4);
        System.out.println("成功");
    }

    @Test
    void selectFriendFlag() throws SQLException {
        Friend friend = friendMapper.selectFriendFlag((long)2,(long)3);
        System.out.println(friend);
    }


    @Test
    void deleteReject() throws SQLException {
        friendMapper.deleteReject((long)3,(long)2);
        System.out.println("成功");
    }

    @Test
    void updateCollectionFlag() throws SQLException {
        friendMapper.updateCollectionFlag((long)2,(long)1);
        System.out.println("成功");
    }




}