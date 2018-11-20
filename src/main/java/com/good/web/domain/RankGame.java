package com.good.web.domain;
// default package



/**
 * RankGame entity. @author MyEclipse Persistence Tools
 */

public class RankGame  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer gameRanking;
     private String gameName;
     private String gameType;
     private String image;
     private Integer reffer;
     private String linkPosition;
     private String andoridUrl;
     private String iosUrl;
     private String downloadUrl;
     private String info;
     private Integer rankId;//tb_show.id
    
     private Integer type;//终端
     
     private Integer status;

    // Constructors

    /** default constructor */
    public RankGame() {
    }

    
    /** full constructor */
    public RankGame(Integer gameRanking, String gameName, String gameType, String image, Integer reffer,
    		String linkPosition, String andoridUrl, String iosUrl, String downloadUrl, String info, Integer rankId) {
        this.gameRanking = gameRanking;
        this.gameName = gameName;
        this.gameType = gameType;
        this.image = image;
        this.reffer = reffer;
        this.linkPosition = linkPosition;
        this.andoridUrl = andoridUrl;
        this.iosUrl = iosUrl;
        this.downloadUrl = downloadUrl;
        this.info = info;
        this.rankId = rankId;
    }
    
    public RankGame(Integer id,Integer gameRanking, String gameName, String gameType, String image, Integer reffer,
    		String linkPosition, String andoridUrl, String iosUrl, String downloadUrl, String info, Integer rankId,Integer type,Integer status) {
    	  this.id=id;
        this.gameRanking = gameRanking;
        this.gameName = gameName;
        this.gameType = gameType;
        this.image = image;
        this.reffer = reffer;
        this.linkPosition = linkPosition;
        this.andoridUrl = andoridUrl;
        this.iosUrl = iosUrl;
        this.downloadUrl = downloadUrl;
        this.info = info;
        this.rankId = rankId;
        this.type = type;
        this.status = status;
    }
   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameRanking() {
        return this.gameRanking;
    }
    
    public void setGameRanking(Integer gameRanking) {
        this.gameRanking = gameRanking;
    }

    public String getGameName() {
        return this.gameName;
    }
    
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameType() {
        return this.gameType;
    }
    
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

    public Integer getReffer() {
        return this.reffer;
    }
    
    public void setReffer(Integer reffer) {
        this.reffer = reffer;
    }


    public String getLinkPosition() {
        return this.linkPosition;
    }
    
    public void setLinkPosition(String linkPosition) {
        this.linkPosition = linkPosition;
    }

    public String getAndoridUrl() {
        return this.andoridUrl;
    }
    
    public void setAndoridUrl(String andoridUrl) {
        this.andoridUrl = andoridUrl;
    }

   

    public String getInfo() {
        return this.info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getRankId() {
        return this.rankId;
    }
    
    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }


		public Integer getType() {
			return type;
		}


		public void setType(Integer type) {
			this.type = type;
		}


		public String getIosUrl() {
			return iosUrl;
		}


		public void setIosUrl(String iosUrl) {
			this.iosUrl = iosUrl;
		}


		public String getDownloadUrl() {
			return downloadUrl;
		}


		public void setDownloadUrl(String downloadUrl) {
			this.downloadUrl = downloadUrl;
		}


		public Integer getStatus() {
			return status;
		}


		public void setStatus(Integer status) {
			this.status = status;
		}
   








}