package com.good.web.domain;

public class Image {
	private Integer id;
	private String title;
	private String englishTitle;
	private String img;
	private String content;
	private String imgType;
	private Integer sorting;
	private Integer state;
	public Image(){}
	public Image(Integer id, String title, String englishTitle, String img,
			String content, String imgType, Integer sorting, Integer state) {
		super();
		this.id = id;
		this.title = title;
		this.englishTitle = englishTitle;
		this.img = img;
		this.content = content;
		this.imgType = imgType;
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
	public String getEnglishTitle() {
		return englishTitle;
	}
	public void setEnglishTitle(String englishTitle) {
		this.englishTitle = englishTitle;
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
	public String getImgType() {
		return imgType;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
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
