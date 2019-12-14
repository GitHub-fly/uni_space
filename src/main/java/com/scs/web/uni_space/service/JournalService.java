package com.scs.web.uni_space.service;


import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.JournalDto;
import com.scs.web.uni_space.domain.dto.UserDto;


/**
 * @author wl
 * @ClassNameJournalService
 * @Description TODO
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
}