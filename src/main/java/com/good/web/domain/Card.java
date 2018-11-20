package com.good.web.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Card implements Serializable{
	private Integer id;
	private String title;
	private String img;
	private String url;
	private Integer cardType;
	private Timestamp createTime;
	private Integer sorting;
	private Integer state;
	public Card(){}
	public Card(Integer id, String title, String img, String url,
			Integer cardType, Timestamp createTime, Integer sorting,
			Integer state) {
		super();
		this.id = id;
		this.title = title;
		this.img = img;
		this.url = url;
		this.cardType = cardType;
		this.createTime = createTime;
		this.sorting = sorting;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getSorting() {
		return sorting;
	}
	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
