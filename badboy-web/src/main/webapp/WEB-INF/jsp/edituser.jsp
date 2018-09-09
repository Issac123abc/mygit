<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户资料</title>
<style type="text/css">
	td{height:35px; line-height: 35px;}
	input{height:20px;}
</style>

<script type="text/javascript" src="${ctx }/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	var image = '';

	function imgclick(){
		$('#file').click();
	}
	
	function selectImage(file){
		if(!file.files || !file.files[0]){
			return;
		}
		var reader = new FileReader();
		reader.onload = function(evt){
		 	$('#image').attr("src",evt.target.result);
		 	image = evt.target.result;
		 	$('#photo').val(image);
		};
		reader.readAsDataURL(file.files[0]);	
	}
	
	function check(){
		var gender=$('input[name="gender"]:checked').val();
		$('#gender').val(gender);
		return true;
	}
	
</script>
</head>
<body>
	<div style="width:80%; margin:113px auto;">
		<form style="width:650px; margin:0px auto; position:relative;" action="${ctx }/user/upuser" method="POST" onsubmit="return check();">
			<table style="width:650px; margin:0px auto;">
				<tr>
					<td style="width:13%; height:30px;">用户名：</td>
					<td style="width:50%;"><input style="width:97%;" id="username" type="text" name="username" value="${requestScope.user.username }" /></td>
					<td style="width:40%; visibility: hidden;">
						<input style="width:97%;" id="file" type="file" onchange="selectImage(this);" />
						<input id="photo" type="hidden" name="photo" />
						<input id="uid" name="id" type="hidden" value="${requestScope.user.id }">
						<input id="gender" name="gender" type="hidden">
					</td>					
				</tr>
				<tr>
					<td style="width:13%;">登录名：</td>
					<td style="width:50%;"><input style="width:97%;" id="login_name" type="text" name="login_name" value="${requestScope.user.login_name }" /></td>
				</tr>
				<tr>
					<td style="width:13%;">年龄：</td>
					<td style="width:50%;"><input style="width:97%;" id="age" type="text" name="age" value="${requestScope.user.age }" /></td>
				</tr>				
				<c:if test="${requestScope.user.gender eq 0 }">
					<tr>
						<td style="width:13%;">性别：</td>
						<td style="width:50%;">男性：<input style="height:12px;" type="radio" checked="checked" name="gender" value="0" /> 女性：<input style="height:12px;" type="radio" name="gender" value="1" /></td>
					</tr>
				</c:if>
				<c:if test="${requestScope.user.gender eq 1 }">
					<tr>
						<td style="width:13%;">性别：</td>
						<td style="width:50%;">男性：<input style="height:12px;" type="radio" name="gender" value="0" /> 女性：<input style="height:12px;" checked="checked" type="radio" name="gender" value="1" /></td>
					</tr>
				</c:if>
				<tr>
					<td style="width:13%;">地区：</td>
					<td style="width:50%;"><input style="width:97%;" id="region" type="text" name="region" value="${requestScope.user.region }" /></td>
				</tr>				
				<tr>
					<td style="width:13%;">签名：</td>
					<td style="width:50%;"><input style="width:97%;" id="autograph" type="text" name="autograph" value="${requestScope.user.autograph }" /></td>
				</tr>
				<tr>
					<td colspan="2"><input style="width:99%; height:30px;" type="submit" value="确 定" /></td>
				</tr>
			</table>
			<div style="position:absolute; right:20px; top:0px;">
				<img id="image" style="width:150px; height:150px;" alt="头像" src="${ctx }/${requestScope.user.photo }">
				<div><button onclick="imgclick();" type="button" style="width:150px; height:30px; margin-top:10px; background-color: #1E9FFF;">上传图片</button></div>
				<div style="margin-top:10px;">(图片只支持jpg格式)</div>
			</div>
			<div style="margin-top:50px; text-align: left; color:blue;">备注:&nbsp;&nbsp;登录名只能修改一次且只能为字母和数字组合，下次可以直接用登录名登录账户</div>
			
		</form>		
	</div>
	
</body>
</html>