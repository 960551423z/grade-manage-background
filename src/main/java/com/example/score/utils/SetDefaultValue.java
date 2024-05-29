package com.example.score.utils;


import com.example.score.common.PageInfoResult;

/**
 * 设置分页的默认值
 */
public class SetDefaultValue {

    public static void setDefault(PageInfoResult pageResult) {
        Integer pageNum = pageResult.getPageNum();
        Integer current = pageResult.getCurrent();

        int defaultPageNum = 10;
        int defaultCurrent = 1;
        if (pageNum == null || pageNum <= 0)
            pageResult.setPageNum(defaultPageNum);

        if (current == null || current <= 0) {
            pageResult.setCurrent(defaultCurrent);
        }
    }
}
