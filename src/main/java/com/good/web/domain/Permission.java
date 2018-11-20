package com.good.web.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Permission implements Serializable{

	private static final long serialVersionUID = 7631408820990995405L;
	
	//主键id
	private int id;
	//权限名
	private String name;
	//action url
	private String url;
	//shiro permission
	private String permission;
	//角色列表
	private List<Role> roles = new ArrayList<Role>();
	
	//构造器
	public Permission(){}

	//-------------Getter/Setter-------------//
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
