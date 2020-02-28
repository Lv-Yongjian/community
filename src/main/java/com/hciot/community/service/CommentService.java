package com.hciot.community.service;

import com.hciot.community.dto.CommentDTO;
import com.hciot.community.enums.CommentTypeEnum;
import com.hciot.community.enums.NotificationStatusEnum;
import com.hciot.community.enums.NotificationTypeEnum;
import com.hciot.community.exception.CustomizeErrorCode;
import com.hciot.community.exception.CustomizeException;
import com.hciot.community.mapper.*;
import com.hciot.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 评论服务层
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Service
public class CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private QuestionExtMapper questionExtMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private CommentExtMapper commentExtMapper;

    @Autowired(required = false)
    private NotificationMapper notificationMapper;

    /**
     * 新增评论
     *
     * @param comment     评论内容的对象
     * @param commentator 评论人
     * @return void
     * @author Lv-YongJian
     * @date 2020/2/28 11:36
     */
    @Transactional //开启事务
    public void insert(Comment comment, User commentator) {
        //没有父类id，未选中任何问题或评论进行回复
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        //评论类型错误或不存在
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        //评论类型存在
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())) {
            //父类 id 不是评论的 id
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            //父类 id 不是问题的 id
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

            //回复评论
            commentMapper.insertSelective(comment);
            //增加评论数
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentExtMapper.incCommentCount(parentComment);

            //创建通知
            createNotify(comment, dbComment.getCommentator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT, question.getId());
        } else {
            //父类 id 不是问题的 id
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //回复问题
            commentMapper.insertSelective(comment);
            //增加回复数
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);

            //创建通知
            createNotify(comment, question.getCreator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_QUESTION, question.getId());
        }
    }

    /**
     * 创建通知
     *
     * @param comment          评论内容对象
     * @param receiver         接收者（问题or评论的发起人）
     * @param notifierName     评论人（登录的用户）
     * @param outerTitle       问题or评论的标题
     * @param notificationType 通知的类型（回复的是问题or评论）
     * @param outerId          问题or评论的id
     * @return void
     * @author Lv-YongJian
     * @date 2020/2/28 11:53
     */
    private void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Long outerId) {
        //如果评论人是通知的接收者，则不通知
        if (comment.getCommentator().equals(receiver)) {
            return;
        }
        //创建通知内容对象
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());//状态：已读or未读
        notification.setReceiver((receiver));
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    /**
     * 评论列表
     *
     * @param id   问题or评论的id
     * @param type 问题or评论的类型
     * @return java.util.List<com.hciot.community.dto.CommentDTO>
     * @author Lv-YongJian
     * @date 2020/2/28 13:53
     */
    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        //查询评论的父 id 和类型，并且倒叙显示
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        //获取去重的评论人，显示需要评论人的详细信息
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        //获取去重评论人的信息并转换成 Map<Long id， User user>
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        //用 map 对象存放所以不重复的评论人
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换 comment 为 commentDTO ,将去重评论人的头像，名称等信息放到 user 中
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            //获取 userMap 中的评论人
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
