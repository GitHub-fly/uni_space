package com.scs.web.uni_space.service;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.domain.dto.MusicDto;

/**
 * @author 22968
 */
public interface MusicService {
    /**
     * 查询所有音乐
     * @return
     */
    Result selectAll() ;

    /**
     * 根据ID查询音乐
     * @return
     */
    Result selectById(MusicDto musicDto);

    /**
     * 添加个人喜欢音乐
     * @param musicDto
     * @return
     */
    Result addMusic(MusicDto musicDto);

    /**
     * 移除个人喜欢音乐
     * @param musicDto
     * @return
     */
    Result deleteMusic(MusicDto musicDto);
}
