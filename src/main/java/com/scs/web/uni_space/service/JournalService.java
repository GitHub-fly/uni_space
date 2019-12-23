package com.scs.web.uni_space.service;


import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.JournalDto;
import com.scs.web.uni_space.domain.dto.LikeDto;
import com.scs.web.uni_space.domain.dto.UserDto;


/**
 * @author wl
 * @ClassNameJournalService
 * @Description 日志服务类
 * @Date 2019/12/9
 * @Version 1.0
 */
public interface JournalService {
  /**
   * 首页数据展示
   *
   * @param userDto
   * @return
   */
  Result findIndexData(UserDto userDto);

  /**
   * 推荐日志
   * @param userDto
   * @return
   */

  Result recommendFriendJournal(UserDto userDto);
  /**
   * 通过指定用户id查找改用户的所有日志信息
   *
   * @param userDto
   * @return
   */
  Result selectById(UserDto userDto);

  /**
   * 通过文章id查询文章评论数
   *
   * @param journalDto
   * @return
   */
  Result selectCommentById(JournalDto journalDto);

  /**
   * 通过日志id查到日志中的相册
   * @param journalDto
   * @return
   */
  Result selectJournalPictureById(JournalDto journalDto);

  /**
   * 通过日志id查找日志详情
   * @param id
   * @return
   */
  Result selectJournalDetailById(Long id);


  /**
   * 判断是否点赞
   * @param likeDto
   * @return
   */
  Result concernJournalLikes(LikeDto likeDto);
  /**
   * 点赞功能
   * @param likeDto
   * @return
   */
  Result clickLikes(LikeDto likeDto);

  /**
   * 取消点赞
   *
   * @param likeDto
   * @return
   */

  Result cancelLike(LikeDto likeDto);

  /**
   * pc  新增日志
   *
   * @param journalDto
   * @return
   */
  Result addJournal(JournalDto journalDto);

  /**
   * 手机新增日志
   *
   * @param journalDto
   * @return
   */

  Result addMobileJournal(JournalDto journalDto);

  /**
   * 批量删除日志
   * @param journalDto
   */
  Result  deleteJournal(JournalDto journalDto);
}