package com.good.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.DateUtil;
import com.good.web.domain.Advice;
import com.good.web.domain.Card;
import com.good.web.domain.Game;
import com.good.web.domain.History;
import com.good.web.domain.Honor;
import com.good.web.domain.Image;
import com.good.web.domain.News;
import com.good.web.domain.Recruit;
import com.good.web.domain.Show;
import com.good.web.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DataServiceImpl implements DataService {
	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;

	/**
	 * 获取展示板块
	 * @Title: getShows  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-3 下午05:04:56 
	 * @return
	 */
	public List<Show> getShows(){
		return dataCenter.sectionList;
	}
	/**
	 * 获取榜单
	 * @Title: rankList  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-3 下午03:54:56 
	 * @return
	 */
	public List<Show> getRanks(){
		return dataCenter.rankList;
	}
	/**
	 * 获取广告
	 * @Title: getaAdvice  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-3 下午03:52:00 
	 * @return
	 */
//	public Advice getAdvice(){
//		return dataCenter.advice;
//	}
	
	/**
	 * @Title: cardsList
	 *@Description: 获取幻灯片
	 * @param @return
	 *@return List<Card>
	 * @author 李松茂   
	 * 2016-6-15 下午01:59:31
	 */
	public List<Card> getCards() {
		return dataCenter.cardList;
	}

	/**
	 * @Title: getNewsList
	 *@Description: 获取新闻列表
	 * @param @return
	 *@return List<News>
	 * @author 李松茂   
	 * 2016-6-15 上午11:36:05
	 */
	public List<News> getNews() {
		return dataCenter.newsList;
	}
	
	/***
	 * @Title: getNews
	 *@Description: 新闻列表内存分页
	 * @param @param pagesize  页码
	 * @param @param count 页大小
	 * @param @return
	 *@return List<News>
	 * @author 李松茂   
	 * 2016-5-23 下午03:03:37
	 */
	public List<News> getNews(int pagesize,int count){
		List<News> newslist= dataCenter.newsList;
		List<News> renewslist=null;
		if((pagesize-1)*count>newslist.size()){
			return null;
		}else{
			renewslist=new ArrayList<News>();
		}
		if(renewslist!=null){
			for(int i=(pagesize-1)*count; i<pagesize*count;i++){
				if(pagesize*count>=newslist.size()){
					break;
				}
				renewslist.add(newslist.get(i));
			}
		}
		
		return renewslist;
	}
	
	/**
	 * @Title: queryNewsById
	 *@Description: 获取新闻详情
	 * @param @param id
	 * @param @return
	 *@return News
	 * @author 李松茂   
	 * 2016-6-15 下午02:29:19
	 */
	public News queryNewsById(Integer id) {
		//return (News) entityManage.findById(News.class, id);
		return dataCenter.NewsMap.get(id);
	}
	
	
	/**
	 * @Title: getGames
	 *@Description:获取游戏列表
	 * @param @return
	 *@return List<Game>
	 * @author 李松茂   
	 * 2016-6-15 下午02:00:09
	 */
	public List<Game> getGames() {
		return dataCenter.gameList;
	}
	/**
	 * @Title: getHistories
	 *@Description: 获取公司发展历程
	 * @param @return
	 *@return JSONArray
	 * @author 李松茂   
	 * 2016-6-15 下午02:01:45
	 */
	public JSONArray getHistories() {
		JSONArray jsonArray = null;
		List<History> list = dataCenter.historyList;
		if(list!=null && list.size()>0){
//			jsonArray = new JSONArray();
//			String sql = "select distinct year from tb_history order by year desc";
//			List<Integer> list2 = entityManage.findBySql(sql);
//			for(Integer i:list2){
//				JSONObject json = new JSONObject();
//				JSONArray arr = new JSONArray();
//				json.put("year", i.intValue());
//				for(History h:list){
//					if(i.intValue()==h.getYear().intValue() && h.getState()==1){
//						arr.add(h);
//					}
//				}
//				if(arr.size()>0){
//					json.put("data", arr);
//					jsonArray.add(json);
//				}
//			}
			
			jsonArray = new JSONArray();
//			System.out.println(DateUtil.getYear(new Date()));
			for(Integer i=DateUtil.getYear(new Date());i>=2010;i--){
				JSONArray arr = new JSONArray();
				for(History h:list){
					if(i.intValue()==h.getYear().intValue() && h.getState()==1){
						arr.add(h);
					}
				}
				if(arr.size()>0){
					JSONObject json = new JSONObject();
					json.put("year", i.intValue());
					json.put("data", arr);
					jsonArray.add(json);
				}
			}
			
		}
		return jsonArray;
	}
	
	/**
	 * @Title: getHonors
	 *@Description: 荣誉列表
	 * @param @return
	 *@return JSONArray
	 * @author 李松茂   
	 * 2016-6-15 下午02:27:53
	 */
	public JSONArray getHonors() {
		JSONArray jsonArray = null;
		List<Honor> list = dataCenter.honorList;
		if(list!=null && list.size()>0){
//			jsonArray = new JSONArray();
//			String sql = "select distinct year from tb_honor order by award_date desc";
//			List<Integer> list2 = entityManage.findBySql(sql);
//			for(Integer i:list2){
//				JSONObject json = new JSONObject();
//				JSONArray arr = new JSONArray();
//				json.put("year", i.intValue());
//				for(Honor h:list){
//					if(i.intValue()==h.getYear().intValue() && h.getState()==1){
//						arr.add(h);
//					}
//				}
//				if(arr.size()>0){
//					json.put("data", arr);
//					jsonArray.add(json);
//				}
//			}
			
			jsonArray = new JSONArray();
			for(Integer i=DateUtil.getYear(new Date());i>=2010;i--){
				JSONArray arr = new JSONArray();
				for(Honor h:list){
					if(i.intValue()==h.getYear().intValue() && h.getState()==1){
						arr.add(h);
					}
				}
				if(arr.size()>0){
					JSONObject json = new JSONObject();
					json.put("year", i.intValue());
					json.put("data", arr);
					jsonArray.add(json);
				}
			}
			
		}
		return jsonArray;
	}
	
	/**
	 * @Title: getRecruits
	 *@Description: 招聘信息列表
	 * @param @param positionName
	 * @param @param positionType
	 * @param @return
	 *@return JSONArray
	 * @author 
	 * 2016-6-15 下午02:54:22
	 */
	public JSONArray getRecruits(String positionName, Integer positionType) {
		JSONArray jsonArray = new JSONArray();
		StringBuffer sql = new StringBuffer("select r.id,r.position,p1.type_name,p2.type_name,d.dept_name,r.recruit_num,r.create_time,r.duty,r.required,r.state")
		.append(" from tb_recruit as r left join tb_position_type as p1 on r.position_type1=p1.id")
		.append(" left join tb_position_type as p2 on r.position_type2=p2.id")
		.append(" left join tb_dept as d on r.dept_id=d.id where 1=1");
		/*if(!ValidateUtil.str_isEmpty(positionName)){
			sql.append(" and r.position like '%").append(positionName).append("%'");
		}
		if(positionType!=null && positionType!=0){
			sql.append(" and r.position_type2=").append(positionType);
		}*/
		sql.append(" order by create_time desc");
		List<Object[]> list = entityManage.findBySql(sql.toString());
		/*StringBuffer hql = new StringBuffer("SELECT r.id,r.position,p1.typeName,p2.typeName,d.deptName,r.recruitNum,r.createTime,r.duty,r.required,r.state ")
		.append("FROM Recruit r,PositionType p1,PositionType p2,Dept d ")
		.append("WHERE r.positionType1=p1.id AND r.positionType2=p2.id AND r.deptId=d.id ");
		Map<String,Object> attrs = new HashMap<String,Object>();
		if(!ValidateUtil.str_isEmpty(positionName)){
			hql.append(" AND r.position LIKE :position");
			attrs.put("position", positionName);
		}
		if(positionType!=null && positionType!=0){
			hql.append(" AND r.positionType2=:type");
			attrs.put("type", positionType);
		}
		hql.append(" ORDER BY r.createTime DESC");
		List<Object[]> list = entityManage.findByHql(hql.toString(), attrs);*/
		if(list!=null && list.size()>0){
			for(Object[] os:list){
				JSONObject json = new JSONObject();
				json.put("id", os[0]);
				json.put("position", os[1]);
				json.put("positionType1", os[2]);
				json.put("positionType2", os[3]);
				json.put("dept", os[4]);
				json.put("recruitNum", os[5]);
				json.put("createTime", os[6]);
				json.put("duty", os[7]);
				json.put("required", os[8]);
				json.put("state", os[9]);
				jsonArray.add(json);
			}
		}
		return jsonArray;
	}
	
	
	
	/**
	 * @Title: queryRecruitById
	 *@Description: 根据id获取招聘信息
	 * @param @param id
	 * @param @return
	 *@return JSONObject
	 * @author 
	 * 2016-6-15 下午02:54:53
	 */
	public JSONObject queryRecruitById(Integer id) {
		JSONObject json = new JSONObject();
		StringBuffer sql = new StringBuffer("select r.id,r.position,pt.type_name,d.dept_name,r.recruit_num,r.duty,r.required")
			.append(" from tb_recruit as r left join tb_position_type as pt on r.position_type1=pt.id")
			.append(" left join tb_dept as d on r.dept_id=d.id")
			.append(" where r.id=").append(id);
		List<Object[]> list = entityManage.findBySql(sql.toString());
		if(list!=null && list.size()>0){
			Object[] os = list.get(0);
			json.put("id", os[0]);
			json.put("position", os[1]);
			json.put("positionType1", os[2]);
			json.put("dept", os[3]);
			json.put("recruitNum", os[4]);
			json.put("duty", os[5]);
			json.put("required", os[6]);
		}
		return json;
	}

	
	
	/**
	 * @Title: getImages
	 *@Description: 根据图片类型获取对应的图片列表
	 * @param @param imageType
	 * @param @return
	 *@return JSONObject
	 * @author 
	 * 2016-6-15 下午02:54:53
	 */
	public List<Image> getImages(String imageType) {
		List<Image> ilist = new ArrayList<Image>();
		for(Image i:dataCenter.imageList){
			if(i.getImgType().equals(imageType))
				ilist.add(i);
		}
		return ilist;
	}

	/**
	 * 获取招聘信息列表
	 */
	public List<Recruit> queryRecruit(Recruit r) {
		return dataCenter.recruitList;
	}
	/**
	 * 根据id查找招聘信息
	 */
	public Recruit findRecruitById(Integer id) {
		if(id!=null){
			return dataCenter.recruitMap.get(id);
		}
		return null;
	}
	
	/**
	 * 根据位置随机查找广告( 0 banner 1 开屏图片 2 底部图片)
	 * 在发布时间之后，撤销时间之前
	 * @param position
	 * @return
	 */
	public Advice getSingleBannerAdvice(int position) {
		List<Advice> advices = new ArrayList<Advice>();
		Date now = new Date();
		for (Advice t : dataCenter.adviceList) {
			if(t.getStartTime().before(now) && t.getEndTime().after(now) && t.getPosition() == position && t.getReffer() == 0){
				advices.add(t);
			}
		}
		if (advices.size() > 0) {
			int index = new Random().nextInt(advices.size());
			return advices.get(index);
		} else {
			int index = new Random().nextInt(dataCenter.adviceList.size());
			return dataCenter.adviceList.get(index);
		}
	}
	
	/**
	 * 根据id查找广告图片
	 * @param imageType
	 * @return
	 *//*
	public Advice getAdviceById(int id) {
		return dataCenter.adviceMap.get(id);
	}*/
	
	/**
	 * 根据id查找板块信息
	 * @param
	 * @return
	 */
	public Show getSectionById(int id) {
		return dataCenter.sectionMap.get(id);
	}
	@Override
	public Advice getAdviceById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
