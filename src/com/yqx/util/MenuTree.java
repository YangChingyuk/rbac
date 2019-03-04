package com.yqx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.yqx.entity.Menu;


public class MenuTree {

	private List<Menu> list;
	
	public List<Map<String, Object>> createMenuTree(){
		List<Map<String, Object>> rootList = new ArrayList<>();
		for(Menu menu:list) {
			Map<String, Object> map = null;
			if(menu.getPcode()==null || menu.getPcode().equals("")) { //判断是否是根节点
				map = new HashMap<String, Object>();
				map.put("id", menu.getCode());
				map.put("text", menu.getName());
				map.put("children", createSubMenuTree(menu.getCode()));
				Map<String, Object> attrMap = new HashMap<String, Object>();
				attrMap.put("url", menu.getUrl());
				attrMap.put("state", menu.getState());
				map.put("attributes", attrMap);
			}
			if(map!=null) {
				rootList.add(map);
			}
		}
		return rootList;
	}
	
	public List<Map<String, Object>> createSubMenuTree(String pcode){
		List<Map<String, Object>> leafList = new ArrayList<>();
		for(Menu menu:list) {
			Map<String, Object> map = null;
			if(menu.getPcode()!=null&&menu.getPcode().equals(pcode)) { //判断子节点
				map = new HashMap<String, Object>();
				map.put("id", menu.getCode());
				map.put("text", menu.getName());
				map.put("children", createSubMenuTree(menu.getCode()));
				Map<String, Object> attrMap = new HashMap<String, Object>();
				attrMap.put("url", menu.getUrl());
				attrMap.put("state", menu.getState());
				map.put("attributes", attrMap);
			}
			if(map!=null) {
				leafList.add(map);
			}
		}
		return leafList;
	}
	
	public String getMenuTreeJson() {
		return JSONArray.toJSONString(createMenuTree());
	}

	public List<Menu> getList() {
		return list;
	}

	public void setList(List<Menu> list) {
		this.list = list;
	}
}
