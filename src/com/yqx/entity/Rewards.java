package com.yqx.entity;

import java.util.Date;

/*
 * 
 * 奖惩类
 * */
public class Rewards {
	
	private int id;//奖励编号
	private int empId;//员工编号
	private String title;//奖惩标题
	private String content;//奖惩内容
	private int type;//奖金类别
	private Date creatDate;//奖惩时间
	
	public Rewards(int id, int empId, String title, String content, int type, Date creatDate) {
		super();
		this.id = id;
		this.empId = empId;
		this.title = title;
		this.content = content;
		this.type = type;
		this.creatDate = creatDate;
	}

	public Rewards() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	@Override
	public String toString() {
		return "Rewards [id=" + id + ", empId=" + empId + ", title=" + title + ", content=" + content + ", type=" + type
				+ ", creatDate=" + creatDate + "]";
	}
	
}
