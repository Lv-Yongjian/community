package com.hciot.community.enums;

//类型：回复问题还是评论
public enum  CommentTypeEnum {
    //问题
    QUESTION(1),
    //评论
    COMMENT(2);
    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()){
            if (commentTypeEnum.getType().equals(type)){
                return true;
            }
        }
        return false;
    }
}
