package com.hciot.community.controller;

import com.hciot.community.dto.CommentDTO;
import com.hciot.community.dto.QuestionDTO;
import com.hciot.community.enums.CommentTypeEnum;
import com.hciot.community.service.CommentService;
import com.hciot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @description: 问题控制层
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    /**
     * 问题列表显示
     *
     * @param id    问题id
     * @param model 给 html 传值
     * @return java.lang.String
     * @author Lv-YongJian
     * @date 2020/2/28 15:15
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {
        //获取问题
        QuestionDTO questionDTO = questionService.getById(id);
        //获取相关问题列表
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        //获取问题回复列表
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
