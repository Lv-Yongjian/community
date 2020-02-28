package com.hciot.community.enums;

/**
 * @description: 回复类型枚举：回复问题还是评论
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
public enum CommentTypeEnum {
    /**
     * 问题
     */
    QUESTION(1),
    /**
     * 评论
     */
    COMMENT(2);

    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    /**
     * 判断类型是否存在
     *
     * @param type 回复类型
     * @return boolean
     * @author Lv-YongJian
     * @date 2020/2/28 15:49
     */
    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
