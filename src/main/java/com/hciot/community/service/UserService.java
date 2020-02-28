package com.hciot.community.service;

import com.hciot.community.exception.CustomizeErrorCode;
import com.hciot.community.exception.CustomizeException;
import com.hciot.community.mapper.UserMapper;
import com.hciot.community.model.User;
import com.hciot.community.model.UserExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户服务层
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Service
@Slf4j
public class UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * 创建or更新用户
     *
     * @param user 用户
     * @return void
     * @author Lv-YongJian
     * @date 2020/2/28 16:30
     */
    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            //更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbUser.getId());
            int update = userMapper.updateByExampleSelective(updateUser, example);
            if (update != 1){
                log.error("user no found {}", user);
                throw new CustomizeException(CustomizeErrorCode.USER_NOT_FOUND);
            }
        }
    }
}
