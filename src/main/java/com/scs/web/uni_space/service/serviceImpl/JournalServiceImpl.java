package com.scs.web.uni_space.service.serviceImpl;


import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import com.scs.web.uni_space.domain.dto.JournalDto;
import com.scs.web.uni_space.domain.dto.LikeDto;
import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.Journal;
import com.scs.web.uni_space.domain.entity.JournalPicture;
import com.scs.web.uni_space.domain.entity.Like;
import com.scs.web.uni_space.domain.vo.JournalVo;
import com.scs.web.uni_space.domain.vo.RecommendVo;
import com.scs.web.uni_space.domain.vo.UserCommentVo;
import com.scs.web.uni_space.mapper.JournalMapper;
import com.scs.web.uni_space.service.JournalService;
import com.scs.web.uni_space.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wl
 * @ClassNameJournalServiceImpl
 * @Description 日志服务类
 * @Date 2019/12/9
 * @Version 1.0
 */
@Service
@Slf4j
public class JournalServiceImpl implements JournalService {
    @Resource
    private JournalMapper journalMapper;
//    @Resource
//    private CommonMapper commonMapper;

    @Override
    public Result findIndexData(UserDto userDto) {
        if (userDto.getId() != null) {
            try {
                //通过用户id查找好友日志
                List<JournalVo> list = journalMapper.findFriendJournal(userDto.getId());
                return Result.success(list);
            } catch (SQLException e) {
                log.error("查询失败");
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }

    @Override
    public Result recommendFriendJournal(UserDto userDto) {
        if (userDto.getId() != null) {
            try {
                //通过uer_id获取推荐好友
                List<RecommendVo> list = journalMapper.recommendJournal(userDto.getId());
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
            //非空判断
            if (userDto.getId() != null) {
                //通过 id查找用户
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
        if (journalDto.getId() != null) {
            try {
                //查找文章所有评论
                List<UserCommentVo> list = journalMapper.selectCommentById((long) journalDto.getId());
                return Result.success(list);
            } catch (SQLException e) {
                log.error("查找好友失败");
            }
        }
        return Result.failure(ResultCode.USER_NOT_EXIST);
    }

    @Override
    public Result selectJournalPictureById(JournalDto journalDto) {
        if (journalDto.getId() != null) {
            try {
                //通过日志id查找日志中照片
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
        if (id != null) {
            try {
                //通过用户id查找日志详情
                JournalVo journalVo = journalMapper.selectJournalById(id);
                return Result.success(journalVo);
            } catch (SQLException e) {
                log.error("查询文章详情失败");
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    @Override
    public Result concernJournalLikes(LikeDto likeDto) {
            //通过user_id 和 日志id

        try {
            Like like = journalMapper.concernJournalLike((long) likeDto.getUserId(), (long) likeDto.getJournalId());
            if (like != null) {
                return Result.success();
            }
        } catch (SQLException e) {
            log.error("查询失败");
        }

        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }

    @Override
    public Result clickLikes(LikeDto likeDto) {
        try {
            //通过用户id 日志id判断有无点赞
            if (journalMapper.concernJournalLike((long) likeDto.getUserId(), (long) likeDto.getJournalId()) == null) {
//                commonMapper.returnId("t_like");
                //通过用户id 日志id点赞
                journalMapper.insertLike((long) likeDto.getUserId(), (long) likeDto.getJournalId());
                //更新journal表likes
                journalMapper.updateLikes((long) likeDto.getJournalId());
                return Result.success();
            }
        } catch (SQLException e) {
            log.error("插入点赞失败");
        }

        return Result.failure(ResultCode.DATA_ALREADY_EXISTED);
    }

    @Override
    public Result cancelLike(LikeDto likeDto) {
        try {
            //通过用户id 日志id判断有无点赞
            if (journalMapper.concernJournalLike((long) likeDto.getUserId(), (long) likeDto.getJournalId()) != null) {
                //通过用户id 日志id删除点赞
                journalMapper.deleteLike((long) likeDto.getUserId(), (long) likeDto.getJournalId());
                //更新journal表likes
                journalMapper.updateLikes((long) likeDto.getJournalId());
                return Result.success();
            }
        } catch (SQLException e) {
            log.error("取消点赞失败");
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);

    }

    @Override
    public Result addJournal(JournalDto journalDto) {

        //传参数不为空
        if (journalDto.getContent() != null || journalDto.getUserId() != null) {
            List<String> list = StringUtil.getImgSrc(journalDto.getContent());
            Journal journal = new Journal();
            journal.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            System.out.println(journal.getCreateTime());
            journal.setUserId(journalDto.getUserId());
            journal.setTitle(journalDto.getTitle());

            journal.setContent(journalDto.getContent().substring(2,journalDto.getContent().length()-2));
            journal.setJournalPictureNum((long) list.size());
            if (list.size() == 0) {
                journal.setThumbnail("https://niit-soft.oss-cn-hangzhou.aliyuncs.com/soft1821/c3cef33b-a373-4dc6-b529-7983a39f72df.jpeg");
            } else {
                journal.setThumbnail(list.get(0));
            }
            try {
//                commonMapper.returnId("t_journal");
                //新增日志
                journalMapper.insertJournal(journal);

                if (list.size() != 0) {
                    List<JournalPicture> pictureList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        JournalPicture journalPicture = new JournalPicture();
                        journalPicture.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
                        //返回自增id
                        journalPicture.setJournalId(journal.getId());
                        journalPicture.setUrl(list.get(i));
                        pictureList.add(journalPicture);
                    }
                    if (journalDto.getContent() != null || journalDto.getUserId() != null) {
//                        commonMapper.returnId("t_journal_photo");
                        //批量插入照片
                        journalMapper.batchInsertJournal(pictureList);
                    }
                }
                return Result.success("新增日志成功");
            } catch (SQLException e) {
                log.error("插入失败");
            }
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @Override
    public Result addMobileJournal(JournalDto journalDto) {
        //非空判断
        if (journalDto.getContent() != null || journalDto.getUserId() != null) {
            //将前端图片数组 转为list集合
            List<String> urlList = Arrays.asList(journalDto.getUrls());
            Journal journal = new Journal();
            journal.setJournalPictureNum((long) urlList.size());
            journal.setContent(journalDto.getContent());
            journal.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            journal.setTitle(journalDto.getTitle());
            journal.setUserId(journalDto.getUserId());
            if (urlList.size() == 0) {
                journal.setThumbnail("https://niit-soft.oss-cn-hangzhou.aliyuncs.com/soft1821/c3cef33b-a373-4dc6-b529-7983a39f72df.jpeg");
            } else {
                journal.setThumbnail(urlList.get(0));
            }
            try {
//                commonMapper.returnId("t_journal");
                //新增日志
                journalMapper.insertJournal(journal);
                if (urlList.size() != 0) {
                    List<JournalPicture> journalPictureList = new ArrayList<>();
                    for (int i = 0; i < urlList.size(); i++) {
                        JournalPicture journalPicture = new JournalPicture();
                        journalPicture.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
                        //获取自增id
                        journalPicture.setJournalId(journal.getId());
                        journalPicture.setUrl(urlList.get(i));
                        journalPictureList.add(journalPicture);
                    }
//                    commonMapper.returnId("t_journal_photo");
                    //批量插入照片
                    journalMapper.batchInsertJournal(journalPictureList);
                }
                return Result.success("新增日志成功");
            } catch (SQLException e) {
                log.error("新增失败");
            }
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @Override
    public Result deleteJournal(JournalDto journalDto) {
        if (journalDto.getLongList().size() != 0) {
            try {
                //批量删除日志
                journalMapper.batchDeleteJournal(journalDto.getLongList());
                //批量删除日志中的照片e
                journalMapper.batchDeleteJournalPicture(journalDto.getLongList());
                return Result.success();
            } catch (SQLException e) {
                log.error("删除失败");
            }
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);

    }
}