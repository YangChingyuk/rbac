package com.yqx.entity;

/*
 * 合同类
 * */
import java.util.Date;

public class Contract {
	
	private int id;//合同id
	private int empId;//员工编号
	private String code;//合同编号
	private Date beginDate;//开始时间
	private Date endDate;//结束时间
	private String job;//职位
	private String content;//内容
	private String attachment;//附件文档
	
	public Contract(int id, int empId, String code, Date beginDate, Date endDate, String job, String content,
			String attachment) {
		super();
		this.id = id;
		this.empId = empId;
		this.code = code;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.job = job;
		this.content = content;
		this.attachment = attachment;
	}
	public Contract() {
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
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	@Override
	public String toString() {
		return "Contract [id=" + id + ", empId=" + empId + ", code=" + code + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", job=" + job + ", content=" + content + ", attachment=" + attachment + "]";
	}
	
}
