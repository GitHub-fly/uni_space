package com.scs.web.uni_space.controller;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author suyuxi
 * @className MessageController
 * @Description 消息控制层
 * @Date 2019/12/21
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/api/message")
@Api(value = "MessageController", tags = {"消息模块接口"})
public class MessageController {

    @Resource MessageService messageService;

    @ApiOperation(value = "根据用户id查找所有点赞消息", notes = "传递参数为id，data为点赞信息列表")
    @PostMapping(value = "/likes")
    Result findAllLike(@RequestBody QueryDto queryDto) {
        return messageService.findAllLike(queryDto);
    }

}
