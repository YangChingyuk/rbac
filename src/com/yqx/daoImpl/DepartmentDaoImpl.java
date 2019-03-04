package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.DepartmentDao;
import com.yqx.entity.Department;
import com.yqx.util.DBUtil;

public class DepartmentDaoImpl implements DepartmentDao{

	@Override
	public void add(Department department) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_department(name,manager,officeNo,phone,remark) values(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, department.getName());
			pst.setString(2, department.getManager());
			pst.setString(3, department.getOfficeNo());
			pst.setString(4, department.getPhone());
			pst.setString(5, department.getRemark());
			
			System.out.println("成功新增"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<Department> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_department(name,manager,officeNo,phone,remark) values(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				Department department = list.get(i);
				pst.setString(1, department.getName());
				pst.setString(2, department.getManager());
				pst.setString(3, department.getOfficeNo());
				pst.setString(4, department.getPhone());
				pst.setString(5, department.getRemark());
				
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
		String sql = "delete from hr_department where id=?";
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
		String sql = "delete from hr_department where id in ("+ids+")";
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
	public void update(Department department) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_department set name=?,manager=?,officeNo=?,phone=?,remark=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, department.getName());
			pst.setString(2, department.getManager());
			pst.setString(3, department.getOfficeNo());
			pst.setString(4, department.getPhone());
			pst.setString(5, department.getRemark());
			pst.setInt(6, department.getId());
			System.out.println("成功修改"+pst.executeUpdate()+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public Department queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_department where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				Department d = new Department();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setManager(rs.getString("manager"));
				d.setOfficeNo(rs.getString("officeNo"));
				d.setPhone(rs.getString("phone"));
				d.setRemark(rs.getString("remark"));
				return d;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return null;
	}

	@Override
	public List<Department> queryAll() {
		List<Department> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_department order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Department d = new Department();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setManager(rs.getString("manager"));
				d.setOfficeNo(rs.getString("officeNo"));
				d.setPhone(rs.getString("phone"));
				d.setRemark(rs.getString("remark"));
				list.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Department> queryByPage(int currentPage, int pageSize) {
		List<Department> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_department order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Department d = new Department();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setManager(rs.getString("manager"));
				d.setOfficeNo(rs.getString("officeNo"));
				d.setPhone(rs.getString("phone"));
				d.setRemark(rs.getString("remark"));
				list.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Department> queryByPage(int currentPage, int pageSize, String condition) {
		List<Department> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_department "+condition+" order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Department d = new Department();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setManager(rs.getString("manager"));
				d.setOfficeNo(rs.getString("officeNo"));
				d.setPhone(rs.getString("phone"));
				d.setRemark(rs.getString("remark"));
				list.add(d);
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
		String sql = "select count(*) from hr_department";
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
		String sql = "select count(*) from hr_department "+condition;
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
