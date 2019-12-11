package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.domain.entity.Security;
import com.scs.web.uni_space.mapper.SecurityMapper;
import com.scs.web.uni_space.service.SecurityService;
import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @author suyuxi
 * @className SecurityServiceImpl
 * @Description 密保操作类
 * @Date 2019/12/5
 * @Version 1.0
 **/

@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Resource
    private SecurityMapper securityMapper;


    @Override
    public Result addAccountSecurity(Security security) {
        if (security.getUserId() != null && security.getQuestion() != null && security.getAnswer() != null){
            Security searchSecurity = null;
            try {
                searchSecurity = securityMapper.findAccountSecurity(security.getUserId());
            } catch (SQLException e) {
                log.error("查找账号密保异常");
            }
            if (searchSecurity != null) {
                return Result.failure(ResultCode.USER_HAS_ACCOUNT_SECURITY);
            } else {
                int i = 0;
                try {
                    i = securityMapper.addAccountSecurity(security.getUserId(), security.getQuestion(), security.getAnswer());
                } catch (SQLException e) {
                    log.error("添加账号密保异常");
                }
                if (i != 0) {
                    return Result.success(ResultCode.SUCCESS);
                } else {
                    return Result.failure(ResultCode.USER_ADD_ACCOUNT_SECURITY_ERROR);
                }
            }
        }else {
            return Result.failure(ResultCode.USER_SECURITY_DATA_ERROR);
        }

    }

    @Override
    public Result addPhotoAlbumSecurity(Security security) {
        if (security.getUserId() != null && security.getPhotoAlbumId() != null && security.getQuestion() != null && security.getAnswer() != null){
            Security searchSecurity = null;
            try {
                searchSecurity = securityMapper.findPhotoAlbumSecurity(security.getUserId(), security.getPhotoAlbumId());
            } catch (SQLException e) {
                log.error("查找相册密保异常");
            }
            if (searchSecurity != null) {
                return Result.failure(ResultCode.USER_HAS_PHOTO_ALBUM_SECURITY);
            } else {
                int i = 0;
                try {
                    i = securityMapper.addPhotoAlbumSecurity(security.getUserId(), security.getPhotoAlbumId(), security.getQuestion(), security.getAnswer());
                } catch (SQLException e) {
                    log.error("添加相册密保异常");
                }
                if (i != 0) {
                    return Result.success(ResultCode.SUCCESS);
                } else {
                    return Result.failure(ResultCode.USER_ADD_PHOTO_ALBUM_SECURITY_ERROR);
                }
            }
        }else {
            return Result.failure(ResultCode.USER_SECURITY_DATA_ERROR);
        }
    }

    @Override
    public Result findAccountSecurity(Security security) {
        if (security.getUserId() != null){
            Security  searchSecurity = null;
            try {
                searchSecurity = securityMapper.findAccountSecurity(security.getUserId());
            } catch (SQLException e) {
                log.error("查找账号密保异常");
            }
            if (searchSecurity != null) {
                return Result.success(searchSecurity);
            } else {
                return Result.failure(ResultCode.USER_FIND_ACCOUNT_SECURITY_ERROR);
            }
        }else {
            return Result.failure(ResultCode.USER_SECURITY_DATA_ERROR);
        }

    }

    @Override
    public Result findPhotoAlbumSecurity(Security security) {
        if (security.getUserId() != null && security.getPhotoAlbumId() != null){
            Security searchSecurity = null;
            try {
                searchSecurity = securityMapper.findPhotoAlbumSecurity(security.getUserId(), security.getPhotoAlbumId());
            } catch (SQLException e) {
                log.error("查找相册密保异常");
            }
            System.out.println(searchSecurity);

            if (searchSecurity != null) {
                return Result.success(searchSecurity);
            } else {
                return Result.failure(ResultCode.USER_FIND_PHOTO_ALBUM_SECURITY_ERROR);
            }
        }else {
            return Result.failure(ResultCode.USER_SECURITY_DATA_ERROR);
        }
    }

    @Override
    public Result updateAccountSecurity(Security security) {
        if (security.getUserId() != null && security.getQuestion() != null && security.getAnswer() != null){
            int i = 0;
            try {
                i = securityMapper.updateAccountSecurity(security.getUserId(), security.getQuestion(), security.getAnswer());
            } catch (SQLException e) {
                log.error("更改账号密保异常");
            }
            if (i != 0) {
                return Result.success(ResultCode.SUCCESS);
            } else {
                return Result.failure(ResultCode.USER_UPDATE_ACCOUNT_SECURITY_ERROR);

            }
        }else{
            return Result.failure(ResultCode.USER_SECURITY_DATA_ERROR);
        }
    }

    @Override
    public Result updatePhotoAlbumSecurity(Security security) {
        if (security.getUserId() != null && security.getPhotoAlbumId() != null && security.getQuestion() != null && security.getAnswer() != null){
            int i = 0;
            try {
                i = securityMapper.updatePhotoAlbumSecurity(security.getUserId(), security.getPhotoAlbumId(), security.getQuestion(), security.getAnswer());
            } catch (SQLException e) {
                log.error("更改相册密保异常");
            }
            if (i != 0) {
                return Result.success(ResultCode.SUCCESS);
            } else {
                return Result.failure(ResultCode.USER_UPDATE_PHOTO_ALBUM_SECURITY_ERROR);
            }
        }else {
            return Result.failure(ResultCode.USER_SECURITY_DATA_ERROR);
        }
    }

    @Override
    public Result deleteAccountSecurity(Security security) {
        if (security.getUserId() != null){
            int i = 0;
            try {
                i = securityMapper.deleteAccountSecurity(security.getUserId());
            } catch (SQLException e) {
                log.error("删除账号密保异常");
            }
            if (i != 0) {
                return Result.success(ResultCode.SUCCESS);
            } else {
                return Result.failure(ResultCode.USER_DELETE_ACCOUNT_SECURITY_ERROR);
            }
        }else {
            return Result.failure(ResultCode.USER_SECURITY_DATA_ERROR);
        }
    }

    @Override
    public Result deletePhotoAlbumSecurity(Security security) {
        if (security.getUserId() != null && security.getPhotoAlbumId() != null){
            int i = 0;
            try {
                i = securityMapper.deletePhotoAlbumSecurity(security.getUserId(), security.getPhotoAlbumId());
            } catch (SQLException e) {
                log.error("删除相册密保异常");
            }
            if (i != 0) {
                return Result.success(ResultCode.SUCCESS);
            } else {
                return Result.failure(ResultCode.USER_DELETE_PHOTO_ALBUM_SECURITY_ERROR);
            }
        }else {
            return Result.failure(ResultCode.USER_SECURITY_DATA_ERROR);
        }
    }
}
