package com.hciot.community.controller;

import com.hciot.community.dto.QuestionDTO;
import com.hciot.community.mapper.QuestionMapper;
import com.hciot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){
        QuestionDTO questionDTO = questionService.getByTd(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
