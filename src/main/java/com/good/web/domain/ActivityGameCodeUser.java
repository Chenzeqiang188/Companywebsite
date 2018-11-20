package com.good.web.domain;
// default package

import java.sql.Timestamp;


/**
 * ActivityGameCodeUser entity. @author MyEclipse Persistence Tools
 */

public class ActivityGameCodeUser  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String userid;
     private String code;
     private Integer CId;
     private Timestamp ceatetime;
     private Integer gameId;


    // Constructors

    /** default constructor */
    public ActivityGameCodeUser() {
    }

    
    /** full constructor */
    public ActivityGameCodeUser(String userid, String code, Integer CId, Timestamp ceatetime, Integer gameId) {
        this.userid = userid;
        this.code = code;
        this.CId = CId;
        this.ceatetime = ceatetime;
        this.gameId = gameId;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCId() {
        return this.CId;
    }
    
    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public Timestamp getCeatetime() {
        return this.ceatetime;
    }
    
    public void setCeatetime(Timestamp ceatetime) {
        this.ceatetime = ceatetime;
    }

    public Integer getGameId() {
        return this.gameId;
    }
    
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
   








}