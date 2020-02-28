package com.hciot.community.provider;

import com.alibaba.fastjson.JSON;
import com.hciot.community.dto.AccesstokenDTO;
import com.hciot.community.dto.GithubUser;
import com.hciot.community.exception.CustomizeErrorCode;
import com.hciot.community.exception.CustomizeException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description: GitHub 授权登录
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Component
@Slf4j
public class GithubProvider {

    /**
     * access_token 访问 GitHub
     *
     * @param accesstokenDTO 访问 GitHub 携带的属性对象
     * @return java.lang.String
     * @author Lv-YongJian
     * @date 2020/2/28 10:19
     */
    public String getAccessToken(AccesstokenDTO accesstokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accesstokenDTO));
        //创建一个请求 access_token ，获取 GitHub 回调的token
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        //OkHttpClient 对象返回实体
        try (Response response = client.newCall(request).execute()) {
            //获取返回的数据，默认是 utf-8
            String string = response.body().string();
            //获取返回数据中的 token
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            log.error("access_token back error {}", e);
            throw new CustomizeException(CustomizeErrorCode.GET_TOKEN_FAIL);
        }
    }

    /**
     * 通过 accessToken 获取 GitHub 中的用户信息
     *
     * @param accessToken GitHub 回调的 token
     * @return com.hciot.community.dto.GithubUser
     * @author Lv-YongJian
     * @date 2020/2/28 10:31
     */
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        //创建一个请求,获取 GitHub 用户的信息
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            log.error("get GitHub user error {}", e);
            throw new CustomizeException(CustomizeErrorCode.GET_USER_FAIL);
        }
    }
}
