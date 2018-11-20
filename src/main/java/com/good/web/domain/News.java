package com.good.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class News {
	private Integer id;
	private String title;
	private Integer categoryId;
	private String author;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String img;
	private String content;
	private String description;
	private String url;
	private Integer state;
	public News(){}
	public News(Integer id, String title, Integer categoryId, String author,
			Date createTime, String img, String content,
			String description, String url, Integer state) {
		super();
		this.id = id;
		this.title = title;
		this.categoryId = categoryId;
		this.author = author;
		this.createTime = createTime;
		this.img = img;
		this.content = content;
		this.description = description;
		this.url = url;
		this.state = state;
	}




	public News(Integer id, String title, Integer categoryId, String author,
			Date createTime, String img, String description, String url,
			Integer state) {
		super();
		this.id = id;
		this.title = title;
		this.categoryId = categoryId;
		this.author = author;
		this.createTime = createTime;
		this.img = img;
		this.description = description;
		this.url = url;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
