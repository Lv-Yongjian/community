package com.hciot.community.dto;

import lombok.Data;

/**
 * @description: 创建回复DTO
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Data
public class CommentCreateDTO {
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 内容
     */
    private String content;
    /**
     * 类型
     */
    private Integer type;
}
