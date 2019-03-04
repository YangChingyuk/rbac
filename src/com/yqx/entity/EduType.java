package com.yqx.entity;

/*
 * 
 * ÅàÑµÀà±ğÀà
 * */
public class EduType {
	
	private int id;//ÅàÑµID
	private String name;//ÅàÑµÃû³Æ
	
	public EduType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public EduType() {
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
	@Override
	public String toString() {
		return "EduType [id=" + id + ", name=" + name + "]";
	}
	
	
}
