package com.good.web.service.impl;

import java.io.File;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.utils.FileUtils;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.base.utils.ValidateUtil;
import com.good.web.domain.Card;
import com.good.web.domain.Image;
import com.good.web.domain.News;
import com.good.web.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;

@Transactional
@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;
	private HttpServletResponse response;
	@Autowired
	private WebApplicationContext webApplicationContext;

	private String message;
	
	@Override
	public String saveOrUpdateImage(Image image, File img,String img1, String imgFileName) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				boolean flag = image.getId()==null ? true : false;
				boolean can = true;
				message = "以下内容不能为空：";
				can = checkNull(image.getTitle(), "&lt;标题&gt;") && can;
				can = checkNull(image.getEnglishTitle(), "&lt;英文标题&gt;") && can;
				can = checkNull(image.getContent(), "&lt;内容&gt;") && can;
				if(image.getSorting()==null || image.getSorting()<1){
					message += "&lt;排序&gt;";
					can = false;
				}
				if(img==null && !checkNull(image.getImg(), "&lt;图片&gt;") && img1==null){
					can = false;
				}
				if(can){
					if(img!=null){
						String upload = PropertiesUtil.getValue("uploadPath");
						String savePath = webApplicationContext.getServletContext().getRealPath(upload);
						String fileName = FileUtils.saveFile(img, savePath, imgFileName, true);
						if(fileName!=null){
							image.setImg(upload + "/" + fileName);
						}else{
							message = "图片上传失败！";
							return message;
						}
					}else{
						image.setImg(img1);
					}
					image.setState(1);
					entityManage.saveOrUpdate(image);
					dataCenter.loadImage();
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
	public List<Image> queryImageList(Image image) {
		StringBuffer sql = new StringBuffer("select * from tb_image where 1=1");
		if(!ValidateUtil.str_isEmpty(image.getTitle())){
			sql.append(" and title like '%").append(image.getTitle()).append("%'");
		}
		if(!ValidateUtil.str_isEmpty(image.getImgType())){
			sql.append(" and img_type='").append(image.getImgType()).append("'");
		}
		if(image.getState()!=null && image.getState()!=-1){
			sql.append(" and state=").append(image.getState());
		}
		sql.append(" order by img_type desc,sorting asc");
		List<Image> list = entityManage.findBySql(sql.toString(), Image.class);
		return list;
	}
	
	@Override
	public String saveOrUpdateCard(Card card, File img,String img1, String imgFileName) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				response.sendRedirect("/login.jsp");
			}else{
				boolean flag = card.getId()==null ? true : false;
				boolean can = true;
				message = "以下内容不能为空：";
				can = checkNull(card.getTitle(), "&lt;标题&gt;") && can;
				if(img==null && !checkNull(card.getImg(), "&lt;图片&gt;") && img1==null){
					can = false;
				}
				if(can){
					if(img!=null){
						String upload = PropertiesUtil.getValue("uploadPath");
						String savePath = webApplicationContext.getServletContext().getRealPath(upload);
						String fileName = FileUtils.saveFile(img, savePath, imgFileName, true);
						if(fileName!=null){
							card.setImg(upload + "/" + fileName);
						}else{
							message = "图片上传失败！";
							return message;
						}
					}else{
						card.setImg(img1);
					}
					card.setState(1);
					entityManage.saveOrUpdate(card);
					dataCenter.loadCard();
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
	public List<Card> queryCardList(Card card) {
		StringBuffer sql = new StringBuffer("select * from tb_card where 1=1");
		if(!ValidateUtil.str_isEmpty(card.getTitle())){
			sql.append(" and title like '%").append(card.getTitle()).append("%'");
		}
		if(card.getCardType()!=null && card.getCardType()!=-1){
			sql.append(" and card_type=").append(card.getCardType());
		}
		if(card.getState()!=null && card.getState()!=-1){
			sql.append(" and state=").append(card.getState());
		}
		sql.append(" order by sorting asc,create_time desc");
		List<Card> list = entityManage.findBySql(sql.toString(), Card.class);
		return list;
	}
	
	@Override
	public Image findImageById(Integer id) {
		return (Image) entityManage.findById(Image.class, id);
	}
	@Override
	public int deleteImageById(Integer id) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				return -1;
			}else{
				Image image = (Image) entityManage.findById(Image.class, id);
				if(image==null){
					return 0;
				}
				if(image.getState()==1){
					image.setState(0);
					entityManage.update(image);
				}else{
					File img = new File(webApplicationContext.getServletContext().getRealPath("/")+image.getImg());
					if(img.exists()) img.delete();
					entityManage.delete(image);
				}
				dataCenter.loadImage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	@Override
	public Card findCardById(Integer id) {
		return (Card) entityManage.findById(Card.class, id);
	}
	@Override
	public int deleteCardById(Integer id) {
		try {
			if(SecurityUtils.getSubject().getSession().getAttribute("USERNAME")==null){
				return -1;
			}else{
				Card card = (Card) entityManage.findById(Card.class, id);
				if(card==null){
					return 0;
				}
				if(card.getState()==1){
					card.setState(0);
					entityManage.update(card);
				}else{
					File img = new File(webApplicationContext.getServletContext().getRealPath("/")+card.getImg());
					if(img.exists()) img.delete();
					entityManage.delete(card);
				}
				dataCenter.loadCard();
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
