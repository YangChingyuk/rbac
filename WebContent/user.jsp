<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<script type="text/javascript">

	//将表单数据转为json
	function form2Json(id) {
	
	    var arr = $("#" + id).serializeArray();
	    var jsonStr = "";
	
	   	jsonStr += '{';
	    for (var i = 0; i < arr.length; i++) {
	        jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",'
	    }
	    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
	    jsonStr += '}'
	
	    var json = JSON.parse(jsonStr);
	    return json;
	}
	
	var url = "";
	$(function(){
		
		$("#dg").datagrid({
			idField:"id",
			url:"user/queryByPage",
			queryParams: form2Json("searchform"),//查询参数
			columns:[[
				{field:"",checkbox:true},
				{field:"id",title:"编号",width:100,align:"center"},
				{field:"username",title:"账号",width:100,align:"center"},
				{field:"password",title:"密码",width:100,align:"center"},
				{field:"empId",title:"员工编号",width:100,align:"center"},
				{field:"state",title:"用户状态",width:100,align:"center",formatter: function(value,row,index){
					if (value==1){
						return "正常";
					} else {
						return "禁用";
					}
				}
				},
				{field:"createTime",title:"创建时间",width:100,align:"center"}
			]],
			fitColumns:true,
			toolbar: [{
				text:"新增",
				iconCls: 'icon-add',
				handler: function(){
					add();
				}
			},'-',{
				text:"修改",
				iconCls: 'icon-edit',
				handler: function(){
					update();
				}
			},'-',{
				text:"删除",
				iconCls: 'icon-remove',
				handler: function(){
					remove();
				}
			},'-',{
				text:"分配角色",
				iconCls: 'icon-add',
				handler: function(){
					setRole();
				}
			}],
			pagination:true,
			pageSize:30,
			onLoadSuccess:function(data){
				$("#rolegrid").datagrid({
					idField:"id",
					url:"role/queryAll",
					columns:[[
						{field:"",checkbox:true},
						{field:"id",title:"角色编号",width:100,align:"center"},
						{field:"name",title:"角色名称",width:100,align:"center"},
						{field:"remark",title:"角色备注",width:100,align:"center"}
					]],
					fitColumns:true,
					scrollbarSize:0
				});
			}
		});
		
		//点击搜索
		$("#search_btn").click(function() {
            $('#dg').datagrid({ 
            	queryParams: form2Json("searchform")
            });   
        });
	});
	//分配角色
	function setRole(){
		var array = $("#dg").datagrid("getSelections");
		if(array.length==0){
			$.messager.alert("提示","请选择记录","info");
			return;
		}
		//用户的编号Id
		var uids = "";
		for(var i=0;i<array.length;i++){
			uids += array[i].id+",";
		}
		uids = uids.substring(0,uids.length-1);
		//获取用户已有角色	
		$.ajax({
			url:"user/getOwnerRoles",
			type:"post",
			data:"uids="+uids,
			dataType:"json",
			success:function(result){
				$("#role").dialog({
					title:"角色列表",
					buttons:[{
						text:'分配角色',
						iconCls:"icon-save",
						handler:function(){
							saveRole(uids);
						}
					},{
						text:'关闭',
						iconCls:"icon-cancel",
						handler:function(){
							$("#role").dialog("close");
						}
					}]
				});
				for(var i=0;i<result.length;i++){
					//根据后台返回的角色id，设置列表框默认选中状态
					$("#rolegrid").datagrid("selectRecord",result[i].id);
				}
				//打开对话框
				$("#role").dialog("open");
			}
		});
		
	}
	//保存角色
	function saveRole(uids){
		var array = $("#rolegrid").datagrid("getSelections");
		if(array.length==0){
			$.messager.alert("提示","请选择角色","info");
			return;
		}
		//角色的编号Id
		var rids = "";
		for(var i=0;i<array.length;i++){
			rids += array[i].id+",";
		}
		rids = rids.substring(0,rids.length-1);
		$.ajax({
			url:"user/saveRole",
			type:"post",
			data:"uids="+uids+"&rids="+rids,
			dataType:"json",
			success:function(result){
				if(result.state==0){
					$("#rolegrid").datagrid("reload");
					$.messager.alert("提示",result.msg,"info");
				}else{
					$.messager.alert("提示",result.msg,"error");
				}
				//关闭弹出框
		        $("#role").dialog("close");
				//清除之前选择的所有行
				$("#dg").datagrid("clearSelections");
			}
		});
	}
	//删除记录
	function remove(){
		var array = $("#dg").datagrid("getSelections");
		if(array.length==0){
			$.messager.alert("提示","请选择要删除的记录","info");
			return;
		}
		$.messager.confirm("提示","你确定要删除这"+array.length+"条记录吗？",function(r){
			if(r){
				var ids = "";
				for(var i=0;i<array.length;i++){
					ids += array[i].id+",";
				}
				ids = ids.substring(0,ids.length-1);
				$.ajax({
					url:"user/deleteMore",
					type:"post",
					data:"ids="+ids,
					dataType:"json",
					success:function(result){
						if(result.state==0){
							//刷新页面
							$("#dg").datagrid("reload");
							$.messager.alert("提示",result.msg,"info");
						}else{
							$.messager.alert("提示",result.msg,"error");
						}
						//清除之前选择的所有行
						$("#dg").datagrid("clearSelections");
					}
				});
			}
		});
	}
	//打开弹出框
	function openFormDialog(){
		$("#dd").dialog({
			buttons:[{
				text:'保存',
				iconCls:"icon-save",
				handler:function(){
					save();
				}
			},{
				text:'重置',
				iconCls:"icon-reload",
				handler:function(){
					var array = $("#dg").datagrid("getSelections");
					if(array.length==1){
						//表单填充内容
				        $("#ff").form("load",array[0]);
					}
					if(array.length==0){
						$("#ff").form("reset");
					}
				}
			},{
				text:'关闭',
				iconCls:"icon-cancel",
				handler:function(){
					$("#dd").dialog("close");
				}
			}]
		});
		//打开对话框
		$("#dd").dialog("open");
	}
	//新增记录
	function add(){
		$("#dg").datagrid("clearSelections");
		//重置表单内容
        $("#ff").form("reset");
		//新增记录的请求地址
		url = "user/add";
		//打开弹出框
		openFormDialog();
		//设置弹出框标题
		$("#dd").dialog("setTitle","新增学生信息");
	}
	//修改记录
	function update(){
		var array = $("#dg").datagrid("getSelections");
		if(array.length==0){
			$.messager.alert("提示","请选择要修改的记录","info");
			return;
		}else if(array.length>1){
			$.messager.alert("提示","每次只能修改一条记录","info");
			return;
		}
		//重置表单内容
        $("#ff").form("reset");
      	//修改记录的请求地址
		url = "user/update?id="+array[0].id;
		//表单填充内容
        $("#ff").form("load",array[0]);
      	//打开弹出框
		openFormDialog();
		//设置弹出框标题
		$("#dd").dialog("setTitle","修改学生信息");
	}
	//保存或者更新数据
	function save(){
		$("#ff").form("submit",{
			url:url,
			onSubmit:function(){
				//调用validate方法校验表单中所有的字段有效性，只有所有的字段有效才返回true
				return $(this).form("validate");
			},
			success:function(result){
				//接收服务器返回的json格式字符串数据转换成json对象
			 	var data = eval('(' + result + ')');   
		        if (data.state==0){    
		           	$.messager.alert("提示消息",data.msg,"info"); 
		           	//刷新列表
		           	$("#dg").datagrid("reload");
		        } else{
		        	$.messager.alert("提示消息",data.msg,"error");
		        }
		        //关闭弹出框
		        $("#dd").dialog("close");
			}
		});
	}
	
</script>
<style type="text/css">
	.right{
		text-align: right;
	}
</style>
</head>
<body>
	<div style="height: 4%">
		<form name="searchform" method="post" id ="searchform">
			账号：<input type="text" name="qname" class="easyui-textbox">
				<a id="search_btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
		        <a id="import_btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">导入</a>
		        <a id="export_btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">导出</a>
	  	</form>
  	</div>
  	<div style="height: 96%">
		<table id="dg" fit="true"></table>
	</div>
	<div id="dd" class="easyui-dialog" style="width: 400px;text-align: center;padding: 10px;" closed="true">
		<form id="ff" method="post">
			<table style="margin: 0 auto;">
				<tr>
					<td class="right">用户账号：</td>
					<td><input type="text" name="username" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">用户密码：</td>
					<td><input type="password" name="password" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">用户确认密码：</td>
					<td><input type="password" name="repassword" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">员工编号：</td>
					<td><input type="text" name="empId" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">用户状态：</td>
					<td>
						<input type="radio" name="state" value="1" checked="checked">正常
						<input type="radio" name="state" value="0">禁用
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="role" class="easyui-dialog" style="width: 400px;text-align: center;" closed="true">
		<table id="rolegrid"></table>
	</div>
</body>
</html>