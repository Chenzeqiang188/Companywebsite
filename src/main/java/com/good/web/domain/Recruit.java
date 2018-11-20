package com.good.web.domain;

import java.sql.Timestamp;

public class Recruit {
	private Integer id;
	private String position;
	private String recruitNum;
	private Integer positionType1;
	private Integer positionType2;
	private Integer deptId;
	private Timestamp createTime;
	private String duty;
	private String required;
	private Integer state;
	private Integer type;
	public Recruit(){}
	public Recruit(Integer id, String position, String recruitNum,
			Integer positionType1, Integer positionType2, Integer deptId,
			Timestamp createTime, String duty, String required, Integer state) {
		super();
		this.id = id;
		this.position = position;
		this.recruitNum = recruitNum;
		this.positionType1 = positionType1;
		this.positionType2 = positionType2;
		this.deptId = deptId;
		this.createTime = createTime;
		this.duty = duty;
		this.required = required;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRecruitNum() {
		return recruitNum;
	}
	public void setRecruitNum(String recruitNum) {
		this.recruitNum = recruitNum;
	}
	public Integer getPositionType1() {
		return positionType1;
	}
	public void setPositionType1(Integer positionType1) {
		this.positionType1 = positionType1;
	}
	public Integer getPositionType2() {
		return positionType2;
	}
	public void setPositionType2(Integer positionType2) {
		this.positionType2 = positionType2;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
