package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import com.scs.web.uni_space.domain.dto.QueryDto;
import com.scs.web.uni_space.domain.vo.LikeVo;
import com.scs.web.uni_space.mapper.MessageMapper;
import com.scs.web.uni_space.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author suyuxi
 * @className MessageImpl
 * @Description 消息服务类
 * @Date 2019/12/21
 * @Version 1.0
 **/
@Service
@Slf4j
public class MessageImpl implements MessageService {

    @Resource MessageMapper messageMapper;

    /**
     * 查找点赞消息
     *
     * @param queryDto
     * @return
     */
    @Override
    public Result findAllLike(QueryDto queryDto) {
        if (queryDto.getId() != null){
            try {
                //调用查找该用户所有点赞信息
                List<LikeVo> likeVoList = messageMapper.findAllLike(queryDto);
                return Result.success(likeVoList);
            } catch (SQLException e) {
                log.error("查找点赞消息异常");
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }

}
