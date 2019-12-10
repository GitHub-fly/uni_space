package com.scs.web.uni_space.mapper;

import com.scs.web.uni_space.UniSpaceApplication;

import com.scs.web.uni_space.domain.entity.Security;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;

@SpringBootTest(classes = UniSpaceApplication.class)
class SecurityMapperTest {

    @Resource private SecurityMapper securityMapper;

    private Security security;
    @Test
    void addAccountSecurity() throws SQLException {
        int i = securityMapper.addAccountSecurity((long)3,"烦得很","广泛单干户根据施工");
        System.out.println(i);
    }

    @Test
    void findAccountSecurity() throws SQLException {
        security = securityMapper.findAccountSecurity((long)1);
        System.out.println(security);
    }

    @Test
    void findPhotoAlbumSecurity() throws SQLException {
        security = securityMapper.findPhotoAlbumSecurity((long)1,(long)1);
        System.out.println(security);
    }

    @Test
    void addPhotoAlbumSecurity() throws SQLException {
        int i = securityMapper.addPhotoAlbumSecurity((long)1,(long)2,"国际化关键是","干啥讲话稿");
        System.out.println(i);
    }

    @Test
    void updateAccountSecurity() throws SQLException {
        int i = securityMapper.updateAccountSecurity((long)1,"回到家防静电还款计划","到花覅hig");
        System.out.println(i);
    }

    @Test
    void updatePhotoAlbumSecurity() throws SQLException {
        int i = securityMapper.updatePhotoAlbumSecurity((long)1,(long)1,"电饭锅","郭德纲接口");
        System.out.println(i);
    }

    @Test
    void deleteAccountSecurity() throws SQLException {
        int i = securityMapper.deleteAccountSecurity((long)1);
        System.out.println(i);
    }

    @Test
    void deletePhotoAlbumSecurity() throws SQLException {
        int i = securityMapper.deletePhotoAlbumSecurity((long)1,(long)3);
        System.out.println(i);

    }
}