package com.good.web.service;

import com.alibaba.fastjson.JSONArray;
import com.good.web.domain.History;
public interface HistoryService {
	/**
	 * 添加/修改历程
	 * @param history
	 * @return
	 */
	String saveOrUpdateHistory(History history);
	/**
	 * 查询历程
	 * @param year
	 * @param state
	 * @return
	 */
	JSONArray queryHistoryList(History history);
	/**
	 * 根据id查找历程
	 * @param id
	 * @return
	 */
	History findHistoryById(Integer id);
	/**
	 * 根据id删除历程
	 * @param id
	 * @return
	 */
	int deleteHistoryById(Integer id);
}
