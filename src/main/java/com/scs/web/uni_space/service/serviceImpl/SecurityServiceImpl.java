package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.entity.Security;
import com.scs.web.uni_space.mapper.SecurityMapper;
import com.scs.web.uni_space.service.SecurityService;
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
                //判断是否已经设置密保
                searchSecurity = securityMapper.findSecurity(security.getUserId(), security.getPhotoAlbumId());
            } catch (SQLException e) {
                log.error("查找相册密保异常");
            }
            if (searchSecurity != null) {
                return Result.failure(ResultCode.USER_HAS_SECURITY);
            } else {
                try {
                    //如未设置，添加密保
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
        //
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


    /**
     * 通过id查找密保
     *
     * @param queryDto
     * @return
     */
    @Override
    public Result findById(QueryDto queryDto) {
        if(queryDto.getId() != null){
            try {
                Security security = securityMapper.findById(queryDto.getId());
                return Result.success(security);
            } catch (SQLException e) {
                log.error("通过id查找密保");
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    @Override
    public Result updateSecurity(Security security) {
        if (security.getUserId() != null && security.getPhotoAlbumId() != null && security.getQuestion() != null && security.getAnswer() != null) {
            try {
                //更改密保信息
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
                //删除密保
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