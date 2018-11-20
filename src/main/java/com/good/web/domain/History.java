package com.good.web.domain;

import java.sql.Date;

public class History {
	private Integer id;
	private Integer year;
	private Date historyDate;
	private String content;
	private Integer state;
	public History(){}
	public History(Integer id, Integer year, Date historyDate, String content,
			Integer state) {
		super();
		this.id = id;
		this.year = year;
		this.historyDate = historyDate;
		this.content = content;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Date getHistoryDate() {
		return historyDate;
	}
	public void setHistoryDate(Date historyDate) {
		this.historyDate = historyDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
