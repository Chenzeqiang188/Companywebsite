package com.good.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.FileUtils;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.Advice;
import com.good.web.service.AdviceService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class AdviceServiceImpl implements AdviceService{

	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;
	@Autowired
	private WebApplicationContext webApplicationContext;
	private HttpServletResponse response;

	private String message;
	
	@Override
	public String saveOrUpdateAdvice(Advice advice, File img, String img1, String imgFileName) {
		try{
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				boolean flag = advice.getId()==null ? true : false;
				boolean can = true;
				message = "以下内容不能为空：";
				/*can = checkNull(advice.getTitle(), "《标题》") && can;
				can = checkNull(news.getAuthor(), "《作者》") && can;
				can = checkNull(news.getContent(), "《正文》") && can;*/
				if(img==null && !checkNull(advice.getImage() ,"《图片》") && img1==null)
					can = false;
				if(can){
					if(img!=null){
						String upload = PropertiesUtil.getValue("uploadPath");
						String savePath = webApplicationContext.getServletContext().getRealPath(upload);
						String fileName = FileUtils.saveFile(img, savePath, imgFileName, true);
						if(fileName!=null){
							advice.setImage(upload + fileName);
						}else{
							message = "图片上传失败！";
							return message;
						}
					}else{
						advice.setImage(img1);
					}
					//advice.setStatus(1);
					entityManage.saveOrUpdate(advice);
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
	public String saveOrUpdateAdvice2(Advice advice, File img, String imgFileName, File qrCodeImg, String qrCodeImgFileName, String img1, String img2) {
		try{
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				boolean flag = advice.getId()==null ? true : false;
				boolean can = true;
				message = "以下内容不能为空：";
				if(img==null && !checkNull(advice.getImage() ,"《图片》") &&  img2==null)
					can = false;
				if(can){
					String upload = PropertiesUtil.getValue("uploadPath");
					String savePath = webApplicationContext.getServletContext().getRealPath(upload);
					if(img!=null){
						String fileName = FileUtils.saveFile(img, savePath, imgFileName, true);
						if(fileName!=null)
							advice.setMinImage(upload + fileName);
						else{
							message = "小图片上传失败！";
							return message;
						}
					}else{
						advice.setMinImage(img1);
					}
					if(qrCodeImg!=null){
						String fileName = FileUtils.saveFile(qrCodeImg, savePath, qrCodeImgFileName, true);
						if(fileName!=null)
							advice.setImage(upload + fileName);
						else{
							message = "图片上传失败！";
							return message;
						}
					}else{
						advice.setImage(img2);
					}
					entityManage.saveOrUpdate(advice);
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
	public JSONArray queryAdviceList(Advice advice) {
		JSONArray arr = new JSONArray();
		if(advice == null){
			advice = new Advice();
		}
		StringBuffer sql = new StringBuffer(" select * from tb_advice where 1=1");
		if(advice.getType()!=null){
			sql.append(" and type=").append(advice.getType());
		}
		if(advice.getReffer()!=null){
			sql.append(" and reffer=").append(advice.getReffer());
		}
		if(advice.getPosition()!=null){
			sql.append(" and position=").append(advice.getPosition());
		}
		if(advice.getSectionId()!=null){
			sql.append(" and show_id=").append(advice.getSectionId());
		}
		if(!ValidateUtil.str_isEmpty(advice.getRefferUrl())){
			sql.append(" and reffer_url like '%").append(advice.getRefferUrl()).append("%'");
		}
		sql.append(" order by id desc");
		List<Object[]> list = entityManage.findBySql(sql.toString());
		if(list!=null && list.size()>0){
			for(Object[] os:list){
				JSONObject json = new JSONObject();
				json.put("id", os[0]);
				json.put("type", os[1]);
				json.put("minImage", os[2]);
				json.put("image", os[3]);
				json.put("createTime", os[4]);
				json.put("startTime", os[5]);
				json.put("endTime", os[6]);
				json.put("position", os[7]);
				json.put("advertisement", os[8]);
				json.put("reffer", os[9]);
				json.put("refferUrl", os[10]);
				json.put("showTime", os[11]);
				json.put("cooking", os[12]);
				json.put("sectionId", os[13]);
				json.put("status", os[14]);
				arr.add(json);
			}
		}
		return arr;
	}
	@Override
	public Advice findAdviceById(Integer id) {
		return (Advice)entityManage.findById(Advice.class, id);
	}
	@Override
	public int deleteAdviceById(Integer id) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				return -1;
			}else {
				Advice game = (Advice) entityManage.findById(Advice.class, id);
				if (game == null) {
					return 0;
				} else {
					File img = new File(webApplicationContext.getServletContext().getRealPath("/") + game.getImage());
					if (img.exists()) img.delete();
					File minImg = new File(webApplicationContext.getServletContext().getRealPath("/") + game.getMinImage());
					if (minImg.exists()) minImg.delete();
					entityManage.delete(game);
					dataCenter.loadGame();
					return 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	public void setEntityManage(EntityManage entityManage) {
		this.entityManage = entityManage;
	}
	
	public void setDataCenter(DataCenter dataCenter) {
		this.dataCenter = dataCenter;
	}
	@Override
	public Advice getSingleBannerAdvice(Integer position) {
//		List<Advice> adviceall = new ArrayList<Advice>();
		List<Advice> advices = new ArrayList<Advice>();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		List<Advice> list = dataCenter.adviceList;
		if(list.size()>0 ){
//			System.out.println("开始时间："+t.getStartTime()+"/ 结束时间:"+t.getEndTime()+"/ 当前时间："+now+"/ 所属分类:"+t.getSectionId()+"=="+position);
//			System.out.println("pc首页-当前时间："+now+"    ----  数量:"+list.size());
			for (Advice t : list) {
					if(t.getStartTime().before(now) &&  t.getEndTime().after(now) ){
//						adviceall.add(t);
						if(t.getSectionId().equals(position) || t.getSectionId() == position){
							advices.add(t);
						}
				}
				
			}
//			dataCenter.adviceList.clear();
//			dataCenter.adviceList.addAll(adviceall);
		}
		//随机
		if(advices.size()>0){
			int index = new Random().nextInt(advices.size());
			return advices.get(index);
		} else {
			return null;
		}
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
