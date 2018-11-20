package com.good.web.service;

import com.alibaba.fastjson.JSONArray;
import com.good.web.domain.ActivityCard;

import java.io.File;

public interface ActivityService {
    /**
     * 增加/修改新闻
     * @param news
     * @param img
     * @param imgFileName
     * @return
     */
    String saveOrUpdateActCard(ActivityCard activityCard, File img, String img1, String imgFileName);
    /**
     * 查询新闻
     * @param title
     * @param category
     * @return
     */
    JSONArray queryActCard(ActivityCard activityCard);
    /**
     * 根据id查找新闻
     * @param id
     * @return
     */
    ActivityCard findActCardById(Integer id);
    /**
     * 根据删除新闻
     * @param id
     * @return
     */
    int deleteActCardById(Integer id);
}
