package com.yqx.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yqx.dao.MenuDao;
import com.yqx.dao.RoleDao;
import com.yqx.dao.RoleMenuDao;
import com.yqx.daoImpl.MenuDaoImpl;
import com.yqx.daoImpl.RoleDaoImpl;
import com.yqx.daoImpl.RoleMenuDaoImpl;
import com.yqx.entity.Menu;
import com.yqx.entity.Role;
import com.yqx.entity.RoleMenu;

@Controller
@RequestMapping("/role")
public class RoleController {
 
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@RequestMapping("/add")
	public void add(Role s,Map<String,Object> map) throws Exception {
		RoleDao dao = new RoleDaoImpl();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			dao.add(s);
			jo.put("state", 0);
			jo.put("msg", "成功新增记录");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "新增记录失败" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			out.write(str);
			out.flush();
			out.close();
		}
		
	}
	@RequestMapping("/deleteById")
	public void deleteById(int id) {
		RoleDao dao = new RoleDaoImpl();
		dao.deleteById(id);
	}
	@RequestMapping("/deleteMore")
	@ResponseBody
	public void deleteMore(String ids,Map<String,Object> map) throws Exception {
		RoleDao dao = new RoleDaoImpl();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			dao.deleteMore(ids);
			jo.put("state", 0);
			jo.put("msg", "成功删除记录");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "删除记录失败" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	public void update(Role s,Map<String,Object> map) throws Exception {
		RoleDao dao = new RoleDaoImpl();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		try {
			dao.update(s);
			jo.put("state", 0);
			jo.put("msg", "成功修改记录");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "修改记录失败" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			out.write(str);
			out.flush();
			out.close();
		}
		
	}
	@RequestMapping("/queryById")
	@ResponseBody
	public Role queryById(int id,String currentPage,Map<String,Object> map) {
		String qname = request.getParameter("qname");
		String qusername = request.getParameter("qusername");
		String qsex = request.getParameter("qsex");
		
		RoleDao dao = new RoleDaoImpl();
		Role s = dao.queryById(id);
		map.put("student", s);
		map.put("currentPage", currentPage);
		map.put("qname", qname);
		map.put("qusername", qusername);
		map.put("qsex", qsex);
		return s;
	}
	@RequestMapping("/queryByPage")
	@ResponseBody
	public void queryByPage(String page,Map<String,Object> map) throws Exception {
		String qname = request.getParameter("qname");
		String qusername = request.getParameter("qusername");
		String qsex = request.getParameter("qsex");
		String qbeginDate = request.getParameter("qbeginDate");
		String qendDate = request.getParameter("qendDate");

		String currentPage = request.getParameter("page");
		String rows = request.getParameter("rows");

		String condition = " where 1=1 ";
		if (qname != null && !qname.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and name like '%" + qname + "%'";
		}
		if (qusername != null && !qusername.equals("") && !qname.equalsIgnoreCase("null")) {
			condition += " and username like '%" + qusername + "%'";
		}
		if (qsex != null && !qsex.equals("") && !qsex.equals("-1") && !qname.equalsIgnoreCase("null")) {
			condition += " and sex = " + qsex;
		}
		if (qbeginDate != null && !qbeginDate.equals("")) {
			condition += " and birthday >= '" + qbeginDate + "'";
		}
		if (qendDate != null && !qendDate.equals("")) {
			condition += " and birthday <= '" + qendDate + "'";
		}
		RoleDao dao = new RoleDaoImpl();

		int sp = 1;

		int totals = dao.getTotals(condition);

		int pageSize = Integer.parseInt(rows);

		int pageCounts = totals / pageSize;
		if (totals % pageSize != 0) {
			pageCounts++;
		}
		try {
			sp = Integer.parseInt(currentPage);
		} catch (Exception e) {
			sp = 1;
		}
		if (sp > pageCounts) {
			sp = pageCounts;
		}
		if (sp < 1) {
			sp = 1;
		}
		List<Role> list = dao.queryByPage(sp, pageSize, condition);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		jo.put("total", totals);
		jo.put("rows", list);
		String json = JSON.toJSONString(jo);
		out.write(json);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/queryAll")
	@ResponseBody
	public List<Role> queryAll() {
		RoleDao dao = new RoleDaoImpl();
		List<Role> list = dao.queryAll();
		return list;
	}
	
	@RequestMapping("/saveMenu")
	public void saveMenu(String rids, String mids) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			List<RoleMenu> list = new ArrayList<>();
			RoleMenuDao roleMenuDao = new RoleMenuDaoImpl();
			roleMenuDao.deleteRoleMenusByRids(rids);
			
			for (String rid : rids.split(",")) {
				for (String mid : mids.split(",")) {
					RoleMenu roleMenu = new RoleMenu();
					roleMenu.setRid(Integer.parseInt(rid));
					roleMenu.setMid(Integer.parseInt(mid));
					list.add(roleMenu);
				}
			}
			roleMenuDao.addMore(list);
			jo.put("state", 0);
			jo.put("msg", "成功分配记录");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "分配记录失败" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			out.write(str);
			out.flush();
			out.close();
		}

	}
	
	@RequestMapping("/getOwnerMenus")
	public void getOwnerMenus(String rids) {
		try {
			PrintWriter out = response.getWriter();
			MenuDao dao = new MenuDaoImpl();
			List<Menu> list = dao.queryAllMenusByRids(rids);
			String str = JSON.toJSONString(list);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 处理参数为日期格式
	 * */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
	            true));
	}
	
	@ModelAttribute
	public void setRequestAndResponse(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.response.setContentType("text/html;charset=utf-8");
	}
}
