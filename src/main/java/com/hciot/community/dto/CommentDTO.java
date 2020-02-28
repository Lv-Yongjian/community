package com.hciot.community.dto;

import com.hciot.community.model.User;
import lombok.Data;

/**
 * @description: 评论内容DTO
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Data
public class CommentDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 父类id
     */
    private Long parentId;
    /**
     * 评论的类型
     */
    private Integer type;
    /**
     * 评论人id
     */
    private Long commentator;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     * 更新时间
     */
    private Long gmtModified;
    /**
     * 点赞数
     */
    private Long likeCount;
    /**
     * 阅读数
     */
    private Integer commentCount;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论人的头像，名称等信息
     */
    private User user;
}
