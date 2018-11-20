package com.good.web.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.shiro.SecurityUtils;

import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.ActivityGameCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;

@Transactional
@Service
public class ActivityGameCodeServiceImpl {
	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;

	private HttpServletResponse httpServletResponse;
	
	String message = "";
	
	public String saveOrUpdateActivityGameCode(ActivityGameCode gameCode){
		
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				httpServletResponse.sendRedirect("/login.jsp");
			}else{
				boolean flag = gameCode.getId()==null?true:false;
				boolean can = true;
				message = "以下内容不能为空：";
				can = checkNull(gameCode.getCode(), "礼包码");
				can = checkNull(gameCode.getStatus(), "礼包领取状态");
				can = checkNull(gameCode.getGameId(), "游戏id");
				can = checkNull(gameCode.getGameName(), "游戏名");
				
				gameCode.setCeatertime(new Timestamp(System.currentTimeMillis()));
				
				if(can){
					
					entityManage.saveOrUpdate(gameCode);
					
				}
				if(flag){
					message = "添加成功！";
				}else{
					message = "修改成功！";
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "系统异常，请联系技术人员！";
		}
		
		return message;
	}
	
	public List<ActivityGameCode> queryActivityGameCode(Integer status){
		String sql = "";
		if(status==-1){
			sql = "select * from tb_activit_game_code order by ceatertime asc";
		}else if(status==0){
			sql = "select * from tb_activit_game_code where status=0 order by ceatertime asc";
		}else{
			sql = "select * from tb_activit_game_code where status=1 order by ceatertime asc";
		}
		
		return entityManage.findBySql(sql,ActivityGameCode.class);
	}
	private boolean checkNull(String str,String msg){
		if(ValidateUtil.str_isEmpty(str)){
			message += msg;
			return false;
		}else{
			return true;
		}
	}
	private boolean checkNull(Integer i,String msg){
		if(i==null||String.valueOf(i)==""){
			message += msg;
			return false;
		}else{
			return true;
		}
	}
	
	
 }
