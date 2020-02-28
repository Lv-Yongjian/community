package com.hciot.community.dto;

import lombok.Data;

/**
 * @description: AccesstokenDTO
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Data
public class AccesstokenDTO {
    /**
     * GitHub client_id
     */
    private String client_id;
    /**
     * GitHub client_secret
     */
    private String client_secret;
    /**
     * GitHub redirect_uri
     */
    private String redirect_uri;
    /**
     * GitHub code
     */
    private String code;
    /**
     * GitHub state
     */
    private String state;

}
