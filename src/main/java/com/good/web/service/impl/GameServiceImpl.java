package com.good.web.service.impl;

import java.io.File;
import java.util.List;

import org.apache.shiro.SecurityUtils;


import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.FileUtils;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.Game;
import com.good.web.domain.History;
import com.good.web.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;

@Transactional
@Service
public class GameServiceImpl implements GameService{
	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;

	@Autowired
	WebApplicationContext webApplicationContext;
	private HttpServletResponse response;


	private String message="";

	@Override
	public String saveOrUpdateHotGame(Game game, File img, String img1, String imgFileName) {
		try{
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				boolean flag = game.getId()==null ? true : false;
				boolean can = true;
				message = "以下内容不能为空：";
				can = checkNull(game.getGameName(), "&lt;游戏名&gt;") && can;

				if(img==null && !checkNull(game.getImg() ,"《图片》") && img1==null)
					can = false;
				if(can){
					if(img!=null){
						String upload = PropertiesUtil.getValue("uploadPath");
						String savePath = webApplicationContext.getServletContext().getRealPath(upload);
						String fileName = FileUtils.saveFile(img, savePath, imgFileName, true);
						if(fileName!=null){
							game.setImg(upload + fileName);
						}else{
							message = "图片上传失败！";
							return message;
						}
					}else{
						game.setImg(img1);
					}
					//game.setState(1);
					entityManage.saveOrUpdate(game);
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
	public String saveOrUpdateGame(Game game, File img, String imgFileName,
			File qrCodeImg, String qrCodeImgFileName,String img1,String img2) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				boolean flag = game.getId()==null ? true : false;
				boolean can = true;
				message = "以下内容不能为空：";
				can = checkNull(game.getGameName(), "&lt;游戏名&gt;") && can;
				if(game.getHot().intValue()==1)
					can = checkNull(game.getPlatform(), "&lt;平台&gt;") && can;
				can = checkNull(game.getDescription(), "&lt;简介&gt;") && can;
				if(img==null && !checkNull(game.getImg(),"&lt;图片&gt;") && img1==null && img2 ==null)
					can = false;
				if(can){
					String upload = PropertiesUtil.getValue("uploadPath");
					String savePath = webApplicationContext.getServletContext().getRealPath(upload);
					if(img!=null){
						String fileName = FileUtils.saveFile(img, savePath, imgFileName, true);
						if(fileName!=null)
							game.setImg(upload + fileName);
						else{
							message = "图片上传失败！";
							return message;
						}
					}else{
						game.setImg(img1);
					}
					if(qrCodeImg!=null){
						String fileName = FileUtils.saveFile(qrCodeImg, savePath, qrCodeImgFileName, true);
						if(fileName!=null)
							game.setQrCodeImg(upload + fileName);
						else{
							message = "二维码上传失败！";
							return message;
						}
					}else{
						game.setQrCodeImg(img2);
					}
					entityManage.saveOrUpdate(game);
					dataCenter.loadGame();
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
	public List<Game> queryGameList(Game game) {
		StringBuffer sql = new StringBuffer(" select * from tb_game where 1=1");
		if(!ValidateUtil.str_isEmpty(game.getGameName()))
			sql.append(" and game_name like '%").append(game.getGameName()).append("%'");
		if(game.getHot()!=null && game.getHot()!=-1)
			sql.append(" and hot=").append(game.getHot());
		if(game.getState()!=null && game.getState()!=-1)
			sql.append(" and state=").append(game.getState());
		sql.append(" order by hot desc");
		return entityManage.findBySql(sql.toString(),Game.class);
	}
	
	@Override
	public Game findGameById(Integer id) {
		return (Game) entityManage.findById(Game.class, id);
	}
	@Override
	public int deleteGameById(Integer id) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				return -1;
			}else {
				Game game = (Game) entityManage.findById(Game.class, id);
				if (game == null) {
					return 0;
				}
				if (game.getState().intValue() == 1) {
					game.setState(0);
					entityManage.update(game);
				} else {
					File img = new File(webApplicationContext.getServletContext().getRealPath("/") + game.getImg());
					if (img.exists()) img.delete();
					File qrCodeImg = new File(webApplicationContext.getServletContext().getRealPath("/") + game.getImg());
					if (qrCodeImg.exists()) qrCodeImg.delete();
					entityManage.delete(game);
//				}
					dataCenter.loadGame();
				}
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
