package com.good.web.domain;

import java.util.List;

// default package



/**
 * Show entity. @author MyEclipse Persistence Tools
 */

public class Show  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer type;
     private String title;
     private Integer status;
     private Integer parentId;
     
     private List<RankGame> gameList;

    // Constructors

    /** default constructor */
    public Show() {
    }

    
    /** full constructor */
    public Show(Integer type, String title, Integer status, Integer parentId) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.parentId = parentId;
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

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParentId() {
        return this.parentId;
    }
    
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


		public List<RankGame> getGameList() {
			return gameList;
		}


		public void setGameList(List<RankGame> gameList) {
			this.gameList = gameList;
		}

   








}