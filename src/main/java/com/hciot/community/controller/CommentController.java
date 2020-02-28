package com.hciot.community.controller;

import com.hciot.community.dto.CommentCreateDTO;
import com.hciot.community.dto.CommentDTO;
import com.hciot.community.dto.ResultDTO;
import com.hciot.community.enums.CommentTypeEnum;
import com.hciot.community.exception.CustomizeErrorCode;
import com.hciot.community.model.Comment;
import com.hciot.community.model.User;
import com.hciot.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: 回复功能控制层
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 回复问题 or 评论
     *
     * @param commentCreateDTO 回复的对象
     * @param request          客户端的请求
     * @return java.lang.Object
     * @author Lv-YongJian
     * @date 2020/2/28 11:04
     */
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        //判断回复是否为空
        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setContent(commentCreateDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment, user);
        return ResultDTO.okOf();
    }

    /**
     * 显示二级评论
     *
     * @param id 问题评论的id
     * @return com.hciot.community.dto.ResultDTO<java.util.List < com.hciot.community.dto.CommentDTO>>
     * @author Lv-YongJian
     * @date 2020/2/28 11:14
     */
    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }

}
