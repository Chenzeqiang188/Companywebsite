package com.good.web.base.common;
/**
 * 
 * 项目名称：goodcommunity     
 *  
 * 类描述：  分页用数据
 * 类名称：Page       
 * 创建人：  周文广
 * 创建时间：2016-10-25 下午02:41:04     
 * 修改人：  
 * 修改时间：   
 * 修改备注：
 */
public class Page {

	/**
	 * 页数、第几页
	 */
	private int pageNum=1;
	/**
	 * 每页显示数
	 */
	private int pageSize = 10;
	/**
	 * 总条数
	 */
	private int totalSize=0;
    /**
     * 总页数
     */
  private int totalPage=1;
	
	public int getPageNum() {
		if(getTotalPage()>0 && pageNum>getTotalPage()){
			return 1;
		}else{
			return pageNum;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getTotalPage() {
		return (int)Math.ceil(Double.valueOf(totalSize)/Double.valueOf(pageSize));
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
