package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.domain.entity.Security;
import com.scs.web.uni_space.mapper.SecurityMapper;
import com.scs.web.uni_space.service.SecurityService;
import com.scs.web.uni_space.util.Result;
import com.scs.web.uni_space.util.ResultCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author suyuxi
 * @className SecurityServiceImpl
 * @Description 密保操作类
 * @Date 2019/12/5
 * @Version 1.0
 **/

@Service
public class SecurityServiceImpl implements SecurityService {

    @Resource
    private SecurityMapper securityMapper;


    @Override
    public Result addAccountSecurity(Security security) {
        Security searchSecurity = securityMapper.findAccountSecurity(security.getUserId());
        if (searchSecurity != null) {
            return Result.failure(ResultCode.USER_HAS_ACCOUNT_SECURITY);
        } else {
            int i = securityMapper.addAccountSecurity(security.getUserId(), security.getQuestion(), security.getAnswer());
            if (i != 0) {
                return Result.success(ResultCode.SUCCESS);
            } else {
                return Result.failure(ResultCode.USER_ADD_ACCOUNT_SECURITY_ERROR);
            }
        }
    }

    @Override
    public Result addPhotoAlbumSecurity(Security security) {
        Security searchSecurity = securityMapper.findPhotoAlbumSecurity(security.getUserId(), security.getPhotoAlbumId());
        if (searchSecurity != null) {
            return Result.failure(ResultCode.USER_HAS_PHOTO_ALBUM_SECURITY);
        } else {
            int i = securityMapper.addPhotoAlbumSecurity(security.getUserId(), security.getPhotoAlbumId(), security.getQuestion(), security.getAnswer());
            if (i != 0) {
                return Result.success(ResultCode.SUCCESS);
            } else {
                return Result.failure(ResultCode.USER_ADD_PHOTO_ALBUM_SECURITY_ERROR);
            }
        }

    }

    @Override
    public Result findAccountSecurity(Security security) {
        Security  searchSecurity = securityMapper.findAccountSecurity(security.getUserId());
        if (searchSecurity != null) {
            return Result.failure(ResultCode.SUCCESS,searchSecurity);
        } else {
            return Result.failure(ResultCode.USER_FIND_ACCOUNT_SECURITY_ERROR);
        }
    }

    @Override
    public Result findPhotoAlbumSecurity(Security security) {
        Security searchSecurity = securityMapper.findPhotoAlbumSecurity(security.getUserId(), security.getPhotoAlbumId());
        System.out.println(searchSecurity);

        if (searchSecurity != null) {
            return Result.success(searchSecurity);
        } else {
            return Result.failure(ResultCode.USER_FIND_PHOTO_ALBUM_SECURITY_ERROR);
        }
    }

    @Override
    public Result updateAccountSecurity(Security security) {
        int i = securityMapper.updateAccountSecurity(security.getUserId(), security.getQuestion(), security.getAnswer());
        if (i != 0) {
            return Result.success(ResultCode.SUCCESS);
        } else {
            return Result.failure(ResultCode.USER_UPDATE_ACCOUNT_SECURITY_ERROR);

        }
    }

    @Override
    public Result updatePhotoAlbumSecurity(Security security) {
        int i = securityMapper.updatePhotoAlbumSecurity(security.getUserId(), security.getPhotoAlbumId(), security.getQuestion(), security.getAnswer());
        if (i != 0) {
            return Result.success(ResultCode.SUCCESS);
        } else {
            return Result.failure(ResultCode.USER_UPDATE_PHOTO_ALBUM_SECURITY_ERROR);
        }
    }

    @Override
    public Result deleteAccountSecurity(Security security) {
        int i = securityMapper.deleteAccountSecurity(security.getUserId());
        if (i != 0) {
            return Result.success(ResultCode.SUCCESS);
        } else {
            return Result.failure(ResultCode.USER_DELETE_ACCOUNT_SECURITY_ERROR);
        }
    }

    @Override
    public Result deletePhotoAlbumSecurity(Security security) {
        int i = securityMapper.deletePhotoAlbumSecurity(security.getUserId(), security.getPhotoAlbumId());
        if (i != 0) {
            return Result.success(ResultCode.SUCCESS);
        } else {
            return Result.failure(ResultCode.USER_DELETE_PHOTO_ALBUM_SECURITY_ERROR);
        }
    }
}
