package com.good.web.domain;

public class Dept {
	private Integer id;
	private String deptName;
	private Integer state;
	public Dept(){}
	public Dept(Integer id, String deptName, Integer state) {
		super();
		this.id = id;
		this.deptName = deptName;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
