package com.hciot.community.dto;

import com.hciot.community.model.User;
import lombok.Data;

/**
 * @description: 问题DTO
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Data
public class QuestionDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String description;
    /**
     * 标签
     */
    private String tag;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     * 更新时间
     */
    private Long gmtModified;
    /**
     * 创建者
     */
    private Long creator;
    /**
     * 阅读数
     */
    private Integer viewCount;
    /**
     * 回复数
     */
    private Integer commentCount;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 用户
     */
    private User user;
}
