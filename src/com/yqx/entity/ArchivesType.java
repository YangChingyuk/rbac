package com.yqx.entity;

/*
 * 
 * 档案类型类
 * */
public class ArchivesType {
	private int id;//档案类型id
	private String name;//档案类型
	private String remark;//备注说明
	
	public ArchivesType(int id, String name, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
	}
	public ArchivesType() {
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "ArchivesType [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}
	
}
