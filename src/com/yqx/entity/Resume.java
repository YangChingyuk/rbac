package com.yqx.entity;

import java.util.Date;

/*
 * 履历类
 *
 * */
public class Resume {
	
	private int id;//履历编号
	private int empId;//员工编号
	private String orgname;//部门
	private String job;//职位
	private String edu;//学历
	private String content;//工作内容
	private String result;//业绩
	private Date beginDate;//开始时间
	private Date endDate;//结束时间
	
	public Resume(int id, int empId, String orgname, String job, String edu, String content, String result,
			Date beginDate, Date endDate) {
		super();
		this.id = id;
		this.empId = empId;
		this.orgname = orgname;
		this.job = job;
		this.edu = edu;
		this.content = content;
		this.result = result;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}
	public Resume() {
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
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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
	@Override
	public String toString() {
		return "Resume [id=" + id + ", empId=" + empId + ", orgname=" + orgname + ", job=" + job + ", edu=" + edu
				+ ", content=" + content + ", result=" + result + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ "]";
	}
	
}
