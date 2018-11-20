package com.good.web.service;

import java.io.File;
import java.util.List;

import com.good.web.domain.Card;
import com.good.web.domain.Image;

public interface ImageService {
	/**
	 * 添加/修改图片
	 * @param image
	 * @param img
	 * @param imgFileName
	 * @return
	 */
	String saveOrUpdateImage(Image image, File img, String img1,String imgFileName);
	/**
	 * 查询图片
	 * @param title
	 * @param imgType
	 * @param state
	 * @return
	 */
	List<Image> queryImageList(Image image);
	/**
	 * 添加/修改幻灯片
	 * @param card
	 * @param img
	 * @param imgFileName
	 * @return
	 */
	String saveOrUpdateCard(Card card, File img, String img1,String imgFileName);
	/**
	 * 查询幻灯片
	 * @param title
	 * @param cardType
	 * @param state
	 * @return
	 */
	List<Card> queryCardList(Card card);
	/**
	 * 根据id查找图片
	 * @param id
	 * @return
	 */
	Image findImageById(Integer id);
	/**
	 * 根据id删除图片
	 * @param id
	 * @return
	 */
	int deleteImageById(Integer id);
	/**
	 * 根据id查找幻灯片
	 * @param id
	 * @return
	 */
	Card findCardById(Integer id);
	/**
	 * 根据id删除幻灯片
	 * @param id
	 * @return
	 */
	int deleteCardById(Integer id);

}
