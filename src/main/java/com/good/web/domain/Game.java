package com.good.web.domain;

public class Game {
	private Integer id;
	private String gameName;
	private String img;
	private String qrCodeImg;
	private String icon;
	private String description;
	private String host;
	private String downloadUrl;
	private String platform;
	private Integer hot;
	private Integer state;
	private Integer sort;
	public Game(){}
	public Game(Integer id, String gameName, String img, String qrCodeImg,
			String icon, String description, String host, String downloadUrl,
			String platform, Integer hot, Integer state) {
		super();
		this.id = id;
		this.gameName = gameName;
		this.img = img;
		this.qrCodeImg = qrCodeImg;
		this.icon = icon;
		this.description = description;
		this.host = host;
		this.downloadUrl = downloadUrl;
		this.platform = platform;
		this.hot = hot;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getQrCodeImg() {
		return qrCodeImg;
	}
	public void setQrCodeImg(String qrCodeImg) {
		this.qrCodeImg = qrCodeImg;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
