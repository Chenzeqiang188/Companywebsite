package com.good.web.domain;

import java.io.Serializable;

public class UserResource implements Serializable {

	private static final long serialVersionUID = 7041894345332651956L;
	
	//主键id
    private Integer id;
    //资源类型
    private String restype;
    //所属用户
    private User user;
    //资源关联标识
    private String relationid;
	
	public UserResource(){}
	
	
	//gettter and setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRestype() {
		return restype;
	}

	public void setRestype(String restype) {
		this.restype = restype;
	}

	public String getRelationid() {
		return relationid;
	}

	public void setRelationid(String relationid) {
		this.relationid = relationid;
	}
	
}
