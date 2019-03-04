package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.CertificateDao;
import com.yqx.entity.Certificate;
import com.yqx.util.DBUtil;

public class CertificateDaoImpl implements CertificateDao{

	@Override
	public void add(Certificate certificate) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_certificate(empId,eduid,name,code,getDate) values(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, certificate.getEmpId());
			pst.setInt(2, certificate.getEduid());
			pst.setString(3, certificate.getName());
			pst.setString(4, certificate.getCode());
			pst.setDate(5, new Date(certificate.getGetDate().getTime()));

			System.out.println("成功新增" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Certificate> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_certificate(empId,eduid,name,code,getDate) values(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Certificate certificate = list.get(i);
				pst.setInt(1, certificate.getEmpId());
				pst.setInt(2, certificate.getEduid());
				pst.setString(3, certificate.getName());
				pst.setString(4, certificate.getCode());
				pst.setDate(5, new Date(certificate.getGetDate().getTime()));

				pst.addBatch();
				if (i % 300 == 0) {
					pst.executeBatch();
					pst.clearBatch();
				}
			}
			pst.executeBatch();
			pst.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void deleteById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from hr_certificate where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("成功删除" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void deleteMore(String ids) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from hr_certificate where id in (" + ids + ")";
		try {
			pst = con.prepareStatement(sql);
			System.out.println("成功删除" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void update(Certificate certificate) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_certificate set empId=?,eduid=?,name=?,code=?,getDate=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, certificate.getEmpId());
			pst.setInt(2, certificate.getEduid());
			pst.setString(3, certificate.getName());
			pst.setString(4, certificate.getCode());
			pst.setDate(5, new Date(certificate.getGetDate().getTime()));
			pst.setInt(6, certificate.getId());
			System.out.println("成功修改" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public Certificate queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_certificate where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				Certificate r = new Certificate();
				r.setId(rs.getInt("id"));
				r.setEmpId(rs.getInt("empId"));
				r.setEduid(rs.getInt("eduid"));
				r.setName(rs.getString("name"));
				r.setCode(rs.getString("code"));
				r.setGetDate(rs.getDate("getDate"));
				return r;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return null;
	}

	@Override
	public List<Certificate> queryAll() {
		List<Certificate> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_certificate order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Certificate r = new Certificate();
				r.setId(rs.getInt("id"));
				r.setEmpId(rs.getInt("empId"));
				r.setEduid(rs.getInt("eduid"));
				r.setName(rs.getString("name"));
				r.setCode(rs.getString("code"));
				r.setGetDate(rs.getDate("getDate"));
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Certificate> queryByPage(int currentPage, int pageSize) {
		List<Certificate> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_certificate order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Certificate r = new Certificate();
				r.setId(rs.getInt("id"));
				r.setEmpId(rs.getInt("empId"));
				r.setEduid(rs.getInt("eduid"));
				r.setName(rs.getString("name"));
				r.setCode(rs.getString("code"));
				r.setGetDate(rs.getDate("getDate"));
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Certificate> queryByPage(int currentPage, int pageSize, String condition) {
		List<Certificate> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_certificate " + condition + " order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Certificate r = new Certificate();
				r.setId(rs.getInt("id"));
				r.setEmpId(rs.getInt("empId"));
				r.setEduid(rs.getInt("eduid"));
				r.setName(rs.getString("name"));
				r.setCode(rs.getString("code"));
				r.setGetDate(rs.getDate("getDate"));
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public int getTotals() {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select count(*) from hr_certificate";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return 0;
	}

	@Override
	public int getTotals(String condition) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select count(*) from hr_certificate " + condition;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return 0;
	}

}
