package com.good.web.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable{

	private static final long serialVersionUID = 8703990318302774836L;
	
	//主键id
	private int id;
	//角色名
	private String rolename;
	//是否有效
	private boolean isValid;
	//用户列表
	private List<User> users = new ArrayList<User>();
	//权限列表
	private List<Permission> permissions = new ArrayList<Permission>();
	
	//默认构造器	
	public Role(){}
	
	//-------------Getter/Setter-------------//
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Boolean getIsvalid() {
		return this.isValid;
	}

	public void setIsvalid(Boolean isValid) {
		this.isValid = isValid;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
