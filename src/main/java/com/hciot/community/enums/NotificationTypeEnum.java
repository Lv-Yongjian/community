package com.hciot.community.enums;

/**
 * @description: 通知回复了评论or问题的枚举
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
public enum NotificationTypeEnum {
    /**
     * 回复了问题
     */
    REPLY_QUESTION(1, "回复了问题"),
    /**
     * 回复了评论
     */
    REPLY_COMMENT(2, "回复了评论");
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * 通过通知的类型获取枚举
     *
     * @param type 通知的类型
     * @return java.lang.String
     * @author Lv-YongJian
     * @date 2020/2/28 14:36
     */
    public static String nameOfType(int type) {
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if (notificationTypeEnum.getType() == type) {
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
