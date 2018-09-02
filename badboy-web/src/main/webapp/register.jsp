<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<style type="text/css">
	tr{height:40px;}
	input{height:20px;}
</style>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx }/js/layer/layer.js"></script>
<script type="text/javascript">
	var submit=false;
	
	function check(){
		var p1=$('#password').val();
		var p2=$('#password2').val();
		var phone=$('#phone').val();
		var email=$('#email').val();
		//var login_name=$('#login_name').val();
		
		if(phone==''||phone==null){
			layer.alert("手机号码不能为空！", {offset: '150px'});
			return false;
		}
		if(email==''||email==null){
			layer.alert("电子邮箱不能为空！", {offset: '150px'});
			return false;
		}
		/* if(login_name==''||login_name==null){
			alert("登录名不能为空！");
			return false;
		} */
		if(p1==''||p1==null){
			layer.alert("密码不能为空！", {offset: '150px'});
			return false;
		}
		if(p1!=p2){
			layer.alert("密码不一致！", {offset: '150px'});
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
		<form action="user/register" method="post" onsubmit="return check();">
			<table style="width:280px; margin:0px auto;">
				<tr>
					<td style="width:90px; height:30px;">手机号码：</td>
					<td><input id="phone" type="text" name="phone" /></td>
				</tr>
				<tr>
					<td style="width:90px;">邮箱：</td>
					<td><input id="email" type="text" name="email" /></td>
				</tr>
				<!-- <tr>
					<td style="width:90px;">登录名：</td>
					<td><input id="login_name" type="text" name="login_name" /></td>
				</tr> -->
				<tr>
					<td style="width:90px;">密码：</td>
					<td><input id="password" type="password" name="password" /></td>
				</tr>
				<tr>
					<td style="width:90px;">确认密码：</td>
					<td><input id="password2" type="password" name="password2" /></td>
				</tr>
				<tr>
					<td colspan="2"><input style="width:100%; height:30px;" type="submit" value="注 册" /></td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>