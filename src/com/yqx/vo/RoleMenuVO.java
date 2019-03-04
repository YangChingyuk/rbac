package com.yqx.vo;

public class RoleMenuVO {

	private int id;//ID
	private int rid;
	private String rname;//½ÇÉ«±àºÅ
	private int mid;
	private String mname;//²Ëµ¥±àºÅ
	public RoleMenuVO(int id, int rid, String rname, int mid, String mname) {
		super();
		this.id = id;
		this.rid = rid;
		this.rname = rname;
		this.mid = mid;
		this.mname = mname;
	}
	public RoleMenuVO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	@Override
	public String toString() {
		return "RoleMenuVO [id=" + id + ", rid=" + rid + ", rname=" + rname + ", mid=" + mid + ", mname=" + mname + "]";
	}
	
	
	
}
