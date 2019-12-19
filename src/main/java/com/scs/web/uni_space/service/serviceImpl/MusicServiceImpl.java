package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import com.scs.web.uni_space.domain.dto.MusicDto;
import com.scs.web.uni_space.domain.vo.MusicVo;
import com.scs.web.uni_space.mapper.CommonMapper;
import com.scs.web.uni_space.mapper.MusicMapper;
import com.scs.web.uni_space.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName MusicServiceimpl
 * @Description 音乐操作类
 * @Author xiaobinggan
 * @Date 2019/12/19 8:47 上午
 * @Version 1.0
 **/
@Service
@Slf4j
public class MusicServiceImpl implements MusicService {
    @Resource
    private MusicMapper musicMapper;
    @Resource
    private CommonMapper commonMapper;

    @Override
    public Result addMusic(MusicDto musicDto) {
        try {
            commonMapper.returnId("t_music");
            musicMapper.addMusic(musicDto);
        } catch (SQLException e) {
            log.error("添加失败");
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
        return Result.success();
    }

    @Override
    public Result deleteMusic(MusicDto musicDto) {
        try {
            musicMapper.deleteMusic(musicDto.getUserId(), musicDto.getId());
        } catch (SQLException e) {
            log.error("添加失败");
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
        return Result.success();
    }

    @Override
    public Result selectById(MusicDto musicDto) {
        List<MusicVo> musicVoList;
        try {
            musicVoList = musicMapper.selectById(musicDto.getUserId());
        } catch (SQLException e) {
            log.error("查询失败");
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
        return Result.success(musicVoList);
    }

    @Override
    public Result selectAll() {
        List<MusicVo> musicVoList;
        try {
            musicVoList = musicMapper.selectAll();
        } catch (SQLException e) {
            log.error("获取失败");
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
        return Result.success(musicVoList);
    }
}
