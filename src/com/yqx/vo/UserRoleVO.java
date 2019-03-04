package com.yqx.vo;

public class UserRoleVO {

	private int id;//ID
	private int uid;
	private String uname;//ÓÃ»§±àºÅ
	private int rid;
	private String rname;//½ÇÉ«±àºÅ
	
	public UserRoleVO(int id, int uid, String uname, int rid, String rname) {
		super();
		this.id = id;
		this.uid = uid;
		this.uname = uname;
		this.rid = rid;
		this.rname = rname;
	}
	public UserRoleVO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	@Override
	public String toString() {
		return "UserRoleVO [id=" + id + ", uid=" + uid + ", uname=" + uname + ", rid=" + rid + ", rname=" + rname + "]";
	}
	
}
