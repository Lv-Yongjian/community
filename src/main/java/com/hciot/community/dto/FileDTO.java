package com.hciot.community.dto;

import lombok.Data;

/**
 * @description: 上传图片DTO
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Data
public class FileDTO {
    /**
     * 成功状态
     */
    private int success;
    /**
     * 信息
     */
    private String message;
    /**
     * 图片路径
     */
    private String url;
}
