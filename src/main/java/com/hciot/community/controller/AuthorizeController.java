package com.hciot.community.controller;

import com.hciot.community.dto.AccesstokenDTO;
import com.hciot.community.dto.GithubUser;
import com.hciot.community.mapper.UserMapper;
import com.hciot.community.model.User;
import com.hciot.community.provider.GithubProvider;
import com.hciot.community.service.UserService;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @description: 登录授权控制层
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Controller
@Slf4j
public class AuthorizeController {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect_uri}")
    private String redirectUri;

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    /**
     * 通过 GitHub Api 登录授权
     *
     * @param code     状态码
     * @param state    状态
     * @param response 对客户端的响应
     * @return java.lang.String
     * @author Lv-YongJian
     * @date 2020/2/27 20:21
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        //页面 authorize 访问 GitHub ，重定向 redirect_uri 回 callback 接口并携带 code
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id(clientId);
        accesstokenDTO.setClient_secret(clientSecret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(redirectUri);
        accesstokenDTO.setState(state);
        //access_token 携带 code 继续访问 GitHub ，获取 GitHub 返回的 accessToken
        String accessToken = githubProvider.getAccessToken(accesstokenDTO);
        //user 携带 accessToken 访问 GitHub ，获取 GitHub 的用户信息
        GithubUser githubUser = githubProvider.getUser(accessToken);
        //判断 GitHub user 是否为空，若不为空则在数据库中创建或更新 user
        if (githubUser != null && githubUser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.createOrUpdate(user);
            //登录完添加 Cookie
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            log.error("callback get github error {}", githubUser);
            //登录失败，重新登录
            return "redirect:/";
        }
    }

    /**
     * 退出登录
     *
     * @param request  客户端的请求
     * @param response 对客户端的响应
     * @return java.lang.String
     * @author Lv-YongJian
     * @date 2020/2/27 20:27
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        //移除 Session 中的 user
        request.getSession().removeAttribute("user");
        //重置 Cookie
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
