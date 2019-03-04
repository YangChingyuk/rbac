package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.DeployDao;
import com.yqx.entity.Deploy;
import com.yqx.util.DBUtil;

public class DeployDaoImpl implements DeployDao{

	@Override
	public void add(Deploy deploy) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_deploy(empId,olddept,newdept,oldjob,newjob,mixDate) values(?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, deploy.getEmpId());
			pst.setString(2, deploy.getOlddept());
			pst.setString(3, deploy.getNewdept());
			pst.setString(4, deploy.getOldjob());
			pst.setString(5, deploy.getNewjob());
			pst.setDate(6, new Date(deploy.getMixDate().getTime()));

			System.out.println("成功新增" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Deploy> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_deploy(empId,olddept,newdept,oldjob,newjob,mixDate) values(?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Deploy deploy = list.get(i);
				pst.setInt(1, deploy.getEmpId());
				pst.setString(2, deploy.getOlddept());
				pst.setString(3, deploy.getNewdept());
				pst.setString(4, deploy.getOldjob());
				pst.setString(5, deploy.getNewjob());
				pst.setDate(6, new Date(deploy.getMixDate().getTime()));

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
		String sql = "delete from hr_deploy where id=?";
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
		String sql = "delete from hr_deploy where id in (" + ids + ")";
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
	public void update(Deploy deploy) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_deploy set empId=?,olddept=?,newdept=?,oldjob=?,newjob=?,mixDate=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, deploy.getEmpId());
			pst.setString(2, deploy.getOlddept());
			pst.setString(3, deploy.getNewdept());
			pst.setString(4, deploy.getOldjob());
			pst.setString(5, deploy.getNewjob());
			pst.setDate(6, new Date(deploy.getMixDate().getTime()));
			pst.setInt(7, deploy.getId());
			System.out.println("成功修改" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public Deploy queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_deploy where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				Deploy r = new Deploy();
				r.setId(rs.getInt("id"));
				r.setEmpId(rs.getInt("empId"));
				r.setOlddept(rs.getString("olddept"));
				r.setNewdept(rs.getString("newdept"));
				r.setOldjob(rs.getString("oldjob"));
				r.setNewjob(rs.getString("newjob"));
				r.setMixDate(rs.getDate("mixDate"));
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
	public List<Deploy> queryAll() {
		List<Deploy> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_deploy order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Deploy r = new Deploy();
				r.setId(rs.getInt("id"));
				r.setEmpId(rs.getInt("empId"));
				r.setOlddept(rs.getString("olddept"));
				r.setNewdept(rs.getString("newdept"));
				r.setOldjob(rs.getString("oldjob"));
				r.setNewjob(rs.getString("newjob"));
				r.setMixDate(rs.getDate("mixDate"));
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
	public List<Deploy> queryByPage(int currentPage, int pageSize) {
		List<Deploy> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_deploy order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Deploy r = new Deploy();
				r.setId(rs.getInt("id"));
				r.setEmpId(rs.getInt("empId"));
				r.setOlddept(rs.getString("olddept"));
				r.setNewdept(rs.getString("newdept"));
				r.setOldjob(rs.getString("oldjob"));
				r.setNewjob(rs.getString("newjob"));
				r.setMixDate(rs.getDate("mixDate"));
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
	public List<Deploy> queryByPage(int currentPage, int pageSize, String condition) {
		List<Deploy> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_deploy " + condition + " order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Deploy a = new Deploy();
				a.setId(rs.getInt("id"));
				a.setEmpId(rs.getInt("empId"));
				a.setOlddept(rs.getString("olddept"));
				a.setNewdept(rs.getString("newdept"));
				a.setOldjob(rs.getString("oldjob"));
				a.setNewjob(rs.getString("newjob"));
				a.setMixDate(rs.getDate("mixDate"));
				list.add(a);
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
		String sql = "select count(*) from hr_deploy";
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
		String sql = "select count(*) from hr_deploy " + condition;
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
