package com.good.web.service;

import java.util.List;

import com.good.web.base.common.Page;
import com.good.web.domain.Show;
/**
 * 板块 和榜单
 * 项目名称：CompanyWebsite     
 *  
 * 类描述：  
 * 类名称：IShowService       
 * 创建人：  周文广
 * 创建时间：2017-3-6 下午05:47:17     
 * 修改人：  
 * 修改时间：2017-3-6 下午05:47:17     
 * 修改备注：     
 *
 */
public interface IShowService {
  /**
   * 获取展示板块
   * @Title: sectionList  
   * @Description: 
   * @author：     周文广
   * @date 2017-3-6 下午05:47:34 
   * @return
   */
	public List<Show> sectionList(Integer type,Page page);
	/**
	 * 获取榜单
	 * @Title: showList  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-6 下午05:47:49 
	 * @return
	 */
	public List<Show> showList(Integer type,String title,Page page);
	/**
	 * 更新榜单
	 * @Title: editShow  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-6 下午05:48:07 
	 * @param show
	 */
	public void editShow(Show show);
	/**
	 * 添加榜单
	 * @Title: addShow  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-6 下午05:48:56 
	 * @param show
	 */
	public void addShow(Show show);
	/**
	 * 获取单个榜单
	 * @Title: getShow  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-6 下午05:49:08 
	 * @param id
	 * @return
	 */
	public Show getShow(Integer id);
	/**
	 * 更新展示板块
	 * @Title: editSection  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-6 下午05:59:47 
	 * @param show
	 */
	String editSection(Show show);
	/**
	 * 删除榜单
	 * @Title: delShow  
	 * @Description: 
	 * @author：     周文广
	 * @date 2017-3-6 下午06:47:38 
	 * @param parseInt
	 */
	public int delShow(Integer parseInt);
}
