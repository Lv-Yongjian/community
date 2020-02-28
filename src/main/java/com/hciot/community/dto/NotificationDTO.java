package com.hciot.community.dto;

import lombok.Data;

/**
 * @description: 通知DTO
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Data
public class NotificationDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     * 状态：已读（1）or未读（0）
     */
    private Integer status;
    /**
     * 通知者id
     */
    private Long notifier;
    /**
     * 通知者名称
     */
    private String notifierName;
    /**
     * 问题的标题
     */
    private String outerTitle;
    /**
     * 问题的id
     */
    private Long outerid;
    /**
     * 通知类型的名称（回复了评论or问题）
     */
    private String typeName;
    /**
     * 通知的类型（通知评论or问题）
     */
    private Integer type;
}
