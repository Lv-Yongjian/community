package com.hciot.community.enums;

public enum NotificationStatusEnum {
    //阅读或未阅读
    UNREAD(0),
    READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
