package com.good.web.domain;

public class PositionType {
	private Integer id;
	private String typeName;
	private Integer typeValue;
	private Integer state;
	public PositionType(){}
	public PositionType(Integer id, String typeName, Integer typeValue,
			Integer state) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.typeValue = typeValue;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(Integer typeValue) {
		this.typeValue = typeValue;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
