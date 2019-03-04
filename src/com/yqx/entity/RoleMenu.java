package com.yqx.entity;

/*
 * ½ÇÉ«²Ëµ¥Àà
 * */
public class RoleMenu {
	
	private int id;//ID
	private int rid;//½ÇÉ«±àºÅ
	private int mid;//²Ëµ¥±àºÅ
	
	public RoleMenu(int id, int rid, int mid) {
		super();
		this.id = id;
		this.rid = rid;
		this.mid = mid;
	}

	public RoleMenu() {
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

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	@Override
	public String toString() {
		return "RoleMenu [id=" + id + ", rid=" + rid + ", mid=" + mid + "]";
	}
	
	
}
