package com.good.web.domain;

import java.sql.Date;

public class Honor {
	private Integer id;
	private Date awardDate;
	private String winner;
	private String awardUnit;
	private String honor;
	private Integer year;
	private Integer sorting;
	private Integer state;
	public Honor(){}
	public Honor(Integer id, Date awardDate, String winner, String awardUnit,
			String honor, Integer year, Integer sorting, Integer state) {
		super();
		this.id = id;
		this.awardDate = awardDate;
		this.winner = winner;
		this.awardUnit = awardUnit;
		this.honor = honor;
		this.year = year;
		this.sorting = sorting;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getAwardDate() {
		return awardDate;
	}
	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getAwardUnit() {
		return awardUnit;
	}
	public void setAwardUnit(String awardUnit) {
		this.awardUnit = awardUnit;
	}
	public String getHonor() {
		return honor;
	}
	public void setHonor(String honor) {
		this.honor = honor;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
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
