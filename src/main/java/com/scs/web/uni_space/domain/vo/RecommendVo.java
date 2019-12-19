package com.scs.web.uni_space.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author wl
 * @ClassNameRecommendVo
 * @Description TODO
 * @Date 2019/12/16
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendVo {
    private Long userId;
    private Long id;
    private String title;
    private String content;
    private String thumbnail;
    private Integer likes;
    private Timestamp createTime;

}
