package com.good.web.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class User implements Serializable {

    //主键id
    private Integer id;
    //真实名
    private String realname;
    //登录名称
    private String username;
    //登录密码
    private String password;
    //电话
    private String phone;
    //创建时间
    private Date createdate;
    //最后一次登录时间
    private Date logindate;
    //职位
    private String vocation;
    //角色名
    private String rolename;
    //拥有的角色
    private List<Role> roles = new ArrayList<Role>();
    //拥有能访问的资源
    private List<UserResource> resources = new ArrayList<UserResource>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getLogindate() {
        return logindate;
    }

    public void setLogindate(Date logindate) {
        this.logindate = logindate;
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<UserResource> getResources() {
        return resources;
    }

    public void setResources(List<UserResource> resources) {
        this.resources = resources;
    }
}
