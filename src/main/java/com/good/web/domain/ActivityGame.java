package com.good.web.domain;
// default package

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * ActivityGame entity. @author MyEclipse Persistence Tools
 */

public class ActivityGame  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String icon;
     private String url;
     private String intro;
     private String imgs;
     private String description;
     private String info;
     private Long playcount;
     private Long endorsecount;
     private Timestamp createtime;
     private Integer sort;


    // Constructors

    /** default constructor */
    public ActivityGame() {
    }

    
    /** full constructor */
    public ActivityGame(String name, String icon, String url, String intro, String imgs, String description, String info, Long playcount, Long endorsecount, Timestamp createtime, Integer sort) {
        this.name = name;
        this.icon = icon;
        this.url = url;
        this.intro = intro;
        this.imgs = imgs;
        this.description = description;
        this.info = info;
        this.playcount = playcount;
        this.endorsecount = endorsecount;
        this.createtime = createtime;
        this.sort = sort;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return this.icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntro() {
        return this.intro;
    }
    
    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImgs() {
        return this.imgs;
    }
    
    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return this.info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }

    public Long getPlaycount() {
        return this.playcount;
    }
    
    public void setPlaycount(Long playcount) {
        this.playcount = playcount;
    }

    public Long getEndorsecount() {
        return this.endorsecount;
    }
    
    public void setEndorsecount(Long endorsecount) {
        this.endorsecount = endorsecount;
    }

    public Timestamp getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Integer getSort() {
        return this.sort;
    }
    
    public void setSort(Integer sort) {
        this.sort = sort;
    }
   








}