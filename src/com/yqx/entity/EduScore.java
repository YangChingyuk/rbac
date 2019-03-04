package com.yqx.entity;

/*
 * ≈‡—µ≥…º®¿‡
 * */
public class EduScore {
	
	private int id;//≈‡—µ≥…º®ID
	private int empId;//‘±π§±‡∫≈
	private int eduid;//≈‡—µ±‡∫≈
	private int score;//≈‡—µ≥…º®
	
	public EduScore(int id, int empId, int eduid, int score) {
		super();
		this.id = id;
		this.empId = empId;
		this.eduid = eduid;
		this.score = score;
	}
	
	public EduScore() {
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
	public int getEduid() {
		return eduid;
	}
	public void setEduid(int eduid) {
		this.eduid = eduid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "EduScore [id=" + id + ", empId=" + empId + ", eduid=" + eduid + ", score=" + score + "]";
	}
	
}
