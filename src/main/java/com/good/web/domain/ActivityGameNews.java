package com.good.web.domain;
// default package

import java.sql.Timestamp;


/**
 * ActivityGameNews entity. @author MyEclipse Persistence Tools
 */

public class ActivityGameNews  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String title;
     private String intro;
     private String context;
     private Timestamp ceatetime;
     private Integer categoryId;
     private Integer gameId;
     private String author;


    // Constructors

    /** default constructor */
    public ActivityGameNews() {
    }

    
    /** full constructor */
    public ActivityGameNews(String title, String intro, String context, Timestamp ceatetime, Integer categoryId, Integer gameId, String author) {
        this.title = title;
        this.intro = intro;
        this.context = context;
        this.ceatetime = ceatetime;
        this.categoryId = categoryId;
        this.gameId = gameId;
        this.author = author;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return this.intro;
    }
    
    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContext() {
        return this.context;
    }
    
    public void setContext(String context) {
        this.context = context;
    }

    public Timestamp getCeatetime() {
        return this.ceatetime;
    }
    
    public void setCeatetime(Timestamp ceatetime) {
        this.ceatetime = ceatetime;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }
    
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getGameId() {
        return this.gameId;
    }
    
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
   








}