package com.good.web.domain;
// default package

import java.sql.Timestamp;


/**
 * ActivitGameCode entity. @author MyEclipse Persistence Tools
 */

public class ActivityGameCode  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String code;
     private Integer status;
     private Integer gameId;
     private String gameName;
     private Timestamp ceatertime;


    // Constructors

    /** default constructor */
    public ActivityGameCode() {
    }

    
    /** full constructor */
    public ActivityGameCode(String code, Integer status, Integer gameId, String gameName, Timestamp ceatertime) {
        this.code = code;
        this.status = status;
        this.gameId = gameId;
        this.gameName = gameName;
        this.ceatertime = ceatertime;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGameId() {
        return this.gameId;
    }
    
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return this.gameName;
    }
    
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Timestamp getCeatertime() {
        return this.ceatertime;
    }
    
    public void setCeatertime(Timestamp ceatertime) {
        this.ceatertime = ceatertime;
    }

}