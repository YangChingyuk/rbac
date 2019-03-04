package com.yqx.entity;

/*
 * 部门类
 * 
 * */
public class Department {

	private int id; // 主键编号
	private String name;// 部门名称
	private String manager;// 部门负责人
	private String officeNo;// 部门办公室编号
	private String phone;// 部门电话
	private String remark;// 备注说明

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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Department() {
		super();
	}

	public Department(int id, String name, String manager, String officeNo, String phone, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.manager = manager;
		this.officeNo = officeNo;
		this.phone = phone;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", manager=" + manager + ", officeNo=" + officeNo
				+ ", phone=" + phone + ", remark=" + remark + "]";
	}

}
