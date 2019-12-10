package com.scs.web.uni_space.service;

import com.scs.web.uni_space.common.Result;

/**
 * @author wl
 * @ClassNameJournalService
 * @Description TODO
 * @Date 2019/12/9
 * @Version 1.0
 */
public interface JournalService {
    /**
     * 查询用户所有的日志列表
     * @param id
     * @return
     */
   Result findUserAllJournal(Long id);

}
