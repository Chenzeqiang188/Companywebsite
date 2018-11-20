package com.good.web.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import com.good.web.base.common.Page;
import com.good.web.domain.CarouselImg;

public interface ICarouselImgService {
	/**
	 * 获取图片列表
	 * @param page 
	 * @param endtime 
	 * @param starttime 
	 * @Title: carouselImgList  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-2-28 下午06:27:56 
	 * @return
	 */
	public List<CarouselImg> carouselImgList();
	/**
	 * 获取图片列表
	 * @param page 
	 * @param endtime 
	 * @param starttime 
	 * @Title: carouselImgList  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-2-28 下午06:27:56 
	 * @return
	 */
	public List<CarouselImg> carouselImgList(Timestamp starttime, Timestamp endtime, Page page);
	/**
	 * 更新图片
	 * @Title: editCarouselImg  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-2-28 下午06:27:01 
	 * @param img
	 * @param imgFileName
	 * @param carouselImg
	 */
	public String editCarouselImg(File img,String imgFileName,CarouselImg carouselImg,String img1);
	/**
	 * 新增图片
	 * @Title: addCarouselImg  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-2-28 下午06:27:42 
	 * @param img
	 * @param imgFileName
	 * @param carouselImg
	 */
	public String addCarouselImg(File img,String imgFileName,CarouselImg carouselImg);
	/**
	 * 删除图片
	 * @Title: delCarouselImg  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-2-28 下午06:39:46 
	 * @param id
	 */
	public int delCarouselImg(Integer id);
	
	public CarouselImg getCarouselImg(Integer id);
}
