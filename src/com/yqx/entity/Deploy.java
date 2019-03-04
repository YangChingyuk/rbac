package com.yqx.entity;

import java.util.Date;

/*
 *调度类 
 * */
public class Deploy {
	
	private int id;//ID
	private int empId;//员工编号
	private String olddept;//原部门
	private String newdept;//新部门
	private String oldjob;//原职位
	private String newjob;//新职位
	private Date mixDate;//时间
	
	public Deploy(int id, int empId, String olddept, String newdept, String oldjob, String newjob, Date mixDate) {
		super();
		this.id = id;
		this.empId = empId;
		this.olddept = olddept;
		this.newdept = newdept;
		this.oldjob = oldjob;
		this.newjob = newjob;
		this.mixDate = mixDate;
	}

	public Deploy() {
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

	public String getOlddept() {
		return olddept;
	}

	public void setOlddept(String olddept) {
		this.olddept = olddept;
	}

	public String getNewdept() {
		return newdept;
	}

	public void setNewdept(String newdept) {
		this.newdept = newdept;
	}

	public String getOldjob() {
		return oldjob;
	}

	public void setOldjob(String oldjob) {
		this.oldjob = oldjob;
	}

	public String getNewjob() {
		return newjob;
	}

	public void setNewjob(String newjob) {
		this.newjob = newjob;
	}

	public Date getMixDate() {
		return mixDate;
	}

	public void setMixDate(Date mixDate) {
		this.mixDate = mixDate;
	}

	@Override
	public String toString() {
		return "Deploy [id=" + id + ", empId=" + empId + ", olddept=" + olddept + ", newdept=" + newdept + ", oldjob="
				+ oldjob + ", newjob=" + newjob + ", mixDate=" + mixDate + "]";
	}
	
	
}
