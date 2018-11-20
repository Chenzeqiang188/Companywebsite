package com.good.web.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.good.web.domain.Recruit;

import javax.servlet.http.HttpServletResponse;


public interface RecruitService {
	/**
	 * 添加职位类别
	 * @param typeName 类别名
	 * @param typeValue 类别类型
	 */
	void addPositionType(String typeName, Integer typeValue,HttpServletResponse response);
	/**
	 * 添加部门
	 * @param typeName 部门名
	 */
	void addDept(String typeName,HttpServletResponse response);
	/**
	 * 删除职位类型
	 * @param id
	 */
	int delPositionType(Integer id,HttpServletResponse response	);
	/**
	 * 删除部门
	 * @param id
	 */
	int delDept(Integer id,HttpServletResponse response);
	/**
	 * 添加/修改招聘信息
	 * @param recruit
	 * @return
	 */
	String addOrUpdateRecruit(Recruit recruit);
	/**
	 * 查询招聘信息
	 * @param position
	 * @param positionType1
	 * @param positionType2
	 * @param deptId
	 * @param state
	 * @return
	 */
	JSONArray queryRecruitList(Recruit recruit);
	/**
	 * 根据id查找招聘信息
	 * @param iD
	 * @return
	 */
	Recruit findRecruitById(Integer id);
	/**
	 * 根据id删除招聘信息
	 * @param id
	 * @return
	 */
	int deleteRecruitById(Integer id);

}
