package com.hciot.community.controller;

import com.hciot.community.dto.PaginationDTO;
import com.hciot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 首页控制层
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    /**
     * 首页显示
     *
     * @param model  给 html 传值
     * @param page   页数
     * @param size   条数
     * @param search 搜索内容
     * @return java.lang.String
     * @author Lv-YongJian
     * @date 2020/2/28 10:51
     */
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size,
                        @RequestParam(name = "search", required = false) String search) {
        //把查询出来的问题封装成分页对象
        PaginationDTO pagination = questionService.list(search, page, size);
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        return "index";
    }
}

