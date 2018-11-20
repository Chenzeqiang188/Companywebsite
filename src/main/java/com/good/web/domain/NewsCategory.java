package com.good.web.domain;

public class NewsCategory {
	private Integer id;
	private String cateName;
	private Integer sorting;
	private Integer state;
	public NewsCategory(){}
	public NewsCategory(Integer id, String cateName, Integer sorting,
			Integer state) {
		super();
		this.id = id;
		this.cateName = cateName;
		this.sorting = sorting;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
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
