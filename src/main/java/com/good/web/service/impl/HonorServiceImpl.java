package com.good.web.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;

import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.History;
import com.good.web.domain.Honor;
import com.good.web.service.HonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;

@Transactional
@Service
public class HonorServiceImpl implements HonorService{
	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;
	private String message;
	private HttpServletResponse response;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Override
	public String saveOrUpdateHonor(Honor honor) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				boolean flag = honor.getId()==null ? true : false;
				boolean can = true;
				message = "以下内容不能为空：";
				if(honor.getYear()==null || honor.getYear()==0){
					message += "&lt;年份&gt;";
				}
				can = checkNull(honor.getWinner(), "&lt;获取产品/单位&gt;") && can;
				can = checkNull(honor.getAwardUnit(), "&lt;颁发单位&gt;") && can;
				can = checkNull(honor.getHonor(), "&lt;荣誉&gt;") && can;
				
				if(can){
					honor.setState(1);
					entityManage.saveOrUpdate(honor);
					dataCenter.loadHonor();
					if(flag) message = "添加成功！";
					else message = "修改成功！";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统异常，请联系技术人员！";
		}
		return message;
	}

	@Override
	public List<Honor> queryHonorList(Honor honor) {
		StringBuffer sql = new StringBuffer("select * from tb_honor where 1=1");
		if(honor.getYear()!=null && honor.getYear()!=0){
			sql.append(" and year=").append(honor.getYear());
		}
		if(!ValidateUtil.str_isEmpty(honor.getWinner())){
			sql.append(" and winner like '%").append(honor.getWinner()).append("%'");
		}
		if(!ValidateUtil.str_isEmpty(honor.getAwardUnit())){
			sql.append(" and award_unit like '%").append(honor.getAwardUnit()).append("%'");
		}
		if(honor.getState()!=null && honor.getState()!=-1){
			sql.append(" and state=").append(honor.getState());
		}
		sql.append(" order by year desc, award_date desc");
		List<Honor> list = entityManage.findBySql(sql.toString(),Honor.class);
		return list;
	}
	
	@Override
	public Honor findHonorById(Integer id) {
		return (Honor) entityManage.findById(Honor.class, id);
	}
	@Override
	public int deleteHonorById(Integer id) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				return -1;
			}else{
				Honor honor = (Honor) entityManage.findById(Honor.class, id);
				if(honor==null){
					return 0;
				}
				if(honor.getState()==1){
					honor.setState(0);
					entityManage.update(honor);
				}else{
					entityManage.delete(honor);
				}
				dataCenter.loadHonor();
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
