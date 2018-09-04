<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<style type="text/css">
	tr{height:40px;}
	input{height:20px;}
</style>
<script type="text/javascript" src="${ctx }/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx }/js/layer/layer.js"></script>
<script type="text/javascript">
	var submit=false;

	$(function(){
		var msg=$('#msg').val();
		if(msg=='error'){
			layer.alert("该用户还没有注册或密码错误！", {offset: '150px'});
		}
	});
	
	function check(){
				
		var name=$('#name').val();
		var password=$('#password').val();

		if(name==''||name==null){
			layer.alert("账号不能为空！", {offset: '150px'});
			return false;
		}
		if(password==''||password==null){
			layer.alert("密码不能为空！", {offset: '150px'});
			return false;
		}
		
		if(!submit){
			submit=true;
			return submit;
		}
		return false;
	}
		
</script>
</head>
<body>
	<div style="width:80%; margin:150px auto;">
		<form action="${ctx }/user/login" method="post" onsubmit="return check();">
			<table style="width:280px; margin:0px auto;">
				<tr>
					<td style="width:80px; height:30px;">账号：</td>
					<td><input id="name" type="text" name="name" placeholder="手机号/邮箱/登录名" /></td>
				</tr>				
				<tr>
					<td style="width:80px;">密码：</td>
					<td><input id="password" type="password" name="password" /></td>
				</tr>				
				<tr>
					<td colspan="2"><input style="width:95%; height:30px;" type="submit" value="登 录" /></td>
				</tr>
			</table>
		</form>
		<input type="hidden" value="${param.msg}" id="msg"/>
	</div>
</body>
</html>