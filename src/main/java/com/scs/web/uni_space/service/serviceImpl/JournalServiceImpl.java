package com.scs.web.uni_space.service.serviceImpl;


import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import com.scs.web.uni_space.domain.dto.JournalDto;
import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.Comment;
import com.scs.web.uni_space.domain.entity.Journal;
import com.scs.web.uni_space.domain.entity.JournalPicture;
import com.scs.web.uni_space.domain.vo.JournalVo;
import com.scs.web.uni_space.domain.vo.UserCommentVo;
import com.scs.web.uni_space.mapper.JournalMapper;
import com.scs.web.uni_space.service.JournalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author wl
 * @ClassNameJournalServiceImpl
 * @Description TODO
 * @Date 2019/12/9
 * @Version 1.0
 */
@Service
@Slf4j
public class JournalServiceImpl implements JournalService {
    @Resource
    private JournalMapper journalMapper;

    @Override
    public Result findIndexData(UserDto userDto) {
        if (userDto.getId() != null) {
            try {

                List<com.scs.web.uni_space.domain.vo.JournalVo> list = journalMapper.findFriendJournal(userDto.getId());
                return Result.success(list);
            } catch (SQLException e) {
                log.error("查询失败");
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }

    @Override
    public Result selectById(UserDto userDto) {
        try {

            if (userDto.getId() != null) {
                List<Journal> list = journalMapper.selectById(userDto.getId());
                return Result.success(list);

            }
        } catch (SQLException e) {
            log.error("查找指定id用户的日志信息出错");
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }

    @Override
    public Result selectCommentById(JournalDto journalDto) {
        if (journalDto.getId()!=null){
            try {
                List<UserCommentVo> list=journalMapper.selectCommentById((long)journalDto.getId());
                return Result.success(list);
            } catch (SQLException e) {
                log.error("查找好友失败");
            }
        }
        return Result.failure(ResultCode.USER_NOT_EXIST);
    }

    @Override
    public Result selectJournalPictureById(JournalDto journalDto) {
        if (journalDto.getId()!=null) {
            try {
                List<JournalPicture> list = journalMapper.selectJournalPictureById((long) journalDto.getId());
                return Result.success(list);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Result.failure(ResultCode.USER_NOT_EXIST);
    }

    @Override
    public Result selectJournalDetailById(Long id) {
        if (id!=null){
            try {
                JournalVo journalVo = journalMapper.selectJournalById(id);
                return  Result.success(journalVo);
            } catch (SQLException e) {
                log.error("查询文章详情失败");
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }
}
