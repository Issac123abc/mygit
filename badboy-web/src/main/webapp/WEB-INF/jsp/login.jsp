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
<script type="text/javascript">
	var submit=false;
	
	function check(){
				
		var name=$('#name').val();
		var password=$('#password').val();

		if(name==''||name==null){
			$('#param').css('display','none');
			$('#hide').css('display','block');
			$('#info').html('请输入你的账号');
			return false;
		}
		if(password==''||password==null){
			$('#param').css('display','none');
			$('#hide').css('display','block');
			$('#info').html('请输入你的密码');
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
	<div style="width:500px; height:350px; margin:150px auto; position:relative;">
		<div style="width:280px; font-size:23px; font-weight:500; text-align:center; margin:15px auto;">账号密码登录</div>
		<c:if test="${!empty param.msg }">
			<div id="param" style="width:260px; height:30px; margin:0px auto; position:relative;">
				<img alt="" src="${ctx }/images/settings/tips.png" style="width:20px; height:20px;">
				<div style="position:absolute; top:-1px; left:32px; color:red;">该用户还没有注册或密码错误</div>
			</div>
		</c:if>
		<div id="hide" style="width:260px; height:30px; margin:0px auto; position:relative; display:none;">
				<img alt="" src="${ctx }/images/settings/tips.png" style="width:20px; height:20px;">
				<div id="info" style="position:absolute; top:-1px; left:32px; color:red;"></div>
		</div>
		<form action="${ctx }/user/login" method="post" onsubmit="return check();" style="height:140px; height:170px;">
			<table style="width:280px; position:absolute; top:70px; left:110px;">
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

		<div style="position:absolute; top:200px; left:110px;"><a href="${ctx }/register.jsp">立即注册</a></div>
		<div style="position:absolute; top:200px; right:125px;"><a href="">忘记密码</a></div>
		<input type="hidden" value="${param.msg}" id="msg"/>
	</div>
</body>
</html>