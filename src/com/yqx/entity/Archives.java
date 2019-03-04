package com.yqx.entity;

import java.sql.Timestamp;

/*
 * 档案类
 * */
public class Archives {
	
	private int id; //档案id
	private int empId;//员工编号
	private String code;//档案编号
	private String name;//档案名称
	private String content;//档案内容
	private int type;//档案类型
	private String remark;//备注说明
	private Timestamp createTime;//创建时间
	
	public Archives(int id, int empId, String code, String name, String content, int type, String remark,
			Timestamp createTime) {
		super();
		this.id = id;
		this.empId = empId;
		this.code = code;
		this.name = name;
		this.content = content;
		this.type = type;
		this.remark = remark;
		this.createTime = createTime;
	}
	public Archives() {
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Archives [id=" + id + ", empId=" + empId + ", code=" + code + ", name=" + name + ", content=" + content
				+ ", type=" + type + ", remark=" + remark + ", createTime=" + createTime + "]";
	}
	
}
