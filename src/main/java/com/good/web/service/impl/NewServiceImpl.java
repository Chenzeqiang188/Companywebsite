package com.good.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.FileUtils;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.News;
import com.good.web.service.NewsService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Transactional
@Service
@PropertySource(value = "classpath:config.properties")
public class NewServiceImpl implements NewsService {
	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;
	private HttpServletResponse response;


	@Autowired
	private WebApplicationContext webApplicationContext;
	@Value("${uploadPath}")
	private String uploadPath;

	private String message;

	@Override
	public String saveOrUpdateNews(News news, File img,String img1,String imgFileName) {
		try{
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				boolean flag = news.getId()==null ? true : false;
				boolean can = true;
				message = "以下内容不能为空：";
				can = checkNull(news.getTitle(), "《标题》") && can;
				can = checkNull(news.getAuthor(), "《作者》") && can;
				can = checkNull(news.getContent(), "《正文》") && can;
				if(img==null && !checkNull(news.getImg() ,"《图片》") && img1==null)
					can = false;
				if(can){
					if(img!=null){
						String upload = PropertiesUtil.getValue("uploadPath");
						String savePath = webApplicationContext.getServletContext().getRealPath(upload);
						String fileName = FileUtils.saveFile(img, savePath, imgFileName, true);
						if(fileName!=null){
							news.setImg(upload + fileName);
						}else{
							message = "图片上传失败！";
							return message;
						}
					}else{
						news.setImg(img1);
					}
					news.setState(1);
					entityManage.saveOrUpdate(news);
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
	public JSONArray queryNews(News news) {
		JSONArray arr = new JSONArray();
		StringBuffer sql = new StringBuffer("select n.id,n.title,n.create_time,n.img,n.url,n.author,n.state,nc.cate_name")
				.append(" from tb_news as n left join tb_news_category as nc on n.category_id=nc.id where 1=1");
		if(!ValidateUtil.str_isEmpty(news.getTitle())){
			sql.append(" and n.title like '%").append(news.getTitle()).append("%'");
		}
		if(news.getCategoryId()!=null && news.getCategoryId()!=0){
			sql.append(" and n.category_id=").append(news.getCategoryId());
		}
		if(news.getState()!=-1){
			sql.append(" and n.state=").append(news.getState());
		}
		List<Object[]> list = entityManage.findBySql(sql.toString());
		if(list!=null && list.size()>0){
			for(Object[] os:list){
				JSONObject json = new JSONObject();
				json.put("id", os[0]);
				json.put("title", os[1]);
				json.put("createTime", os[2]);
				json.put("img", os[3]);
				json.put("url", os[4]);
				json.put("author", os[5]);
				json.put("state", os[6]);
				json.put("category", os[7]);
				arr.add(json);
			}
		}
		return arr;
	}
	
	@Override
	public News findNewsById(Integer id) {
		return (News) entityManage.findById(News.class, id);
	}
	
	@Override
	public int deleteNewsById(Integer id) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				return -1;
			}else{
				News news = (News) entityManage.findById(News.class, id);
				if(news==null){
					return 0;
				}
				if(news.getState()==1){
					news.setState(0);
					entityManage.update(news);
				}else{
					File img = new File(webApplicationContext.getServletContext().getRealPath("/")+news.getImg());
					if(img.exists()) img.delete();
					entityManage.delete(news);
				}
				dataCenter.loadNews();
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
