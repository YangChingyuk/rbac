package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.EduDao;
import com.yqx.entity.Edu;
import com.yqx.util.DBUtil;

public class EduDaoImpl implements EduDao{

	@Override
	public void add(Edu edu) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_edu(name,beginDate,endDate,address,type) values(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, edu.getName());
			pst.setDate(2, new Date(edu.getBeginDate().getTime()));
			pst.setDate(3, new Date(edu.getEndDate().getTime()));
			pst.setString(4, edu.getAddress());
			pst.setInt(5, edu.getType());
			
			System.out.println("成功新增" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Edu> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_edu(name,beginDate,endDate,address,type) values(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Edu edu = list.get(i);
				pst.setString(1, edu.getName());
				pst.setDate(2, new Date(edu.getBeginDate().getTime()));
				pst.setDate(3, new Date(edu.getEndDate().getTime()));
				pst.setString(4, edu.getAddress());
				pst.setInt(5, edu.getType());

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
		String sql = "delete from hr_edu where id=?";
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
		String sql = "delete from hr_edu where id in (" + ids + ")";
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
	public void update(Edu edu) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_edu set name=?,beginDate=?,endDate=?,address=?,type=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, edu.getName());
			pst.setDate(2, new Date(edu.getBeginDate().getTime()));
			pst.setDate(3, new Date(edu.getEndDate().getTime()));
			pst.setString(4, edu.getAddress());
			pst.setInt(5, edu.getType());
			pst.setInt(6, edu.getId());
			System.out.println("成功修改" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public Edu queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_edu where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				Edu r = new Edu();
				r.setId(rs.getInt("id"));
				r.setName(rs.getString("name"));
				r.setBeginDate(rs.getDate("beginDate"));
				r.setEndDate(rs.getDate("endDate"));
				r.setAddress(rs.getString("address"));
				r.setType(rs.getInt("type"));
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
	public List<Edu> queryAll() {
		List<Edu> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_edu order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Edu r = new Edu();
				r.setId(rs.getInt("id"));
				r.setName(rs.getString("name"));
				r.setBeginDate(rs.getDate("beginDate"));
				r.setEndDate(rs.getDate("endDate"));
				r.setAddress(rs.getString("address"));
				r.setType(rs.getInt("type"));
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
	public List<Edu> queryByPage(int currentPage, int pageSize) {
		List<Edu> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_edu order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Edu r = new Edu();
				r.setId(rs.getInt("id"));
				r.setName(rs.getString("name"));
				r.setBeginDate(rs.getDate("beginDate"));
				r.setEndDate(rs.getDate("endDate"));
				r.setAddress(rs.getString("address"));
				r.setType(rs.getInt("type"));
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
	public List<Edu> queryByPage(int currentPage, int pageSize, String condition) {
		List<Edu> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_edu " + condition + " order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Edu r = new Edu();
				r.setId(rs.getInt("id"));
				r.setName(rs.getString("name"));
				r.setBeginDate(rs.getDate("beginDate"));
				r.setEndDate(rs.getDate("endDate"));
				r.setAddress(rs.getString("address"));
				r.setType(rs.getInt("type"));
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
		String sql = "select count(*) from hr_edu";
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
		String sql = "select count(*) from hr_edu " + condition;
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
