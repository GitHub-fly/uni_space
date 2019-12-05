package com.scs.web.uni_space.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author wl
 * @ClassNameUpDownService
 * @Description TODO
 * @Date 2019/12/4
 * @Version 1.0
 */
public interface UpDownService {
    String updateHead(MultipartFile file, long userId) throws Exception;
}
