package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.RewardsDao;
import com.yqx.entity.Rewards;
import com.yqx.util.DBUtil;

public class RewardsDaoImpl implements RewardsDao{

	@Override
	public void add(Rewards rewards) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_rewards(empId,title,content,type,creatDate) values(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, rewards.getEmpId());
			pst.setString(2, rewards.getTitle());
			pst.setString(3, rewards.getContent());
			pst.setInt(4, rewards.getType());
			pst.setDate(5, new Date(rewards.getCreatDate().getTime()));
			System.out.println("成功新增" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Rewards> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_rewards(empId,title,content,type,creatDate) values(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Rewards rewards = list.get(i);
				pst.setInt(1, rewards.getEmpId());
				pst.setString(2, rewards.getTitle());
				pst.setString(3, rewards.getContent());
				pst.setInt(4, rewards.getType());
				pst.setDate(5, new Date(rewards.getCreatDate().getTime()));

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
		String sql = "delete from hr_rewards where id=?";
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
		String sql = "delete from hr_rewards where id in (" + ids + ")";
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
	public void update(Rewards rewards) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_rewards set empId=?,title=?,content=?,type=?,creatDate=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, rewards.getEmpId());
			pst.setString(2, rewards.getTitle());
			pst.setString(3, rewards.getContent());
			pst.setInt(4, rewards.getType());
			pst.setDate(5, new Date(rewards.getCreatDate().getTime()));
			pst.setInt(6, rewards.getId());
			System.out.println("成功修改" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public Rewards queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_rewards where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				Rewards r = new Rewards();
				r.setId(rs.getInt("id"));
				r.setEmpId(rs.getInt("empId"));
				r.setTitle(rs.getString("title"));
				r.setContent(rs.getString("content"));
				r.setType(rs.getInt("type"));
				r.setCreatDate(rs.getDate("CreatDate"));
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
	public List<Rewards> queryAll() {
		List<Rewards> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_rewards order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Rewards a = new Rewards();
				a.setId(rs.getInt("id"));
				a.setEmpId(rs.getInt("empId"));
				a.setTitle(rs.getString("title"));
				a.setContent(rs.getString("content"));
				a.setType(rs.getInt("type"));
				a.setCreatDate(rs.getDate("CreatDate"));
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
	public List<Rewards> queryByPage(int currentPage, int pageSize) {
		List<Rewards> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_rewards order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Rewards a = new Rewards();
				a.setId(rs.getInt("id"));
				a.setEmpId(rs.getInt("empId"));
				a.setTitle(rs.getString("title"));
				a.setContent(rs.getString("content"));
				a.setType(rs.getInt("type"));
				a.setCreatDate(rs.getDate("CreatDate"));
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
	public List<Rewards> queryByPage(int currentPage, int pageSize, String condition) {
		List<Rewards> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_rewards " + condition + " order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Rewards a = new Rewards();
				a.setId(rs.getInt("id"));
				a.setEmpId(rs.getInt("empId"));
				a.setTitle(rs.getString("title"));
				a.setContent(rs.getString("content"));
				a.setType(rs.getInt("type"));
				a.setCreatDate(rs.getDate("CreatDate"));
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
		String sql = "select count(*) from hr_rewards";
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
		String sql = "select count(*) from hr_rewards " + condition;
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
