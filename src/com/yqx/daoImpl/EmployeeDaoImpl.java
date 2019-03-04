package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.EmployeeDao;
import com.yqx.entity.Employee;
import com.yqx.util.DBUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public void add(Employee employee) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_employee(name,sex,age,edu,degree,job,deptId,state,phone,address,createTime) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, employee.getName());
			pst.setInt(2, employee.getSex());
			pst.setInt(3, employee.getAge());
			pst.setInt(4, employee.getEdu());
			pst.setInt(5, employee.getDegree());
			pst.setString(6, employee.getJob());
			pst.setInt(7, employee.getDeptId());
			pst.setInt(8, employee.getState());
			pst.setString(9, employee.getPhone());
			pst.setString(10, employee.getAddress());
			pst.setTimestamp(11, new Timestamp(System.currentTimeMillis()));

			System.out.println("成功新增" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}

	}

	@Override
	public void addMore(List<Employee> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_employee(name,sex,age,edu,degree,job,deptId,state,phone,address,createTime) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Employee employee = list.get(i);
				pst.setString(1, employee.getName());
				pst.setInt(2, employee.getSex());
				pst.setInt(3, employee.getAge());
				pst.setInt(4, employee.getEdu());
				pst.setInt(5, employee.getDegree());
				pst.setString(6, employee.getJob());
				pst.setInt(7, employee.getDeptId());
				pst.setInt(8, employee.getState());
				pst.setString(9, employee.getPhone());
				pst.setString(10, employee.getAddress());
				pst.setTimestamp(11, new Timestamp(System.currentTimeMillis()));

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
		String sql = "delete from hr_employee where id=?";
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
		String sql = "delete from hr_employee where id in (" + ids + ")";
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
	public void update(Employee employee) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_employee set name=?,sex=?,age=?,edu=?,degree=?,job=?,deptId=?,state=?,phone=?,address=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, employee.getName());
			pst.setInt(2, employee.getSex());
			pst.setInt(3, employee.getAge());
			pst.setInt(4, employee.getEdu());
			pst.setInt(5, employee.getDegree());
			pst.setString(6, employee.getJob());
			pst.setInt(7, employee.getDeptId());
			pst.setInt(8, employee.getState());
			pst.setString(9, employee.getPhone());
			pst.setString(10, employee.getAddress());
			pst.setInt(11, employee.getId());
			System.out.println("成功修改" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}

	}

	@Override
	public Employee queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_employee where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSex(rs.getInt("sex"));
				e.setAge(rs.getInt("age"));
				e.setEdu(rs.getInt("edu"));
				e.setDegree(rs.getInt("degree"));
				e.setJob(rs.getString("job"));
				e.setDeptId(rs.getInt("deptId"));
				e.setState(rs.getInt("state"));
				e.setPhone(rs.getString("phone"));
				e.setAddress(rs.getString("address"));
				e.setCreateTime(rs.getTimestamp("createTime"));
				return e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return null;
	}

	@Override
	public List<Employee> queryAll() {
		List<Employee> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_employee order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSex(rs.getInt("sex"));
				e.setAge(rs.getInt("age"));
				e.setEdu(rs.getInt("edu"));
				e.setDegree(rs.getInt("degree"));
				e.setJob(rs.getString("job"));
				e.setDeptId(rs.getInt("deptId"));
				e.setState(rs.getInt("state"));
				e.setPhone(rs.getString("phone"));
				e.setAddress(rs.getString("address"));
				e.setCreateTime(rs.getTimestamp("createTime"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Employee> queryByPage(int currentPage, int pageSize) {
		List<Employee> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_employee order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSex(rs.getInt("sex"));
				e.setAge(rs.getInt("age"));
				e.setEdu(rs.getInt("edu"));
				e.setDegree(rs.getInt("degree"));
				e.setJob(rs.getString("job"));
				e.setDeptId(rs.getInt("deptId"));
				e.setState(rs.getInt("state"));
				e.setPhone(rs.getString("phone"));
				e.setAddress(rs.getString("address"));
				e.setCreateTime(rs.getTimestamp("createTime"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Employee> queryByPage(int currentPage, int pageSize, String condition) {
		List<Employee> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_employee " + condition + " order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSex(rs.getInt("sex"));
				e.setAge(rs.getInt("age"));
				e.setEdu(rs.getInt("edu"));
				e.setDegree(rs.getInt("degree"));
				e.setJob(rs.getString("job"));
				e.setDeptId(rs.getInt("deptId"));
				e.setState(rs.getInt("state"));
				e.setPhone(rs.getString("phone"));
				e.setAddress(rs.getString("address"));
				e.setCreateTime(rs.getTimestamp("createTime"));
				list.add(e);
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
		String sql = "select count(*) from hr_employee";
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
		String sql = "select count(*) from hr_employee " + condition;
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
