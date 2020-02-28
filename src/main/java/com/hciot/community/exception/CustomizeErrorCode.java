package com.hciot.community.exception;

/**
 * @description: 错误类型枚举
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    /**
     * 异常枚举
     */
    QUESTION_NOT_FOUND(2001,"你找的问题不在了，要不要换一个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试"),
    SYS_ERROR(2004,"服务冒烟了，要不然您稍后再试试！！！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在了，要不要换个试试？"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空"),
    READ_NOTIFICATION_TAIL(2008,"兄弟你这是都别人的信息呢？"),
    NOTIFICATION_NOT_FOUND(2009,"消息莫非是不翼而飞了？"),
    IMAGE_TOO_BIG(2010,"图片太大了，可能需要换一张~"),
    GET_TOKEN_FAIL(2011,"获取GitHub的token失败"),
    GET_USER_FAIL(2011,"获取GitHub的用户信息失败"),
    USER_NOT_FOUND(2012,"用户不存在？");

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
