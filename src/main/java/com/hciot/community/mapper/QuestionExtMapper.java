package com.hciot.community.mapper;

import com.hciot.community.dto.QuestionQueryDTO;
import com.hciot.community.model.Question;
import com.hciot.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @description: 自定义 question 类 mapper 方法
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
public interface QuestionExtMapper {
    /**
     * 新增阅读数
     *
     * @param record
     * @return int
     * @author Lv-YongJian
     * @date 2020/2/28 16:06
     */
    int incView(Question record);

    /**
     * 新增问题回复数
     *
     * @param record
     * @return int
     * @author Lv-YongJian
     * @date 2020/2/28 16:07
     */
    int incCommentCount(Question record);

    /**
     * 相关问题列表
     *
     * @param question
     * @return java.util.List<com.hciot.community.model.Question>
     * @author Lv-YongJian
     * @date 2020/2/28 16:08
     */
    List<Question> selectRelated(Question question);

    /**
     * 模糊搜索问题总条数
     *
     * @param questionQueryDTO
     * @return java.lang.Integer
     * @author Lv-YongJian
     * @date 2020/2/28 16:08
     */
    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    /**
     * 模糊搜索问题列表倒叙
     *
     * @param questionQueryDTO
     * @return java.util.List<com.hciot.community.model.Question>
     * @author Lv-YongJian
     * @date 2020/2/28 16:08
     */
    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}