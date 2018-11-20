package com.good.web.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;

import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.ActivityGame;
import com.good.web.domain.ActivityGameNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Transactional
@Service
public class ActivityGameNewsServiceImpl {
	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;
	private HttpServletResponse response;
	private String message;
	
	/**
	 * 方法功能说明：增加和修改新闻
	 * @param gameNews
	 * @return
	 * String
	 * author:邓超
	 * 2016-8-3 下午5:01:03
	 */
	public String saveOrUpdateActivityGameNews(ActivityGameNews gameNews){
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				boolean flag = gameNews.getId()==null?true:false;
				boolean can = true;
				message = "以下内容不能为空：";			
				can = checkNull(gameNews.getTitle(), "标题");				
				can = checkNull(gameNews.getAuthor(), "作者");			
				can = checkNull(gameNews.getIntro(), "简介");
				can = checkNull(gameNews.getContext(), "内容");
				if(can){
					gameNews.setCeatetime(new Timestamp(System.currentTimeMillis()));
					entityManage.saveOrUpdate(gameNews);
					dataCenter.loadActivityGameNews();
					if(flag){
						message = "添加成功！";
					}else{
						message = "修改成功！";
				}
				
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "系统异常，请联系技术人员！";
		}
		return message;
	}

	/**
	 * 方法功能说明：检查新闻属性是否为空
	 * @param str
	 * @param msg
	 * @return
	 * boolean
	 * author:邓超
	 * 2016-8-3 下午5:00:20
	 */
	private boolean checkNull(String str,String msg){
		if(ValidateUtil.str_isEmpty(str)){
			message +=(msg+" ");
			return false;
		}else{
			return true;
		}
	}

	
	/**
	 * 方法功能说明：模糊查询，根据游戏类别和游戏名
	 * @param i
	 * @param title
	 * @return
	 * List<ActivityGameNews>
	 * author:邓超
	 * 2016-8-4 上午9:36:58
	 */
	public List<ActivityGameNews> queryActivityGameNews(Integer i,String title){
		List<ActivityGameNews> list;
		String sql;
		if(i==-1){
			sql="SELECT * FROM tb_activity_game_news order by ceatetime desc";
		}else{
			sql="SELECT * FROM tb_activity_game_news WHERE category_id='"+i+"' and title like '%"+title+"%' order by ceatetime desc";
		}
		
		list = entityManage.findBySql(sql, ActivityGameNews.class);
		return list;
	}
	
	/**
	 * 方法功能说明：根据id 删除新闻
	 * @param id
	 * @return
	 * int
	 * author:邓超
	 * 2016-8-4 上午10:00:24
	 */
	public int delGameNewsById(Integer id){
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				return -1;
			}else{
				ActivityGameNews gameNews = findGameNewsById(id);
				if(gameNews==null){
					return 0;
				}
				entityManage.delete(gameNews);
				dataCenter.loadActivityGameNews();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	/**
	 * 方法功能说明：根据新闻id查询新闻
	 * @param id
	 * @return
	 * ActivityGameNews
	 * author:邓超
	 * 2016-8-4 上午10:49:36
	 */
	public ActivityGameNews findGameNewsById(Integer id){
		return  (ActivityGameNews) entityManage.findById(ActivityGameNews.class, id);
	}
	
	
}
