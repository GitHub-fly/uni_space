package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.UniSpaceApplication;
import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.domain.entity.Like;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.util.JSoupSpider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest(classes = UniSpaceApplication.class)
class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    private static Random random = new Random();

    @Test
    void selectUserByMobile() throws SQLException {
        User user = userMapper.selectUserByMobile("13222222222");
        System.out.println(user);
    }

    @Test
    void selectUserByAccount() {
    }

    @Test
    void selectUserByEmail() {
    }

    @Test
    void insertUser() {
    }

    @Test
    void updateUserData() {
    }

    @Test
    void batchInsertUser() {
        userMapper.batchInsertUser(JSoupSpider.getUsers());
    }

    @Test
    void batchInsertFriend() {
        List<Friend> list = new ArrayList<>();
        int i;
        for (i = 1; i <= 72; i++) {
            Friend friend = new Friend();
            friend.setFromId((long)i);
            friend.setToId((long)(random.nextInt(71) + 1));
            friend.setFriendFlag((short)random.nextInt(2));
            list.add(friend);
        }
        userMapper.batchInsertFriend(list);
    }

    @Test
    void batchInsertLike() {
        List<Like> list = new ArrayList<>();
        int i;
        for (i = 1; i <= 72; i++) {
            Like like = new Like();
            like.setUserId((long)i);
            like.setJournalId((long)(random.nextInt(71) + 1));
            list.add(like);
        }
        userMapper.batchInsertLike(list);
    }

    @Test
    void batchAddJournal() {
        userMapper.batchInsertJournal(JSoupSpider.getJournal());
    }

    @Test
    void batchInsertPhoto() {
        userMapper.batchInsertPhoto(JSoupSpider.getPhoto());
    }

    @Test
    void batchInsertPhotoAlbum() {
        userMapper.batchInsertPhotoAlbum(JSoupSpider.getPhotoAlbum());
    }


}