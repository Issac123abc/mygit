<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>圈子</title>
<script type="text/javascript" src="${ctx }/js/jquery-3.3.1.min.js"></script>
<style type="text/css">
	td{ width:20%; height:200px; border:0.1px solid gray; text-align: center;}
	.setting td{height:15px; border:0px;}
	.setting a{text-decoration: none;}
</style>
<script type="text/javascript">
	$(function(){
		var width=$('#name').outerWidth();
		$('#setting').css('left',740+width+20+'px');
		$('#setting').hover(function() {
			$('#setting').append('<table style="width:120px; background-color:white;" class="setting">'+
			'<tr><td><a href="${ctx }/user/edituser/${requestScope.user.id}">修改资料</a></td></tr><tr><td><a href="${ctx }/user/logout">退出登录</a></td></tr></table>');
		},function(){
			$('#setting').find(".setting").remove();
		});
	});

	
</script>
</head>
<body style="width:70%;  margin:0px auto;">
	<div id="top" style="width:100%; height:35px; position:relative;">
		<div style="position:absolute; left:700px; height:30px; margin-top:4px;">			
			<img alt="" src="${ctx }/${requestScope.user.photo }" style="width:27px; height:27px;">
		</div>
		<div style="position:absolute; left:740px; height:30px; line-height:35px;">
			<span id="name">${requestScope.user.username }</span>&nbsp;&nbsp;&nbsp;&nbsp;			
		</div>		

		<div id="setting" style="height:26px; position:absolute; top:0px; margin-top:4px; cursor:pointer;">
			<img alt="" src="${ctx }/images/settings/setting.jpg" style="width:26px; height:26px;">
		</div>			
	</div>
	<div id="head" style="width:100%; height:450px;">
		<img alt="" style="width:100%; height:100%;" src="${ctx}/images/001.jpg">

	</div>
	<div id="content">
		<table style="width:100%;" cellpadding="5" cellspacing="1">
			<tr>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>			
			<tr>
				<c:forEach items="${requestScope.moudles }" var="m">					
					<td onclick="window.location.href='${ctx}/moudle?title=${m.title }'">
						<img alt="${m.title }" src="${ctx}/${m.images }" style="width:120px; height:120px; cursor: pointer;">
						<div style="font-size: 25px; margin-top:5px;">${m.title }</div>
					</td>
				</c:forEach>
			</tr>
			
		</table>
	</div>
	<div id="footer" style="width:100%; height:250px;"></div>
</body>
</html>