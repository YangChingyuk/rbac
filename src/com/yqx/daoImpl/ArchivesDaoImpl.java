package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.ArchivesDao;
import com.yqx.entity.Archives;
import com.yqx.util.DBUtil;

public class ArchivesDaoImpl implements ArchivesDao{

	@Override
	public void add(Archives archives) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_archives(empId,code,name,content,type,remark,createTime) values(?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, archives.getEmpId());
			pst.setString(2, archives.getCode());
			pst.setString(3, archives.getName());
			pst.setString(4, archives.getContent());
			pst.setInt(5, archives.getType());
			pst.setString(6, archives.getRemark());
			pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			System.out.println("成功新增"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Archives> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_archives(empId,code,name,content,type,remark,createTime) values(?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				Archives archives = list.get(i);
				pst.setInt(1, archives.getEmpId());
				pst.setString(2, archives.getCode());
				pst.setString(3, archives.getName());
				pst.setString(4, archives.getContent());
				pst.setInt(5, archives.getType());
				pst.setString(6, archives.getRemark());
				pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
				
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
		String sql = "delete from hr_archives where id=?";
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
		String sql = "delete from hr_archives where id in ("+ids+")";
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
	public void update(Archives archives) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_archives set empId=?,code=?,name=?,content=?,type=?,remark=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, archives.getEmpId());
			pst.setString(2, archives.getCode());
			pst.setString(3, archives.getName());
			pst.setString(4, archives.getContent());
			pst.setInt(5, archives.getType());
			pst.setString(6, archives.getRemark());
			pst.setInt(7, archives.getId());
			System.out.println("成功修改"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public Archives queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_archives where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				Archives a = new Archives();
				a.setId(rs.getInt("id"));
				a.setEmpId(rs.getInt("empId"));
				a.setCode(rs.getString("code"));
				a.setName(rs.getString("name"));
				a.setContent(rs.getString("content"));
				a.setType(rs.getInt("type"));
				a.setRemark(rs.getString("remark"));
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return null;
	}

	@Override
	public List<Archives> queryAll() {
		List<Archives> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_archives order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Archives a = new Archives();
				a.setId(rs.getInt("id"));
				a.setEmpId(rs.getInt("empId"));
				a.setCode(rs.getString("code"));
				a.setName(rs.getString("name"));
				a.setContent(rs.getString("content"));
				a.setType(rs.getInt("type"));
				a.setRemark(rs.getString("remark"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Archives> queryByPage(int currentPage, int pageSize) {
		List<Archives> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_archives order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Archives a = new Archives();
				a.setId(rs.getInt("id"));
				a.setEmpId(rs.getInt("empId"));
				a.setCode(rs.getString("code"));
				a.setName(rs.getString("name"));
				a.setContent(rs.getString("content"));
				a.setType(rs.getInt("type"));
				a.setRemark(rs.getString("remark"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Archives> queryByPage(int currentPage, int pageSize, String condition) {
		List<Archives> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_archives "+condition+" order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Archives a = new Archives();
				a.setId(rs.getInt("id"));
				a.setEmpId(rs.getInt("empId"));
				a.setCode(rs.getString("code"));
				a.setName(rs.getString("name"));
				a.setContent(rs.getString("content"));
				a.setType(rs.getInt("type"));
				a.setRemark(rs.getString("remark"));
				list.add(a);
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
		String sql = "select count(*) from hr_archives";
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
		String sql = "select count(*) from hr_archives "+condition;
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
