package com.hciot.community.enums;

/**
 * @description: 通知状态枚举
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
public enum NotificationStatusEnum {
    /**
     * 未读
     */
    UNREAD(0),
    /**
     * 已读
     */
    READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
