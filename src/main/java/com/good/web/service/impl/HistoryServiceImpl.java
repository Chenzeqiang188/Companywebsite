package com.good.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.History;
import com.good.web.service.HistoryService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;

@Transactional
@Service
public class HistoryServiceImpl implements HistoryService {
	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;
	private String message;
	private HttpServletResponse response;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Override
	public String saveOrUpdateHistory(History history) {
		try{
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				boolean flag = history.getId()==null ? true : false;
				boolean can = true;
				message = "以下内容不能为空：";
				if(history.getYear()==null){
					message += "&lt;职位名&gt;";
					can = false;
				}
				if(history.getHistoryDate()==null){
					message += "&lt;职责&gt;";
					can = false;
				}
				can = checkNull(history.getContent(), "&lt;历程&gt;") && can;
				if(can){
					history.setState(1);
					entityManage.saveOrUpdate(history);
					dataCenter.loadHistory();
					if(flag){
						message = "添加成功！";
					}else{
						message = "修改成功！";
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			message = "系统异常，请联系技术人员！";
		}
		return message;
	}

	@Override
	public JSONArray queryHistoryList(History history) {
		JSONArray arr = new JSONArray();
		StringBuffer sql = new StringBuffer("select * from tb_history where 1=1");
		if(history.getYear()!=null && history.getYear()!=0){
			sql.append(" and year=").append(history.getYear());
		}
		if(history.getState()!=null && history.getState()!=-1){
			sql.append(" and state=").append(history.getState());
		}
		sql.append(" order by year desc,history_date desc");
		List<History> list = entityManage.findBySql(sql.toString(),History.class);
		if(list!=null && list.size()>0){
			for(History h:list){
				JSONObject json = new JSONObject();
				json.put("id", h.getId());
				json.put("year", h.getYear());
				json.put("historyDate", new SimpleDateFormat("yyyy年MM月dd日").format(h.getHistoryDate()));
				json.put("content", h.getContent());
				json.put("state", h.getState());
				arr.add(json);
			}
		}
		return arr;
	}
	
	@Override
	public History findHistoryById(Integer id) {
		return (History) entityManage.findById(History.class, id);
	}
	@Override
	public int deleteHistoryById(Integer id) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				return -1;
			}else{
				History history = (History) entityManage.findById(History.class, id);
				if(history==null){
					return 0;
				}
				if(history.getState()==1){
					history.setState(0);
					entityManage.update(history);
				}else{
					entityManage.delete(history);
				}
				dataCenter.loadHistory();
			}
		} catch (Exception e) {
			e.printStackTrace();
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
