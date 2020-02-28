package com.hciot.community.dto;

import com.hciot.community.exception.CustomizeErrorCode;
import com.hciot.community.exception.CustomizeException;
import lombok.Data;

/**
 * @description: 返回结果DTO
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Data
public class ResultDTO<T> {
    /**
     * code码
     */
    private Integer code;
    /**
     * 信息
     */
    private String message;
    /**
     * 泛型数据
     */
    private T data;

    /**
     * 返回 code 跟 message 的结果集
     *
     * @param code
     * @param message
     * @return com.hciot.community.dto.ResultDTO
     * @author Lv-YongJian
     * @date 2020/2/28 15:42
     */
    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    /**
     * 返回 code 跟 message 的结果集
     *
     * @param errorCode 错误枚举
     * @return com.hciot.community.dto.ResultDTO
     * @author Lv-YongJian
     * @date 2020/2/28 15:43
     */
    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * 返回 code 跟 message 的结果集
     *
     * @param e 错误异常
     * @return com.hciot.community.dto.ResultDTO
     * @author Lv-YongJian
     * @date 2020/2/28 15:43
     */
    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    /**
     * 返回 ok 结果集
     *
     * @param
     * @return com.hciot.community.dto.ResultDTO
     * @author Lv-YongJian
     * @date 2020/2/28 15:45
     */
    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    /**
     * 返回带数据的 ok 结果集
     *
     * @param t 泛型数据
     * @return com.hciot.community.dto.ResultDTO
     * @author Lv-YongJian
     * @date 2020/2/28 15:45
     */
    public static <T> ResultDTO okOf(T t) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }
}
