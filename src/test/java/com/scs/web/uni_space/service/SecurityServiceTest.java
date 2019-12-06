package com.scs.web.uni_space.service;

import com.scs.web.uni_space.UniSpaceApplication;
import com.scs.web.uni_space.domain.entity.Security;
import com.scs.web.uni_space.service.serviceImpl.SecurityServiceImpl;
import com.scs.web.uni_space.util.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest(classes = UniSpaceApplication.class)
class SecurityServiceTest {


    @Resource
    private SecurityServiceImpl securityServiceImpl;

    private Result result = null;

    @Test
    void addAccountSecurity() {
        Security security = new Security();
        security.setUserId((long) 2);
        security.setQuestion("上的会感到恐惧");
        security.setAnswer("赶紧告诉大家赶紧");
        result = securityServiceImpl.addAccountSecurity(security);
        System.out.println(result);

    }

    @Test
    void addPhotoAlbumSecurity() {
        Security security = new Security();
        security.setUserId((long) 2);
        security.setPhotoAlbumId((long)1);
        security.setQuestion("上的会感到恐惧");
        security.setAnswer("赶紧告诉大家赶紧");
        result = securityServiceImpl.addPhotoAlbumSecurity(security);
        System.out.println(result);
    }

    @Test
    void findAccountSecurity() {
        Security security = new Security();
        security.setUserId((long) 1);
        result = securityServiceImpl.findAccountSecurity(security);
        System.out.println(result);

    }

    @Test
    void findPhotoAlbumSecurity() {
        Security security = new Security();
        security.setUserId((long) 1);
        security.setPhotoAlbumId((long)2);
        result = securityServiceImpl.findPhotoAlbumSecurity(security);
        System.out.println(result);
    }

    @Test
    void updateAccountSecurity() {
        Security security = new Security();
        security.setUserId((long) 3);
        security.setQuestion("上的会感到恐惧");
        security.setAnswer("赶紧告诉大家赶紧");
        result = securityServiceImpl.updateAccountSecurity(security);
        System.out.println(result);
    }

    @Test
    void updatePhotoAlbumSecurity() {
        Security security = new Security();
        security.setUserId((long) 3);
        security.setPhotoAlbumId((long) 1);
        security.setQuestion("上的会感到恐惧");
        security.setAnswer("赶紧告诉大家赶紧");
        result = securityServiceImpl.updatePhotoAlbumSecurity(security);
        System.out.println(result);
    }

    @Test
    void deleteAccountSecurity() {
        Security security = new Security();
        security.setUserId((long) 3);
        result = securityServiceImpl.deleteAccountSecurity(security);
        System.out.println(result);
    }

    @Test
    void deletePhotoAlbumSecurity() {
        Security security = new Security();
        security.setUserId((long) 1);
        security.setPhotoAlbumId((long) 1);
        result = securityServiceImpl.deletePhotoAlbumSecurity(security);
        System.out.println(result);
    }
}