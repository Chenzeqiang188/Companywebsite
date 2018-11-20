package com.good.web.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.good.web.service.ActivityGameService;
import org.apache.shiro.SecurityUtils;


import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.FileUtils;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.ActivityGame;
import com.good.web.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;

@Transactional
@Service
public class ActivityGameServiceImpl  implements ActivityGameService {
	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;
	@Autowired
	private WebApplicationContext webApplicationContext;
	HttpServletResponse response;
	private String message;
	

/*	public String saveOrUpdateActivityGame(ActivityGame game, File icon,
			String iconFileName, List<File> imgs, List<String> imgsFileName) {
		try {
			if(ServletActionContext.getRequest().getSession().getAttribute("USERNAME")==null){
				ServletActionContext.getResponse().sendRedirect("/login.jsp");
			}else{
				System.out.println(game.getId());
				boolean flag = game.getId()==null ? true : false;
				System.out.println(flag);
				boolean can = true;
				message = "以下内容不能为空：";
				can = checkNull(game.getName(), "&lt;游戏名&gt;") && can;
				
				can = checkNull(game.getUrl(), "&lt;下载链接&gt;") && can;
				can = checkNull(game.getInfo(),"&lt;简介信息&gt;") && can;
				
				can = checkNull(game.getDescription(),"&lt;描述信息&gt;") && can;
				can = checkNull(game.getInfo(), "&lt;信息&gt;") && can;
				can = checkNull(game.getPlaycount(),"&lt;玩家数量&gt;") && can;
				can = checkNull(game.getEndorsecount(), "&lt;点赞数量&gt;") && can;
				can = checkNull(game.getSort(),"&lt;排序&gt;") && can;
				
				
//				if(game.getHot().intValue()==1)
//					can = checkNull(game.getPlatform(), "&lt;平台&gt;") && can;

				if(imgs==null && !checkNull(game.getImgs(),"&lt;图片&gt;"))
					can = false;
//				if(game.getHot().intValue()==0 && qrCodeImg==null && !checkNull(game.getQrCodeImg(),"&lt;二维码&gt;"))
//					can = false;
				if(icon==null && !checkNull(game.getIcon(),"&lt;图标&gt;"))
					can = false;
				if(can){
					String upload = PropertiesUtil.getValue("uploadPath");
					String savePath = ServletActionContext.getServletContext().getRealPath(upload);
					for(int i=0;i<imgs.size();i++){
					if(imgs.get(i)!=null){
						String fileName = FileUtils.saveFile(imgs.get(i), savePath,imgsFileName.get(i), true);
						String str = null;
					
						if(fileName!=null)
							
						else{
							message = "图片上传失败！";
							return message;
						}
					}
					}
					if(icon!=null){
						String fileName = FileUtils.saveFile(icon, savePath, iconFileName, true);
						if(fileName!=null)
							game.setIcon(upload + fileName);
						else{
							message = "图标上传失败！";
							return message;
						}
					}
//					if(icon!=null){
//						String fileName = FileUtils.saveFile(icon, savePath, iconFileName, true);
//						if(fileName!=null)
//							game.setIcon(upload + fileName);
//						else{
//							message = "图标上传失败！";
//							return message;
//						}
//					}
					game.setCreatetime(new Timestamp(System.currentTimeMillis()));
					entityManage.saveOrUpdate(game);
					//dataCenter.loadGame();
					//ServletActionContext.getServletContext().setAttribute("hotGames", dataCenter.hotGames);
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
*/

	
	private boolean checkNull(String str,String msg){
		if(ValidateUtil.str_isEmpty(str)){
			message += msg;
			return false;
		}else{
			return true;
		}
	}
	private boolean checkNull(Long l,String msg){
		if(l==null||String.valueOf(l)==""){
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
	private boolean checkNull(List<String> s,String msg){
		if(s==null){
			message += msg;
			return false;
		}else{
			return true;
		}
	}

	@Override
	public List<ActivityGame> queryActivityGameList() {
		StringBuffer sql = new StringBuffer(" select * from tb_activity_game order by sort desc");
		return entityManage.findBySql(sql.toString(),ActivityGame.class);
	}

	@Override
	public ActivityGame findActivityGameById(Integer id) {
		return (ActivityGame) entityManage.findById(ActivityGame.class, id);
	}



	@Override
	public int deleteActivityGameById(Integer id) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				return -1;
			}else{
				ActivityGame game = (ActivityGame) entityManage.findById(ActivityGame.class, id);
				if(game==null){
					return 0;
				}
				entityManage.delete(game);
				dataCenter.loadActivityGame();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public List<ActivityGame> findActivityGameByName(String name) {
		
		String sql="SELECT * FROM tb_activity_game WHERE name LIKE '%"+name+"%'";
		return entityManage.findBySql(sql, ActivityGame.class);
	}

	public String saveOrUpdateActivityGame(ActivityGame game,File icon,
			String iconFileName,File[] imgs, List<String> imgsFileName) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				
				boolean flag = game.getId()==null ? true : false;
				
				boolean can = true;
				message = "以下内容不能为空：";
				can = checkNull(game.getName(), "&lt;游戏名&gt;") && can;
				if(flag==true){
					can = checkNull(iconFileName,"&lt;图标&gt;") && can;
					can = checkNull(imgsFileName,"&lt;图片&gt;") && can;
				}
				
				can = checkNull(game.getUrl(), "&lt;下载链接&gt;") && can;
				can = checkNull(game.getInfo(),"&lt;简介信息&gt;") && can;
				
				can = checkNull(game.getDescription(),"&lt;描述信息&gt;") && can;
				can = checkNull(game.getInfo(), "&lt;信息&gt;") && can;
				can = checkNull(game.getPlaycount(),"&lt;玩家数量&gt;") && can;
				can = checkNull(game.getEndorsecount(), "&lt;点赞数量&gt;") && can;
				can = checkNull(game.getSort(),"&lt;排序&gt;") && can;
				
				if(can){
					String upload = PropertiesUtil.getValue("uploadPath");
					String savePath = webApplicationContext.getServletContext().getRealPath(upload);
					if(icon!=null){
						String fileName = FileUtils.saveFile(icon, savePath, iconFileName, true);
						if(fileName!=null){
							game.setIcon(fileName);
						}else{
							message = "图标上传失败";
							return message;
						}
					}
					if(imgs!=null){
						String imgNames = "";
						for(int i=0;i<imgs.length;i++){
							String fileName = FileUtils.saveFile(imgs[i], savePath, imgsFileName.get(i), true);
							imgNames+=(fileName+",");
						}
						if(imgNames!=""){
							game.setImgs(imgNames);
						}else{
							message = "图片上传失败";
							return message;
						}
					}
				game.setCreatetime(new Timestamp(System.currentTimeMillis()));
				entityManage.saveOrUpdate(game);
				dataCenter.loadActivityGame();
				dataCenter.loadGameIdList();
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

}
