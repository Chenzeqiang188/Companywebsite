package com.good.web.domain;
// default package

import java.sql.Timestamp;


/**
 * Advice entity. @author MyEclipse Persistence Tools
 */

public class Advice  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer type;
     private String minImage;
     private String image;
     private Timestamp createTime;
     private Timestamp startTime;
     private Timestamp endTime;
     private Integer position;
     private String advertisement;
     private Integer reffer;
     private String refferUrl;
     private Integer showTime;
     private Double cooking;
     private Integer sectionId;
     private Integer status;

    // Constructors

    /** default constructor */
    public Advice() {
    }

    
    /** full constructor */
    public Advice(Integer type, String minImage, String image, Timestamp startTime, Timestamp endTime, Integer position, String advertisement, Integer reffer, String refferUrl, Integer showTime, Double cooking, Integer sectionId) {
        this.type = type;
        this.minImage = minImage;
        this.image = image;
        this.startTime = startTime;
        this.endTime = endTime;
        this.position = position;
        this.advertisement = advertisement;
        this.reffer = reffer;
        this.refferUrl = refferUrl;
        this.showTime = showTime;
        this.cooking = cooking;
        this.sectionId = sectionId;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public String getMinImage() {
        return this.minImage;
    }
    
    public void setMinImage(String minImage) {
        this.minImage = minImage;
    }

    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
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

    public Integer getPosition() {
        return this.position;
    }
    
    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getAdvertisement() {
        return this.advertisement;
    }
    
    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public Integer getReffer() {
        return this.reffer;
    }
    
    public void setReffer(Integer reffer) {
        this.reffer = reffer;
    }

    public String getRefferUrl() {
        return this.refferUrl;
    }
    
    public void setRefferUrl(String refferUrl) {
        this.refferUrl = refferUrl;
    }

    public Integer getShowTime() {
        return this.showTime;
    }
    
    public void setShowTime(Integer showTime) {
        this.showTime = showTime;
    }

    public Double getCooking() {
        return this.cooking;
    }
    
    public void setCooking(Double cooking) {
        this.cooking = cooking;
    }

    public Integer getSectionId() {
        return this.sectionId;
    }
    
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }


		public Integer getStatus() {
			return status;
		}


		public void setStatus(Integer status) {
			this.status = status;
		}


		public Timestamp getCreateTime() {
			return createTime;
		}


		public void setCreateTime(Timestamp createTime) {
			this.createTime = createTime;
		}
   








}