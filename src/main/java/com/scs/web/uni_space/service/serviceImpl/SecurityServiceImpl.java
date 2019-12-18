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
    public Result addSecurity(Security security) {
        if (security.getUserId() != null && security.getPhotoAlbumId() != null && security.getQuestion() != null && security.getAnswer() != null) {
            Security searchSecurity = null;
            try {
                searchSecurity = securityMapper.findSecurity(security.getUserId(), security.getPhotoAlbumId());
            } catch (SQLException e) {
                log.error("查找相册密保异常");
            }
            if (searchSecurity != null) {
                return Result.failure(ResultCode.USER_HAS_SECURITY);
            } else {
                try {
                    securityMapper.addSecurity(security.getUserId(), security.getPhotoAlbumId(), security.getQuestion(), security.getAnswer());
                    return Result.success(ResultCode.SUCCESS);
                } catch (SQLException e) {
                    log.error("添加相册密保异常");
                    return Result.failure(ResultCode.USER_ADD_SECURITY_ERROR);
                }
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    @Override
    public Result findSecurity(Security security) {
        if (security.getUserId() != null && security.getPhotoAlbumId() != null) {
            Security searchSecurity = null;
            try {
                searchSecurity = securityMapper.findSecurity(security.getUserId(), security.getPhotoAlbumId());
            } catch (SQLException e) {
                log.error("查找相册密保异常");
            }
            if (searchSecurity != null) {
                return Result.success(searchSecurity);
            } else {
                return Result.failure(ResultCode.USER_NOT_SECURITY);
            }
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }



    @Override
    public Result updateSecurity(Security security) {
        if (security.getUserId() != null && security.getPhotoAlbumId() != null && security.getQuestion() != null && security.getAnswer() != null) {
            try {
                securityMapper.updateSecurity(security.getUserId(), security.getPhotoAlbumId(), security.getQuestion(), security.getAnswer());
                return Result.success(ResultCode.SUCCESS);
            } catch (SQLException e) {
                log.error("更改相册密保异常");
                return Result.failure(ResultCode.USER_UPDATE_SECURITY_ERROR);
            }
        }
        return Result.failure(ResultCode.USER_UPDATE_SECURITY_ERROR);
    }


    @Override
    public Result deleteSecurity(Security security) {
        if (security.getUserId() != null && security.getPhotoAlbumId() != null) {
            try {
                securityMapper.deleteSecurity(security.getUserId(), security.getPhotoAlbumId());
                return Result.success(ResultCode.SUCCESS);
            } catch (SQLException e) {
                log.error("删除相册密保异常");
                return Result.failure(ResultCode.USER_DELETE_SECURITY_ERROR);
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }
}
