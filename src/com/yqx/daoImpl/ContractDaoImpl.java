package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.ContractDao;
import com.yqx.entity.Contract;
import com.yqx.util.DBUtil;

public class ContractDaoImpl implements ContractDao{

	@Override
	public void add(Contract contract) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_contract(empId,code,beginDate,endDate,job,content,attachment) values(?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, contract.getEmpId());
			pst.setString(2, contract.getCode());
			pst.setDate(3, new Date(contract.getBeginDate().getTime()));
			pst.setDate(4, new Date(contract.getEndDate().getTime()));
			pst.setString(5, contract.getJob());
			pst.setString(6, contract.getContent());
			pst.setString(7, contract.getAttachment());
			System.out.println("成功新增" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Contract> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_contract(empId,code,beginDate,endDate,job,content,attachment) values(?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Contract contract = list.get(i);
				pst.setInt(1, contract.getEmpId());
				pst.setString(2, contract.getCode());
				pst.setDate(3, new Date(contract.getBeginDate().getTime()));
				pst.setDate(4, new Date(contract.getEndDate().getTime()));
				pst.setString(5, contract.getJob());
				pst.setString(6, contract.getContent());
				pst.setString(7, contract.getAttachment());

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
		String sql = "delete from hr_contract where id=?";
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
		String sql = "delete from hr_contract where id in (" + ids + ")";
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
	public void update(Contract contract) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_contract set empId=?,code=?,beginDate=?,endDate=?,job=?,content=?,attachment=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, contract.getEmpId());
			pst.setString(2, contract.getCode());
			pst.setDate(3, new Date(contract.getBeginDate().getTime()));
			pst.setDate(4, new Date(contract.getEndDate().getTime()));
			pst.setString(5, contract.getJob());
			pst.setString(6, contract.getContent());
			pst.setString(7, contract.getAttachment());
			pst.setInt(8, contract.getId());
			System.out.println("成功修改" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public Contract queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_contract where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			Contract a = new Contract();
			if (rs.next()) {
				a.setId(rs.getInt("id"));
				a.setEmpId(rs.getInt("empId"));
				a.setCode(rs.getString("code"));
				a.setBeginDate(rs.getDate("beginDate"));
				a.setEndDate(rs.getDate("endDate"));
				a.setJob(rs.getString("job"));
				a.setContent(rs.getString("content"));
				a.setAttachment(rs.getString("attachment"));
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return null;
	}

	@Override
	public List<Contract> queryAll() {
		List<Contract> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_contract order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Contract a = new Contract();
				a.setId(rs.getInt("id"));
				a.setEmpId(rs.getInt("empId"));
				a.setCode(rs.getString("code"));
				a.setBeginDate(rs.getDate("beginDate"));
				a.setEndDate(rs.getDate("endDate"));
				a.setJob(rs.getString("job"));
				a.setContent(rs.getString("content"));
				a.setAttachment(rs.getString("attachment"));
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
	public List<Contract> queryByPage(int currentPage, int pageSize) {
		List<Contract> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_contract order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Contract a = new Contract();
				a.setId(rs.getInt("id"));
				a.setEmpId(rs.getInt("empId"));
				a.setCode(rs.getString("code"));
				a.setBeginDate(rs.getDate("beginDate"));
				a.setEndDate(rs.getDate("endDate"));
				a.setJob(rs.getString("job"));
				a.setContent(rs.getString("content"));
				a.setAttachment(rs.getString("attachment"));
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
	public List<Contract> queryByPage(int currentPage, int pageSize, String condition) {
		List<Contract> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_contract " + condition + " order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Contract a = new Contract();
				a.setId(rs.getInt("id"));
				a.setEmpId(rs.getInt("empId"));
				a.setCode(rs.getString("code"));
				a.setBeginDate(rs.getDate("beginDate"));
				a.setEndDate(rs.getDate("endDate"));
				a.setJob(rs.getString("job"));
				a.setContent(rs.getString("content"));
				a.setAttachment(rs.getString("attachment"));
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
		String sql = "select count(*) from hr_contract";
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
		String sql = "select count(*) from hr_contract " + condition;
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
