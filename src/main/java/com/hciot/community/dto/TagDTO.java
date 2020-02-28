package com.hciot.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @description: 标签库DTO
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Data
public class TagDTO {
    /**
     * 类别标签
     */
    private String categoryName;
    /**
     * 标签集合List
     */
    private List<String> tags;
}
