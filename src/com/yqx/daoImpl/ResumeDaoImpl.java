package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.ResumeDao;
import com.yqx.entity.Resume;
import com.yqx.util.DBUtil;

public class ResumeDaoImpl implements ResumeDao{

	@Override
	public void add(Resume resume) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_resume(empId,orgname,job,edu,content,result,beginDate,endDate) values(?,?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, resume.getEmpId());
			pst.setString(2, resume.getOrgname());
			pst.setString(3, resume.getJob());
			pst.setString(4, resume.getEdu());
			pst.setString(5, resume.getContent());
			pst.setString(6, resume.getResult());
			pst.setDate(7, new Date(resume.getBeginDate().getTime()));
			pst.setDate(8, new Date(resume.getEndDate().getTime()));
			System.out.println("成功新增"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Resume> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_resume(empId,orgname,job,edu,content,result,beginDate,endDate) values(?,?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				Resume resume = list.get(i);
				pst.setInt(1, resume.getEmpId());
				pst.setString(2, resume.getOrgname());
				pst.setString(3, resume.getJob());
				pst.setString(4, resume.getEdu());
				pst.setString(5, resume.getContent());
				pst.setString(6, resume.getResult());
				pst.setDate(7, new Date(resume.getBeginDate().getTime()));
				pst.setDate(8, new Date(resume.getEndDate().getTime()));
				
				pst.addBatch();
				if(i%300==0) {
					pst.executeBatch();
					pst.clearBatch();
				}
			}
			pst.executeBatch();
			pst.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void deleteById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from hr_resume where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("成功删除"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void deleteMore(String ids) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from hr_resume where id in ("+ids+")";
		try {
			pst = con.prepareStatement(sql);
			System.out.println("成功删除"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void update(Resume resume) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_resume set empId=?,orgname=?,job=?,edu=?,content=?,result=?,beginDate=?,endDate=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, resume.getEmpId());
			pst.setString(2, resume.getOrgname());
			pst.setString(3, resume.getJob());
			pst.setString(4, resume.getEdu());
			pst.setString(5, resume.getContent());
			pst.setString(6, resume.getResult());
			pst.setDate(7, new Date(resume.getBeginDate().getTime()));
			pst.setDate(8, new Date(resume.getEndDate().getTime()));
			pst.setInt(9, resume.getId());
			System.out.println("成功修改"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public Resume queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_resume where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				Resume r = new Resume();
				r.setId(rs.getInt("id"));
				r.setEmpId(rs.getInt("empId"));
				r.setOrgname(rs.getString("orgname"));
				r.setJob(rs.getString("job"));
				r.setEdu(rs.getString("edu"));
				r.setContent(rs.getString("content"));
				r.setResult(rs.getString("result"));
				r.setBeginDate(rs.getDate("beginDate"));
				r.setEndDate(rs.getDate("endDate"));
				return r;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return null;
	}

	@Override
	public List<Resume> queryAll() {
		List<Resume> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_resume order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Resume r = new Resume();
				r.setId(rs.getInt("id"));
				r.setEmpId(rs.getInt("empId"));
				r.setOrgname(rs.getString("orgname"));
				r.setJob(rs.getString("job"));
				r.setEdu(rs.getString("edu"));
				r.setContent(rs.getString("content"));
				r.setResult(rs.getString("result"));
				r.setBeginDate(rs.getDate("beginDate"));
				r.setEndDate(rs.getDate("endDate"));
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Resume> queryByPage(int currentPage, int pageSize) {
		List<Resume> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_resume order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Resume r = new Resume();
				r.setId(rs.getInt("id"));
				r.setEmpId(rs.getInt("empId"));
				r.setOrgname(rs.getString("orgname"));
				r.setJob(rs.getString("job"));
				r.setEdu(rs.getString("edu"));
				r.setContent(rs.getString("content"));
				r.setResult(rs.getString("result"));
				r.setBeginDate(rs.getDate("beginDate"));
				r.setEndDate(rs.getDate("endDate"));
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Resume> queryByPage(int currentPage, int pageSize, String condition) {
		List<Resume> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_resume "+condition+" order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Resume r = new Resume();
				r.setId(rs.getInt("id"));
				r.setEmpId(rs.getInt("empId"));
				r.setOrgname(rs.getString("orgname"));
				r.setJob(rs.getString("job"));
				r.setEdu(rs.getString("edu"));
				r.setContent(rs.getString("content"));
				r.setResult(rs.getString("result"));
				r.setBeginDate(rs.getDate("beginDate"));
				r.setEndDate(rs.getDate("endDate"));
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public int getTotals() {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select count(*) from hr_resume";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return 0;
	}

	@Override
	public int getTotals(String condition) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select count(*) from hr_resume "+condition;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return 0;
	}

}
