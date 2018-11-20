package com.good.web.service;

import java.io.File;
import java.util.List;

import com.good.web.base.common.Page;
import com.good.web.domain.RankGame;
import com.good.web.domain.Show;

public interface IRankGameService {
	/**
	 * 获取游戏排行
	 * @param page 
	 * @param name 
	 * @param showid 
	 * @Title: rankGameList  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-3 下午02:48:13 
	 * @return
	 */
	public List<RankGame> rankGameList(Integer showid, String name,String type, Page page);
	/**
	 * 更新游戏排行
	 * @Title: editRankGame  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-3 下午02:48:44 
	 * @param img
	 * @param imgFileName
	 * @param rankGame
	 */
	public void editRankGame(File img,String imgFileName,RankGame rankGame,String img1);
	/**
	 * 新增游戏排行
	 * @Title: addRankGame  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-3 下午02:48:55 
	 * @param img
	 * @param imgFileName
	 * @param rankGame
	 */
	public void addRankGame(File img,String imgFileName,RankGame rankGame);
	/**
	 * 获取游戏排行的单挑数据
	 * @Title: getRankGame  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-3 下午02:49:14 
	 * @param id
	 * @return
	 */
	public RankGame getRankGame(Integer id);
	/**
	 * 获取榜单
	 * @Title: rankList  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-3 下午02:49:37 
	 * @param type
	 * @return
	 */
	public List<Show> rankList(Integer type);
	/**
	 * 删除
	 * @Title: delRankGame  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-7 上午10:52:17 
	 * @param
	 */
	public void delRankGame(Integer id);
	
	public List<RankGame> rankGameList(Integer rankId);
}
