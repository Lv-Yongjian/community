package com.hciot.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO<T> {
    private List<T> data;//列表
    private Boolean showPrevious;//显示上一页
    private Boolean showFirstPage;//显示第一页
    private Boolean showNext;//显示下一页
    private Boolean showEndPage;//显示最后一页
    private Integer page;//当前显示页数
    private List<Integer> pages = new ArrayList<>();//显示页数列表
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;//总页数
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
