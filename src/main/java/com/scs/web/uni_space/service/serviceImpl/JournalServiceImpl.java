package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.entity.Journal;
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

    public Result findUserAllJournal(Long id) {
        try {
            List<Journal> journals =journalMapper.findAllJournal((long) id);

            if (journals!=null){

                return  Result.success(journals);

            }
        } catch (SQLException e) {
            log.error("查找失败");
        }
       return Result.success("此用户还未添加日志");
    }
}
