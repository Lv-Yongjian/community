package com.hciot.community.exception;

/**
 * @description: 异常
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
public class CustomizeException extends RuntimeException{
    /**
     * 信息
     */
    private String message;
    /**
     * 状态码
     */
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
