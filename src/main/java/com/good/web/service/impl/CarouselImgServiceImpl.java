package com.good.web.service.impl;

import com.good.web.base.DataCenter;
import com.good.web.base.EntityManage;
import com.good.web.base.common.Page;
import com.good.web.base.utils.FileUtils;
import com.good.web.base.utils.PropertiesUtil;
import com.good.web.domain.CarouselImg;
import com.good.web.service.ICarouselImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图片
 * 项目名称：CompanyWebsite     
 *  
 * 类描述：  
 * 类名称：CarouselImgServiceImpl       
 * 创建人：  周文广
 * 创建时间：2017-2-28 下午06:26:46     
 * 修改人：  
 * 修改时间：2017-2-28 下午06:26:46     
 * 修改备注：     
 *
 */
@Transactional
@Service
public class CarouselImgServiceImpl implements ICarouselImgService {

	@Autowired
	private EntityManage entityManage;
	@Autowired
	private DataCenter dataCenter;

	@Autowired
	WebApplicationContext webApplicationContext;
	private HttpServletResponse response;

	private String message;
	/**
	 * 获取图片列表
	 * @Title: carouselImgList  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-2-28 下午06:27:56 
	 * @return
	 */
	@Override
	public List<CarouselImg> carouselImgList(Timestamp starttime, Timestamp endtime, Page page){
		List<CarouselImg> list=new ArrayList<>();
		try {
			String hql="from CarouselImg where  1=1  ";
			if(starttime!=null && !("").equals(starttime)){
				hql+=" and DATE_FORMAT('"+starttime+"','%Y-%m-%d %H:%i:%s')<=startTime ";
			}
			if(endtime!=null && !("").equals(endtime)){
				hql+=" and DATE_FORMAT('"+endtime+"','%Y-%m-%d %H:%i:%s')>=endTime ";
			}
			hql+=" order by startTime ";
			page.setTotalSize(entityManage.getCountByHql(hql));
			list = entityManage.findByHql(hql, page.getPageNum(), (short) page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  list;
	}
	
	@Override
	public List<CarouselImg> carouselImgList(){
		List<CarouselImg> carouselImgs = new ArrayList<CarouselImg>();
		try {
			Timestamp date = new Timestamp(System.currentTimeMillis());
			List<CarouselImg> list = dataCenter.carouselImgList;
			if(list.size()>0){
	//			System.out.println("mobile轮播图-当前时间："+date+"    ----  数量:"+list.size());
				for (CarouselImg c : list) {
					if(c.getStartTime().before(date) &&  c.getEndTime().after(date) ){
						carouselImgs.add(c);
					}
				}
	//			dataCenter.carouselImgList.clear();
	//			dataCenter.carouselImgList.addAll(carouselImgs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carouselImgs;
	}
	/**
	 * 更新图片
	 * @Title: editCarouselImg  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-2-28 下午06:27:01 
	 * @param img
	 * @param imgFileName
	 * @param carouselImg
	 */
	@Override
	public String editCarouselImg(File img,String imgFileName,CarouselImg carouselImg,String img1){
		try {
			String upload = PropertiesUtil.getValue("uploadPath");
			String savePath = webApplicationContext.getServletContext().getRealPath(upload);
			if(img!=null){
				String fileName = FileUtils.saveFile(img, savePath, imgFileName, true);
				if(fileName!=null){
					carouselImg.setImage(upload + fileName);
				}
			}else{
				carouselImg.setImage(img1);
			}
			entityManage.update(carouselImg);
			dataCenter.loadCarouselImgList();
			message="更新成功";
		} catch (Exception e) {
			e.printStackTrace();
			message="更新失败，请联系工作人员";
		}
		return message;
	}
	/**
	 * 新增图片
	 * @Title: addCarouselImg  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-2-28 下午06:27:42 
	 * @param img
	 * @param imgFileName
	 * @param carouselImg
	 */
	public String addCarouselImg(File img,String imgFileName,CarouselImg carouselImg){
		try {
			String upload = PropertiesUtil.getValue("uploadPath");
			String savePath = webApplicationContext.getServletContext().getRealPath(upload);
			if(img!=null){
				String fileName = FileUtils.saveFile(img, savePath, imgFileName, true);
				if(fileName!=null){
					carouselImg.setImage(upload + fileName);
				}
			}
			carouselImg.setCreateTime(new Timestamp(System.currentTimeMillis()));
			entityManage.save(carouselImg);
			dataCenter.loadCarouselImgList();
			message="添加成功";
		} catch (Exception e) {
			e.printStackTrace();
			message="添加失败,请联系工作人员。";
		}
		return message;
	}
	/**
	 * 删除图片
	 * @Title: delCarouselImg  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-2-28 下午06:39:46 
	 * @param id
	 */
	public int delCarouselImg(Integer id){
		try {
			CarouselImg carouselImg= (CarouselImg)entityManage.findById(CarouselImg.class, id);
			entityManage.delete(carouselImg);
			entityManage.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
			dataCenter.loadCarouselImgList();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public CarouselImg getCarouselImg(Integer id){
		return (CarouselImg) entityManage.findById(CarouselImg.class, id);
	}
}
