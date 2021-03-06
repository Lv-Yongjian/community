package com.hciot.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 分页列表DTO
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Data
public class PaginationDTO<T> {
    /**
     * 列表
     */
    private List<T> data;
    /**
     * 显示上一页
     */
    private Boolean showPrevious;
    /**
     * 显示第一页
     */
    private Boolean showFirstPage;
    /**
     * 显示下一页
     */
    private Boolean showNext;
    /**
     * 显示最后一页
     */
    private Boolean showEndPage;
    /**
     * 当前显示页数
     */
    private Integer page;
    /**
     * 显示页数列表
     */
    private List<Integer> pages = new ArrayList<>();
    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 设置页数显示效果
     *
     * @param totalPage
     * @param page
     * @return void
     * @author Lv-YongJian
     * @date 2020/2/28 15:35
     */
    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;//获取页数提供页面判断是否高亮
        pages.add(page);//添加页数列表
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {//添加当前页数前3页
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {//添加页数后3页
                pages.add(page + i);
            }
        }

        //是否显示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否显示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        //是否显示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //是否显示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
