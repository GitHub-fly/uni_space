package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.domain.entity.Journal;
import com.scs.web.uni_space.mapper.JournalMapper;

import com.scs.web.uni_space.service.JournalService;
import com.scs.web.uni_space.util.Result;
import com.scs.web.uni_space.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            List<Journal> journals =journalMapper.findAllJounal((long) id);

            if (journals!=null){

                return  Result.success(journals,journals.size());

            }
        } catch (SQLException e) {
            log.error("查找失败");
        }
       return Result.success("此用户还未添加日志");
    }
}
