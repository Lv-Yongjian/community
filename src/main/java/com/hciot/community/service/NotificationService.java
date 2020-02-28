package com.hciot.community.service;

import com.hciot.community.dto.NotificationDTO;
import com.hciot.community.dto.PaginationDTO;
import com.hciot.community.dto.QuestionDTO;
import com.hciot.community.enums.NotificationStatusEnum;
import com.hciot.community.enums.NotificationTypeEnum;
import com.hciot.community.exception.CustomizeErrorCode;
import com.hciot.community.exception.CustomizeException;
import com.hciot.community.mapper.NotificationMapper;
import com.hciot.community.mapper.UserMapper;
import com.hciot.community.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 通知服务层
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Service
public class NotificationService {

    @Autowired(required = false)
    private NotificationMapper notificationMapper;

    /**
     * 通知列表
     *
     * @param userId 用户id
     * @param page   页数
     * @param size   条数
     * @return com.hciot.community.dto.PaginationDTO
     * @author Lv-YongJian
     * @date 2020/2/28 16:16
     */
    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        //获取该用户的通知总条数
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);

        Integer totalPage;//总页数
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        //判断页数是否超出正常范围
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);

        //size*(page-1)
        Integer offset = page < 1 ? 0 : size * (page - 1);
        //获取通知列表
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        if (notifications.size() == 0) {
            return paginationDTO;
        }
        //将 notifications 转成 notificationDTOS 对象
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    /**
     * 获取未读通知数
     *
     * @param userId 用户id
     * @return java.lang.Long
     * @author Lv-YongJian
     * @date 2020/2/28 16:00
     */
    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    /**
     * 阅读通知
     *
     * @param id   通知的id
     * @param user 阅读通知的用户
     * @return com.hciot.community.dto.NotificationDTO
     * @author Lv-YongJian
     * @date 2020/2/28 14:29
     */
    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        //通知消息找不到
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        //通知的接收者不是该用户
        if (!notification.getReceiver().equals(user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_TAIL);
        }
        //修改通知的状态为已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        //设置 typeName ，是回复评论or回复问题
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
