package com.good.web.service;

import java.io.File;
import java.util.List;

import com.good.web.domain.Game;
import com.good.web.domain.News;


public interface GameService {
	/**
	 * 增加/修改新闻
	 * @param game
	 * @param img
	 * @param imgFileName
	 * @return
	 */
	String saveOrUpdateHotGame(Game game, File img, String img1, String imgFileName);
	/**
	 * 添加/修改游戏
	 * @param game
	 * @param qrCodeImgFileName 
	 * @param qrCodeImg 
	 * @param imgFileName 
	 * @param img 
	 * @param iconFileName 
	 * @param icon 
	 * @return
	 */
	String saveOrUpdateGame(Game game, File img, String imgFileName, File qrCodeImg, String qrCodeImgFileName,String img1,String img2);
	/**
	 * 查询游戏
	 * @param gameName
	 * @param hot
	 * @param state
	 * @return
	 */
	List<Game> queryGameList(Game game);
	/**
	 * 根据id查找游戏
	 * @param id
	 * @return
	 */
	Game findGameById(Integer id);
	/**
	 * 根据id删除游戏
	 * @param id
	 * @return
	 */
	int deleteGameById(Integer id);

}
