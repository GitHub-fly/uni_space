package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.UniSpaceApplication;
import com.scs.web.uni_space.domain.dto.SignDto;
import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.domain.entity.Like;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.util.JSoupSpider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest(classes = UniSpaceApplication.class)
class UserMapperTest {
    private static Random random = new Random();
    @Resource
    private UserMapper userMapper;

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
    void updateUserData() throws SQLException {
        User user = new User();
        user.setId((long) 1);
        user.setNickname("张三");
        user.setAddress("中国南京");
        user.setGender("男");
        user.setIntroduction("开开心心过大年");
        user.setConstellation("摩羯座");
        user.setBirthday(LocalDate.now());
        user.setAvatar("http://attach.bbs.miui.com/forum/201305/05/201954rwwc6ceji1jcka91.jpg");
        userMapper.updateUserData(user);
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
            friend.setFromId((long) i);
            friend.setToId((long) (random.nextInt(71) + 1));
            friend.setFriendFlag((short) random.nextInt(2));
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
            like.setUserId((long) i);
            like.setJournalId((long) (random.nextInt(71) + 1));
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


    @Test
    void updateUserAvatar() throws SQLException {
        userMapper.updateUserAvatar("https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/ed3216ca-a117-4ca1-b1f7-54ecb3ae68b0.jpeg", 1L);
    }


    @Test
    void findUserBy() {
        SignDto signDto =SignDto.builder().Name("1").build();

        try {
            User user=   userMapper.findUserBy(signDto);
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}