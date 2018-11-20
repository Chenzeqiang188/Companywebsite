package com.good.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ServletUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.Dept;
import com.good.web.domain.PositionType;
import com.good.web.domain.Recruit;
import com.good.web.service.RecruitService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Transactional
@Service
public class RecruitServiceImpl implements RecruitService {
	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;
	@Autowired
	private WebApplicationContext webApplicationContext;
	private HttpServletResponse response;
	private String message;

	@Override
	public void addPositionType(String typeName, Integer typeValue,HttpServletResponse response) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				ServletUtil.responseToClient(response, -1, PropertiesUtil.getValue("contentType"));
			}else if(ValidateUtil.str_isEmpty(typeName) || typeValue==null){
				ServletUtil.responseToClient(response, 0, PropertiesUtil.getValue("contentType"));
			}else{
				PositionType pt = new PositionType(null,typeName,typeValue,1);
				entityManage.save(pt);
				dataCenter.loadPositionType();
				ServletUtil.responseToClient(response, 1, PropertiesUtil.getValue("contentType"));
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void addDept(String typeName,HttpServletResponse response) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				ServletUtil.responseToClient(response, -1, PropertiesUtil.getValue("contentType"));
			}else if(ValidateUtil.str_isEmpty(typeName)){
				ServletUtil.responseToClient(response, 0, PropertiesUtil.getValue("contentType"));
			}else{
				Dept dept = new Dept(null, typeName, 1);
				entityManage.save(dept);
				entityManage.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
				dataCenter.loadDept();
				ServletUtil.responseToClient(response, 1, PropertiesUtil.getValue("contentType"));
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int delPositionType(Integer id,HttpServletResponse response) {
		try {
			if (SecurityUtils.getSubject().getSession().getAttribute("USERNAME") == null) {
				return -1;
			}else{
				if (SecurityUtils.getSubject().getSession().getAttribute("USERNAME") == null) {
					ServletUtil.responseToClient(response, -1, PropertiesUtil.getValue("contentType"));
				} else if (id == null) {
					ServletUtil.responseToClient(response, 0, PropertiesUtil.getValue("contentType"));
				} else {
					PositionType news = (PositionType) entityManage.findById(PositionType.class, id);
					if (news!= null) {
						entityManage.delete(news);
						entityManage.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
					}
					dataCenter.loadPositionType();
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int delDept(Integer id, HttpServletResponse response) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				return -1;
			}else{
				if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
					ServletUtil.responseToClient(response, -1, PropertiesUtil.getValue("contentType"));
				}else if(id==null){
					ServletUtil.responseToClient(response, 0, PropertiesUtil.getValue("contentType"));
				}else{
					Dept dept = (Dept) entityManage.findById(Dept.class, id);
					if(dept!=null) {
						entityManage.delete(dept);
						entityManage.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
					}
					dataCenter.loadDept();
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}
	@Override
	public String addOrUpdateRecruit(Recruit recruit) {
		try{
			boolean flag = recruit.getId()==null ? true : false;
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				boolean can = true;
				message = "以下内容不能为空：";
				can = checkNull(recruit.getPosition(), "&lt;职位名&gt;") && can;
				can = checkNull(recruit.getDuty(), "&lt;职责&gt;") && can;
				can = checkNull(recruit.getRequired(), "&lt;任职要求&gt;") && can;
				if(can){
					recruit.setState(1);
					if(recruit.getType()==null){
						recruit.setType(0);//默认为社会招聘
					}
					entityManage.saveOrUpdate(recruit);
					dataCenter.loadRecruit();
					if(flag) message = "添加成功！";
					else message = "修改成功！";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			message = "系统异常，请联系技术人员！";
		}
		return message;
	}

	@Override
	public JSONArray queryRecruitList(Recruit recruit) {
		JSONArray arr = new JSONArray();
		StringBuffer sql = new StringBuffer("select r.id,r.position,p1.type_name as t1,p2.type_name as t2,d.dept_name,r.recruit_num,r.create_time,r.duty,r.required,r.state,r.type")
			.append(" from tb_recruit as r left join tb_position_type as p1 on r.position_type1=p1.id")
			.append(" left join tb_position_type as p2 on r.position_type2=p2.id")
			.append(" left join tb_dept as d on r.dept_id=d.id where 1=1");
		if(!ValidateUtil.str_isEmpty(recruit.getPosition())){
			sql.append(" and r.position like '%").append(recruit.getPosition()).append("%'");
		}
		if(recruit.getPositionType1()!=null && recruit.getPositionType1()!=0){
			sql.append(" and r.position_type1=").append(recruit.getPositionType1());
		}
		if(recruit.getPositionType2()!=null && recruit.getPositionType2()!=0){
			sql.append(" and r.position_type2=").append(recruit.getPositionType2());
		}
		if(recruit.getDeptId()!=null && recruit.getDeptId()!=0){
			sql.append(" and r.dept_id=").append(recruit.getDeptId());
		}
		if(recruit.getState()!=null && recruit.getState()!=-1){
			sql.append(" and r.state=").append(recruit.getState());
		}
		sql.append(" order by r.create_time desc");
		List<Object[]> list = entityManage.findBySql(sql.toString());
		if(list!=null && list.size()>0){
			for(Object[] os:list){
				System.out.println(os[3] + "-" + os[2]);
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
				json.put("type", os[10]);
				arr.add(json);
			}
		}
		System.out.println(arr);
		return arr;
	}

	@Override
	public Recruit findRecruitById(Integer id) {
		return (Recruit) entityManage.findById(Recruit.class, id);
	}
	
	@Override
	public int deleteRecruitById(Integer id) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				return -1;
			}else{
				Recruit recruit = (Recruit) entityManage.findById(Recruit.class, id);
				if(recruit==null){
					return 0;
				}
				if(recruit.getState()==1){
					recruit.setState(0);
					entityManage.update(recruit);
				}else{
					entityManage.delete(recruit);
				}
				dataCenter.loadRecruit();
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
