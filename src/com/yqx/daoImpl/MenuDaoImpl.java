package com.yqx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yqx.dao.MenuDao;
import com.yqx.entity.Menu;
import com.yqx.util.DBUtil;

public class MenuDaoImpl implements MenuDao{

	@Override
	public void add(Menu menu) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_sys_menu(code,pcode,name,url,state,remark) values(?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, menu.getCode());
			pst.setString(2, menu.getPcode());
			pst.setString(3, menu.getName());
			pst.setString(4, menu.getUrl());
			pst.setInt(5, menu.getState());
			pst.setString(6, menu.getRemark());
			System.out.println("成功新增" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Menu> list) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into hr_sys_menu(code,pcode,name,url,state,remark) values(?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Menu menu = list.get(i);
				pst.setString(1, menu.getCode());
				pst.setString(2, menu.getPcode());
				pst.setString(3, menu.getName());
				pst.setString(4, menu.getUrl());
				pst.setInt(5, menu.getState());
				pst.setString(6, menu.getRemark());

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
		String sql = "delete from hr_sys_menu where id=?";
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
		String sql = "delete from hr_sys_menu where id in (" + ids + ")";
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
	public void update(Menu menu) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "update hr_sys_menu set code=?,pcode=?,name=?,url=?,state=?,remark=? where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, menu.getCode());
			pst.setString(2, menu.getPcode());
			pst.setString(3, menu.getName());
			pst.setString(4, menu.getUrl());
			pst.setInt(5, menu.getState());
			pst.setString(6, menu.getRemark());
			pst.setInt(7, menu.getId());
			System.out.println("成功修改" + pst.executeUpdate() + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public Menu queryById(int id) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_menu where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				Menu a = new Menu();
				a.setId(rs.getInt("id"));
				a.setCode(rs.getString("code"));
				a.setPcode(rs.getString("pcode"));
				a.setName(rs.getString("name"));
				a.setUrl(rs.getString("url"));
				a.setState(rs.getInt("state"));
				a.setRemark(rs.getString("remark"));
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
	public List<Menu> queryAll() {
		List<Menu> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_menu order by id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Menu a = new Menu();
				a.setId(rs.getInt("id"));
				a.setCode(rs.getString("code"));
				a.setPcode(rs.getString("pcode"));
				a.setName(rs.getString("name"));
				a.setUrl(rs.getString("url"));
				a.setState(rs.getInt("state"));
				a.setRemark(rs.getString("remark"));
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
	public List<Menu> byIdgetAll(Integer id) {
		List<Menu> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT hr_sys_menu.* \r\n" + 
				"\r\n" + 
				"from hr_sys_user,hr_sys_role,hr_sys_menu,hr_sys_userrole,hr_sys_rolemenu\r\n" + 
				"\r\n" + 
				"where hr_sys_user.id=hr_sys_userrole.uid\r\n" + 
				"\r\n" + 
				"and hr_sys_role.id=hr_sys_userrole.rid\r\n" + 
				"\r\n" + 
				"and hr_sys_userrole.rid=hr_sys_rolemenu.rid\r\n" + 
				"\r\n" + 
				"and hr_sys_rolemenu.mid=hr_sys_menu.id\r\n" + 
				"\r\n" + 
				"and hr_sys_user.id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Menu a = new Menu();
				a.setId(rs.getInt("hr_sys_menu.id"));
				a.setCode(rs.getString("hr_sys_menu.code"));
				a.setPcode(rs.getString("hr_sys_menu.pcode"));
				a.setName(rs.getString("hr_sys_menu.name"));
				a.setUrl(rs.getString("hr_sys_menu.url"));
				a.setState(rs.getInt("hr_sys_menu.state"));
				a.setRemark(rs.getString("hr_sys_menu.remark"));
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
	public List<Menu> queryByPage(int currentPage, int pageSize) {
		List<Menu> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_menu order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Menu a = new Menu();
				a.setId(rs.getInt("id"));
				a.setCode(rs.getString("code"));
				a.setPcode(rs.getString("pcode"));
				a.setName(rs.getString("name"));
				a.setUrl(rs.getString("url"));
				a.setState(rs.getInt("state"));
				a.setRemark(rs.getString("remark"));
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
	public List<Menu> queryByPage(int currentPage, int pageSize, String condition) {
		List<Menu> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from hr_sys_menu " + condition + " order by id limit ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (currentPage - 1) * pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				Menu a = new Menu();
				a.setId(rs.getInt("id"));
				a.setCode(rs.getString("code"));
				a.setPcode(rs.getString("pcode"));
				a.setName(rs.getString("name"));
				a.setUrl(rs.getString("url"));
				a.setState(rs.getInt("state"));
				a.setRemark(rs.getString("remark"));
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
		String sql = "select count(*) from hr_sys_menu";
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
		String sql = "select count(*) from hr_sys_menu " + condition;
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
	public List<Menu> queryAllMenusByRids(String rids) {
		List<Menu> list = new ArrayList<>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select m.* from hr_sys_menu m,hr_sys_role r,hr_sys_rolemenu rm where m.id=rm.mid and r.id=rm.rid and r.id in ("+rids+") order by r.id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Menu a = new Menu();
				a.setId(rs.getInt("id"));
				a.setCode(rs.getString("code"));
				a.setPcode(rs.getString("pcode"));
				a.setName(rs.getString("name"));
				a.setUrl(rs.getString("url"));
				a.setState(rs.getInt("state"));
				a.setRemark(rs.getString("remark"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

}
