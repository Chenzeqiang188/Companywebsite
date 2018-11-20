package com.good.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.FileUtils;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.ActivityCard;
import com.good.web.service.ActivityService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Transactional
@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;
	@Autowired
	private WebApplicationContext webApplicationContext;
	private HttpServletResponse response;
	private String message;

	@Override
	public String saveOrUpdateActCard(ActivityCard activityCard, File img, String img1, String imgFileName) {
		try{
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				boolean flag = activityCard.getId()==null ? true : false;
				boolean can = true;
				message = "以下内容不能为空：";
				can = checkNull(activityCard.getTitle(), "《标题》") && can;
			/*	can = checkNull(news.getAuthor(), "《作者》") && can;
				can = checkNull(news.getContent(), "《正文》") && can;*/
				if(img==null && !checkNull(activityCard.getImg() ,"《图片》") && img1==null)
					can = false;
				if(can){
					if(img!=null){
						String upload = PropertiesUtil.getValue("uploadPath");
						String savePath = webApplicationContext.getServletContext().getRealPath(upload);
						String fileName = FileUtils.saveFile(img, savePath, imgFileName, true);
						if(fileName!=null){
							activityCard.setImg(upload + fileName);
						}else{
							message = "图片上传失败！";
							return message;
						}
					}else{
						activityCard.setImg(img1);
					}
					activityCard.setStatus(1);
					entityManage.saveOrUpdate(activityCard);
					dataCenter.loadNews();
					if(flag) message = "保存成功";
					else message = "修改成功！";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "系统异常，请联系技术人员！";
		}
		return message;
	}

	@Override
	public JSONArray queryActCard(ActivityCard activityCard) {
		JSONArray arr = new JSONArray();
		StringBuffer sql = new StringBuffer("select n.id,n.title,n.img,n.url,n.sort,n.status,n.createtime")
				.append(" from tb_activity_card as n where 1=1");
		if(!ValidateUtil.str_isEmpty(activityCard.getTitle())){
			sql.append(" and n.title like '%").append(activityCard.getTitle()).append("%'");
		}
		List<Object[]> list = entityManage.findBySql(sql.toString());
		if(list!=null && list.size()>0){
			for(Object[] os:list){
				JSONObject json = new JSONObject();
				json.put("id", os[0]);
				json.put("title", os[1]);
				json.put("img", os[2]);
				json.put("url", os[3]);
				json.put("sort", os[4]);
				json.put("status", os[5]);
				json.put("createtime", os[6]);
				arr.add(json);
			}
		}
		return arr;
	}

	@Override
	public ActivityCard findActCardById(Integer id) {
		return (ActivityCard) entityManage.findById(ActivityCard.class, id);
	}

	@Override
	public int deleteActCardById(Integer id) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				return -1;
			}else{
				ActivityCard activityCard = (ActivityCard) entityManage.findById(ActivityCard.class, id);
				if(activityCard==null){
					return 0;
				}else{
					File img = new File(webApplicationContext.getServletContext().getRealPath("/")+activityCard.getImg());
					if(img.exists()) img.delete();
					entityManage.delete(activityCard);
				}
				dataCenter.loadActivityCard();
			}
		} catch (Exception e) {
			e.printStackTrace();
			message="保存失败";
		}
		return 1;
	}

	private boolean checkNull(String str,String msg){
		if(ValidateUtil.str_isEmpty(str)){
			message += msg;
			return false;
		}else{
			return true;
		}
	}
}
