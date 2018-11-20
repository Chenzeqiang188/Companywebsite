package com.good.web.service;

import java.util.List;

import com.good.web.domain.Honor;

public interface HonorService {
	/**
	 * 添加/修改荣誉
	 * @param honour
	 * @return
	 */
	String saveOrUpdateHonor(Honor honor);
	/**
	 * 查询荣誉
	 * @param year
	 * @param winner
	 * @param awardUnit
	 * @param state
	 * @return
	 */
	List<Honor> queryHonorList(Honor honor);
	/**
	 * 根据id查找荣誉
	 * @param id
	 * @return
	 */
	Honor findHonorById(Integer id);
	/**
	 * 根据id删除荣誉
	 * @param id
	 * @return
	 */
	int deleteHonorById(Integer id);

}
