<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<style type="text/css">
	h2{
		text-align: center;
		padding:5%;	
	}
	#box{
		margin: 0 auto;
		text-align: center;
	}
</style>

<script type="text/javascript">
	
	function loginCheck(){
		var uname = $('#username').textbox('getValue');
		var pwd = $('#password').textbox('getValue');
		$.ajax({
			url:"user/queryAll",
			type:"post",
			dataType:"json",
			success:function(result){
				for(var i=0;i<result.length;i++){
					var row = result[i];
						if(uname == row.username && pwd == row.password){
							$.messager.confirm("提示信息","登录成功",function(r){
								if(r){
									$.ajax({
										url:"menu/byIdgetAll",
										type:"post",
										data:"id="+row.id,
										dataType:"json",
										success:function(result){
											location.href="byIdgetAll.jsp";
										}
									})
									
								}
							});
							return;
						}else{
							$.messager.alert("提示信息","账号或者密码错误","info");
							return;
						}
				}
			}
		});
	}
	
	function registerTo(){
		location.href="register.jsp";
	}

</script>

</head>
<body>
	<h2>欢迎访问</h2>
	<div id="box">
		帐号:<input id="username" class="easyui-textbox" data-options="iconCls:'icon-man',prompt:'请输入帐号'" style="width:200px;"> <br/><br/>
		密码:<input id="password" class="easyui-passwordbox" data-options="iconCls:'icon-lock',prompt:'请输入密码'" style="width:200px"> <br/><br/>
		<a id="loginBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="loginCheck()">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a id="registerBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="registerTo()">注册</a> 
	</div>
</body>
</html>