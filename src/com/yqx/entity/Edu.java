package com.yqx.entity;

import java.util.Date;

/*
 *  培训管理类
 * */
public class Edu {
	
	private int id;//培训ID
	private String name;//培训名称
	private Date beginDate;//开始时间
	private Date endDate;//结束时间
	private String address;//培训地址
	private int type;//培训类型
	
	public Edu(int id, String name, Date beginDate, Date endDate, String address, int type) {
		super();
		this.id = id;
		this.name = name;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.address = address;
		this.type = type;
	}
	public Edu() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Edu [id=" + id + ", name=" + name + ", beginDate=" + beginDate + ", endDate=" + endDate + ", address="
				+ address + ", type=" + type + "]";
	}
	
	
}
