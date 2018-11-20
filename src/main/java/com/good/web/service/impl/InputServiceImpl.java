package com.good.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.common.CommonData;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.domain.Advice;
import com.good.web.domain.CarouselImg;
import com.good.web.domain.Show;
import com.good.web.service.AdviceService;
import com.good.web.service.ICarouselImgService;
import com.good.web.service.IRankGameService;
import com.good.web.service.InputService;

/**
 * 输入接口处理类
 * @author max
 *
 */
public class InputServiceImpl implements InputService{
	
	private EntityManage entityManage;
	private DataCenter dataCenter;
	private ICarouselImgService carouselImgService;
	private AdviceService adviceService;
	private IRankGameService rankGameService;
	
	
	public EntityManage getEntityManage() {
		return entityManage;
	}
	public void setEntityManage(EntityManage entityManage) {
		this.entityManage = entityManage;
	}
	public DataCenter getDataCenter() {
		return dataCenter;
	}
	public void setDataCenter(DataCenter dataCenter) {
		this.dataCenter = dataCenter;
	}
	public ICarouselImgService getCarouselImgService() {
		return carouselImgService;
	}
	public void setCarouselImgService(ICarouselImgService carouselImgService) {
		this.carouselImgService = carouselImgService;
	}
	public AdviceService getAdviceService() {
		return adviceService;
	}
	public void setAdviceService(AdviceService adviceService) {
		this.adviceService = adviceService;
	}
	
	public IRankGameService getRankGameService() {
		return rankGameService;
	}
	public void setRankGameService(IRankGameService rankGameService) {
		this.rankGameService = rankGameService;
	}
	/**
     * 输入处理
     * @param data
     * @param request
     * @param response
     */
	public String switchHeadle(int cmdId,JSONObject data, HttpServletRequest request ,HttpServletResponse response){
		String result = null;
		JSONObject json = new JSONObject();
		String imgpath =  PropertiesUtil.getValue("imagepath");
		json.put("path",imgpath);
		switch (cmdId) {
		case 10000://pc 
//			Advice advice = adviceService.getSingleBannerAdvice(CommonData.active);
//			json.put("advice", advice);
			//top banner开启状态
//			if(advice!=null){
//				json.put("astatus", dataCenter.sectionMap.get(CommonData.active).getStatus());
//			}else{
//				json.put("astatus", 1);//不开启
//			}
			List<Show> rankList = dataCenter.rankList;
			json.put("rankList", rankList);
			json.put("rstatus", dataCenter.sectionMap.get(CommonData.ranking).getStatus());//排行榜 开启状态
			json.put("result", 0);
			break;
		case 10001://手机
			List<CarouselImg> imageList = carouselImgService.carouselImgList();
			json.put("hotgameList", dataCenter.phoneRankMap.get(CommonData.hotgamephone));
			json.put("newgameList", dataCenter.phoneRankMap.get(CommonData.newgamephone));
			json.put("imageList", imageList);
			json.put("result", 0);
			break;
		case 10002://top banner
			Advice advice3 = adviceService.getSingleBannerAdvice(CommonData.active);
			json.put("data", advice3);
		  //top banner开启状态
			if(advice3==null){
				json.put("astatus", 1);//不开启
			}else if(advice3.getId()!=null){
				json.put("astatus", dataCenter.sectionMap.get(CommonData.active).getStatus());
			}else{
				json.put("astatus", 1);//不开启
			}
			json.put("result", advice3==null?-1:0);
			break;
		case 10003://开屏大图
			Advice mobileOpenAdvice = adviceService.getSingleBannerAdvice(CommonData.kpimg);
			json.put("data", mobileOpenAdvice);
			json.put("astatus", dataCenter.sectionMap.get(CommonData.kpimg).getStatus());
			json.put("result", mobileOpenAdvice==null?-1:0);
			break;
		case 10004://底部banner
			Advice mobileBottomAdvice = adviceService.getSingleBannerAdvice(CommonData.downimg);
			json.put("data", mobileBottomAdvice);
			json.put("astatus", dataCenter.sectionMap.get(CommonData.downimg).getStatus());
			json.put("result", mobileBottomAdvice==null?-1:0);
			break;
		case 10005://pc排行榜
//			dataCenter.loadRankList();
			List<Show> rankList3 = dataCenter.rankList;
			json.put("rankList", rankList3);
			json.put("result", 0);
			break;
		case 10006://手机排行榜
			List<CarouselImg> imageList3 = carouselImgService.carouselImgList();
			List<Show> rankList21 = dataCenter.phoneRankList;
			json.put("imageList", imageList3);
			json.put("rankList", rankList21);
			json.put("result", 0);
			
			break;
		case 10007://热门游戏
			json.put("hotgameList", rankGameService.rankGameList(CommonData.hotgamephone));
			json.put("result", 0);
			break;
		case 10008://新游戏
			json.put("newgameList", rankGameService.rankGameList(CommonData.newgamephone));
			json.put("result", 0);
			break;
		default:
			break;
		}
		result = JSON.toJSONString(json);
		return result;
	}
}
