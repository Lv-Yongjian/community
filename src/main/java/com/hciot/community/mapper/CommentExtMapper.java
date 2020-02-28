package com.hciot.community.mapper;

import com.hciot.community.model.Comment;

/**
 * @description: 自定义 comment 类 mapper 方法
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
public interface CommentExtMapper {
    /**
     * 新增评论回复数
     *
     * @param comment
     * @return int
     * @author Lv-YongJian
     * @date 2020/2/28 16:05
     */
    int incCommentCount(Comment comment);
}