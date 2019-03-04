package com.yqx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.yqx.util.MenuTree;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private HttpServletRequest request;
	private HttpServletResponse response;

	@RequestMapping("/add")
	public void add(Menu menu) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			MenuDao dao = new MenuDaoImpl();
			dao.add(menu);
			jo.put("state", 0);
			jo.put("msg", "成功新增记录");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "新增记录失败" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}

	@RequestMapping("/deleteMore")
	public void deleteMore(String ids) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			MenuDao dao = new MenuDaoImpl();
			dao.deleteMore(ids);
			jo.put("state", 0);
			jo.put("msg", "成功删除记录");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "删除记录失败" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}

	@RequestMapping("/update")
	public void update(Menu menu) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			MenuDao dao = new MenuDaoImpl();
			dao.update(menu);
			jo.put("state", 0);
			jo.put("msg", "成功更新记录");
		} catch (Exception e) {
			jo.put("state", -1);
			jo.put("msg", "更新记录失败" + e.getMessage());
		} finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}

	@RequestMapping("/queryByPage")
	public void queryByPage(String page) {

		String qname = request.getParameter("qname");
		String qusername = request.getParameter("qusername");
		String qsex = request.getParameter("qsex");
		String qbeginDate = request.getParameter("qbeginDate");
		String qendDate = request.getParameter("qendDate");
		// 获取当前页
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
		MenuDao dao = new MenuDaoImpl();

		int sp = 1;
		int totals = dao.getTotals(condition);
		int pageSize = Integer.parseInt(rows);
		int pageCounts = totals / pageSize;
		if (totals % pageSize != 0) {
			pageCounts++;
		}
		try {
			sp = Integer.parseInt(page);
		} catch (Exception e) {
			sp = 1;
		}
		if (sp > pageCounts) {
			sp = pageCounts;
		}
		if (sp < 1) {
			sp = 1;
		}
		List<Menu> list = dao.queryByPage(sp, pageSize, condition);
		try {
			PrintWriter out = response.getWriter();
			JSONObject jo = new JSONObject();
			jo.put("total", totals);
			jo.put("rows", list);
			String json = JSON.toJSONString(jo);
			System.out.println(json);
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/queryAll")
	public void queryAll() {
		MenuDao dao = new MenuDaoImpl();
		List<Menu> list = dao.queryAll();
		try {
			PrintWriter out = response.getWriter();
			MenuTree mt = new MenuTree();
			mt.setList(list);
			String json = mt.getMenuTreeJson();
			System.out.println(json);
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/mqueryAll")
	@ResponseBody
	public List<Menu> mqueryAll() {
		MenuDao dao = new MenuDaoImpl();
		List<Menu> list = dao.queryAll();
		return list;
	}

	@RequestMapping("/byIdgetAll")
	public void byIdgetAll(Integer id) {
		MenuDao dao = new MenuDaoImpl();
		List<Menu> list = dao.byIdgetAll(id);
		try {
			PrintWriter out = response.getWriter();
			MenuTree mt = new MenuTree();
			mt.setList(list);
			String json = mt.getMenuTreeJson();
			System.out.println(json);
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/*
	 * 时间格式
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@ModelAttribute
	public void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.response.setContentType("text/html;charset=utf-8");
	}
}
