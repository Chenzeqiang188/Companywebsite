package com.good.web.domain;
// default package

import java.sql.Timestamp;


/**
 * CarouselFigure entity. @author MyEclipse Persistence Tools
 */

public class CarouselImg  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String image;
     private String url;
     private Timestamp createTime;
     private Timestamp startTime;
     private Timestamp endTime;
     private Integer sectionId;
     private Integer reffer;
     private Integer status;
    // Constructors

    /** default constructor */
    public CarouselImg() {
    }

    public CarouselImg(Integer id) {
    	this.id=id;
    }
    /** full constructor */
    public CarouselImg(String image, String url, Timestamp startTime, Timestamp endTime, Integer sectionId) {
        this.image = image;
        this.url = url;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sectionId = sectionId;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getSectionId() {
        return this.sectionId;
    }
    
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

		public Integer getReffer() {
			return reffer;
		}

		public void setReffer(Integer reffer) {
			this.reffer = reffer;
		}

		public Timestamp getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Timestamp createTime) {
			this.createTime = createTime;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}
   








}