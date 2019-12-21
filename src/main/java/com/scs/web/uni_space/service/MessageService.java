package com.scs.web.uni_space.service;


import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.QueryDto;

/**
 * @author suyuxi
 * @className MessageService
 * @Description 消息操作接口
 * @Date 2019/12/21
 * @Version 1.0
 **/

public interface MessageService {


    /**
     * 查找所有点赞
     *
     * @param queryDto
     * @return
     */
    Result findAllLike(QueryDto queryDto);
}
