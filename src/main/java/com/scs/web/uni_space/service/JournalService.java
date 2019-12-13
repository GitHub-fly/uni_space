package com.scs.web.uni_space.service;


import com.scs.web.uni_space.common.Result;
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

}
