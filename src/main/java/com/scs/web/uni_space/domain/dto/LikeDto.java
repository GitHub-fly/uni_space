package com.scs.web.uni_space.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wl
 * @ClassNamel
 * @Description 点赞传输类
 * @Date 2019/12/15
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeDto {
    Long userId;
    Long journalId;
    Integer likes;
}
