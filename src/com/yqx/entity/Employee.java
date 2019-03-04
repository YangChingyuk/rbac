package com.yqx.entity;

import java.sql.Timestamp;

/*
 * 员工类
 * 
 * */
public class Employee {
	
	private int id;//员工编号
	private String name;//员工姓名
	private int sex;//性别
	private int age;//年龄
	private int edu;//学历
	private int degree;//学位
	private String job;//职位
	private int deptId;//部门
	private int state;//状态
	private String phone;//电话
	private String address;//地址
	private Timestamp createTime;//创建时间
	
	public Employee(int id, String name, int sex, int age, int edu, int degree, String job, int deptId, int state,
			String phone, String address, Timestamp createTime) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.edu = edu;
		this.degree = degree;
		this.job = job;
		this.deptId = deptId;
		this.state = state;
		this.phone = phone;
		this.address = address;
		this.createTime = createTime;
	}

	public Employee() {
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getEdu() {
		return edu;
	}

	public void setEdu(int edu) {
		this.edu = edu;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", edu=" + edu + ", degree="
				+ degree + ", job=" + job + ", deptId=" + deptId + ", state=" + state + ", phone=" + phone
				+ ", address=" + address + ", createTime=" + createTime + "]";
	}
	
}
