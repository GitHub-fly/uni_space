package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.UserDto;
import com.scs.web.uni_space.service.JournalService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wl
 * @ClassNameJournalController
 * @Description 日志接口
 * @Date 2019/12/9
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/journal")

public class JournalController {

    @Resource
    private JournalService journalService;

    /**
     * 首页数据展示
     *
     * @param userDto
     * @return
     */
    @PostMapping(value = "/index/data")
    Result findIndexData(@RequestBody UserDto userDto) {
        return journalService.findIndexData(userDto);
    }


    /**
     * 通过指定用户id查找改用户的所有日志信息
     *
     * @param userDto
     * @return
     */
    @PostMapping(value = "/user/data")
    Result selectById(@RequestBody UserDto userDto) {
        return journalService.selectById(userDto);
    }

}
