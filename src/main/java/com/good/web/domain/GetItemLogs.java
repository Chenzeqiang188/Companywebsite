package com.good.web.domain;
// default package

import java.sql.Timestamp;

/**
 * GetItemLogs entity. @author MyEclipse Persistence Tools
 */

public class GetItemLogs implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer gameid;
	private Integer serverid;
	private String servername;
	private String actorid;
	private String itemcode;
	private String ip;
	private Timestamp createtime;
	private Integer codetype;

	// Constructors

	/** default constructor */
	public GetItemLogs() {
	}

	/** full constructor */
	public GetItemLogs(Integer gameid, Integer serverid, String servername,
			String actorid, String itemcode, String ip, Timestamp createtime,
			Integer codetype) {
		this.gameid = gameid;
		this.serverid = serverid;
		this.servername = servername;
		this.actorid = actorid;
		this.itemcode = itemcode;
		this.ip = ip;
		this.createtime = createtime;
		this.codetype = codetype;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGameid() {
		return this.gameid;
	}

	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}

	public Integer getServerid() {
		return this.serverid;
	}

	public void setServerid(Integer serverid) {
		this.serverid = serverid;
	}

	public String getServername() {
		return this.servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getActorid() {
		return this.actorid;
	}

	public void setActorid(String actorid) {
		this.actorid = actorid;
	}

	public String getItemcode() {
		return this.itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Integer getCodetype() {
		return this.codetype;
	}

	public void setCodetype(Integer codetype) {
		this.codetype = codetype;
	}

}