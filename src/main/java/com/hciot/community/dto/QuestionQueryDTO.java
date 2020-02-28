package com.hciot.community.dto;

import lombok.Data;

/**
 * @description: 搜索问题DTO
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Data
public class QuestionQueryDTO {
    /**
     * 搜索内容
     */
    private String search;
    /**
     * 页数
     */
    private Integer page;
    /**
     * 条数
     */
    private Integer size;
}
