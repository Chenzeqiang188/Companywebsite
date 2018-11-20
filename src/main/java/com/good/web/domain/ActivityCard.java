package com.good.web.domain;
// default package

import java.sql.Timestamp;

/**
 * ActivityCard entity. @author MyEclipse Persistence Tools
 */

public class ActivityCard implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String img;
	private String url;
	private Integer sort;
	private Integer status;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public ActivityCard() {
	}

	/** full constructor */
	public ActivityCard(String title, String img, String url, Integer sort,
			Timestamp createtime) {
		this.title = title;
		this.img = img;
		this.url = url;
		this.sort = sort;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}