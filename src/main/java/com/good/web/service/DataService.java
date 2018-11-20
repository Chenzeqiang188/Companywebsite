package com.good.web.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.web.domain.Advice;
import com.good.web.domain.Card;
import com.good.web.domain.Game;
import com.good.web.domain.Image;
import com.good.web.domain.News;
import com.good.web.domain.Recruit;
import com.good.web.domain.Show;

public interface DataService {
	/**
	 * 获取展示板块
	 * @Title: getShows  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-3 下午05:04:56 
	 * @return
	 */
	public List<Show> getShows();
  /**
   * 获取广告
   * @Title: getaAdvice  
   * @Description: 
   * @author：     周文广
   * @date 2017-3-3 下午03:52:38 
   * @return
   */
//	public Advice getAdvice();
	/**
	 * 获取榜单
	 * @Title: getRanks  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-3 下午03:54:56 
	 * @return
	 */
	public List<Show> getRanks();
	
	/**
	 * 获取幻灯片
	 * @return
	 */
	public List<Card> getCards();
	/**
	 * 获取新闻
	 * @param newsCategory 
	 * @return
	 */
	public List<News> getNews();
	/**
	 * @Title: getNews
	 *@Description: 分页获取新闻列表
	 * @param @param pagesize 页码
	 * @param @param count 页大小
	 * @param @return
	 *@return List<News>
	 * @author 李松茂   
	 * 2016-6-17 下午03:15:14
	 */
	public  List<News> getNews(int pagesize,int count);
	
	/**
	 * 获取游戏
	 * @param hot 
	 * @return
	 */
	public List<Game> getGames();
	/**
	 * 获取公司发展历程
	 * @return
	 */
	public JSONArray getHistories();
	/**
	 * 获取公司荣誉
	 * @return
	 */
	public JSONArray getHonors();
	/**
	 * 获取招聘信息
	 * @param positionType 职位类型
	 * @param positionName 职位名称
	 * @return
	 */
	public JSONArray getRecruits(String positionName, Integer positionType);
	/**
	 * 获取图片
	 * @param imageType
	 * @return
	 */
	public List<Image> getImages(String imageType);
	/**
	 * 根据id查找招聘信息
	 * @param id
	 * @return
	 */
	public JSONObject queryRecruitById(Integer id);
	/**
	 * 根据id查找新闻
	 * @param id
	 * @return
	 */
	public News queryNewsById(Integer id);
	
	/**
	 * @Title: queryRecruit
	 *@Description: 获取招聘信息列表
	 * @param @param r
	 * @param @return
	 *@return List<Recruit>
	 * @author 李松茂   
	 * 2016-7-6 下午02:31:50
	 */
	public List<Recruit> queryRecruit(Recruit r);
	/**
	 * @Title: findRecruitById
	 *@Description: 根据id查找招聘信息
	 * @param @param id
	 * @param @return
	 *@return Recruit
	 * @author 李松茂   
	 * 2016-7-6 下午02:51:05
	 */
	public Recruit findRecruitById(Integer id);
	

	/**
	 * 获取随机banner广告图片( 0 banner 1 开屏图片 2 底部图片)
	 * 在发布时间之后，撤销时间之前
	 * @param imageType
	 * @return
	 */
	public Advice getSingleBannerAdvice(int position);
	
	/**
	 * 根据id查找广告图片
	 * @param imageType
	 * @return
	 */
	public Advice getAdviceById(int id);
	
	/**
	 * 根据id查找板块信息
	 * @param imageType
	 * @return
	 */
	public Show getSectionById(int id);
}
