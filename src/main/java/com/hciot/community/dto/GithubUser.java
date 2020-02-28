package com.hciot.community.dto;

import lombok.Data;

/**
 * @description: GitHub用户DTO
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Data
public class GithubUser {
    /**
     * 名称
     */
    private String name;
    /**
     * id
     */
    private Long id;
    /**
     * bio
     */
    private String bio;
    /**
     * 头像url
     */
    private String avatar_url;
}
