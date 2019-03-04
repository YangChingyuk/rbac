package com.yqx.entity;

/*
 * 菜单类
 * */
public class Menu {
	
	private int id;//菜单ID
	private String code;//菜单编号
	private String pcode;//
	private String name;//名称
	private String url;//
	private int state;//状态
	private String remark;//备注说明
	
	public Menu(int id, String code, String pcode, String name, String url, int state, String remark) {
		super();
		this.id = id;
		this.code = code;
		this.pcode = pcode;
		this.name = name;
		this.url = url;
		this.state = state;
		this.remark = remark;
	}

	public Menu() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", code=" + code + ", pcode=" + pcode + ", name=" + name + ", url=" + url + ", state="
				+ state + ", remark=" + remark + "]";
	}
	
}
