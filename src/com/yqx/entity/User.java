package com.yqx.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/*
 * 用户类
 * */
public class User {
	
	private int id;//用户ID
	private String username;//帐号
	private String password;//密码
	private int empId;//员工编号
	private int state;//状态
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp createTime;//创建时间
	
	public User(int id, String username, String password, int empId, int state, Timestamp createTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.empId = empId;
		this.state = state;
		this.createTime = createTime;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", empId=" + empId + ", state="
				+ state + ", createTime=" + createTime + "]";
	}
	
}
