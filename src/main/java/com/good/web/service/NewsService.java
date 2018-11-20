package com.good.web.service;

import java.io.File;

import com.alibaba.fastjson.JSONArray;
import com.good.web.domain.News;
import org.springframework.web.multipart.MultipartFile;

public interface NewsService {
	/**
	 * 增加/修改新闻
	 * @param news
	 * @param img
	 * @param imgFileName 
	 * @return
	 */
	String saveOrUpdateNews(News news, File img,String img1,String imgFileName);
	/**
	 * 查询新闻
	 * @param title
	 * @param category
	 * @return
	 */
	JSONArray queryNews(News news);
	/**
	 * 根据id查找新闻
	 * @param id
	 * @return
	 */
	News findNewsById(Integer id);
	/**
	 * 根据删除新闻
	 * @param id
	 * @return
	 */
	int deleteNewsById(Integer id);

}
