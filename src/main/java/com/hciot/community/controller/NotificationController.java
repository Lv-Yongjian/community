package com.hciot.community.controller;

import com.hciot.community.dto.NotificationDTO;
import com.hciot.community.enums.NotificationTypeEnum;
import com.hciot.community.model.User;
import com.hciot.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 通知功能控制层
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 通知跳转
     *
     * @param id      通知的id
     * @param request 客户端的请求
     * @return java.lang.String
     * @author Lv-YongJian
     * @date 2020/2/28 14:25
     */
    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Long id,
                          HttpServletRequest request) {
        //用户未登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        //阅读通知
        NotificationDTO notificationDTO = notificationService.read(id, user);

        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            //跳转到该问题
            return "redirect:/question/" + notificationDTO.getOuterid();
        } else {
            return "redirect:/";
        }
    }
}
