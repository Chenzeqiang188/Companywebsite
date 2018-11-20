package com.good.web.service;

import java.io.File;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.good.web.domain.Advice;
import org.springframework.stereotype.Service;


public interface AdviceService {
	/**
	 * 添加/修改游戏
	 * @param Advice
	 * @param qrCodeImgFileName 
	 * @param qrCodeImg 
	 * @param imgFileName 
	 * @param img 
	 * @param iconFileName 
	 * @param icon 
	 * @return
	 */
	String saveOrUpdateAdvice(Advice advice, File img,String img1,String imgFileName);
	/**
	 * 添加/修改游戏
	 * @param Advice
	 * @param qrCodeImgFileName
	 * @param qrCodeImg
	 * @param imgFileName
	 * @param img
	 * @param iconFileName
	 * @param icon
	 * @return
	 */
	String saveOrUpdateAdvice2(Advice advice,File img, String imgFileName, File qrCodeImg, String qrCodeImgFileName,String img1,String img2);
	/**
	 * 查询游戏
	 * @param AdviceName
	 * @param hot
	 * @param state
	 * @return
	 */
	JSONArray queryAdviceList(Advice Advice);
	/**
	 * 根据id查找游戏
	 * @param id
	 * @return
	 */
	Advice findAdviceById(Integer id);
	/**
	 * 根据id删除游戏
	 * @param id
	 * @return
	 */
	int deleteAdviceById(Integer id);
	/**
	 * 获取随机banner广告图片( 0 banner 1 开屏图片 2 底部图片)
	 * 在发布时间之后，撤销时间之前
	 * @param position
	 * @return
	 */
	Advice getSingleBannerAdvice(Integer position);

}
