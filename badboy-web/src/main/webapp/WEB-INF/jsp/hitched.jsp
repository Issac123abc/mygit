<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顺风车</title>
<%@ include file="topjs.jsp" %>

</head>
<body style="width:50%;  margin:0px auto;">
	<%@ include file="top.jsp" %>
	<div style="width:100%; height:150px; margin:20px auto; position:relative; font-size:20px;">
		<div style="width:150px; height:150px; position:absolute;"><img alt="头像" src="${ctx }/${requestScope.user.photo}" style="width:150px; height:150px;"></div>
		<div style="position:absolute; top:10px; left:175px; font-size:25px;">${requestScope.user.username}</div>
		<div style="position:absolute; bottom:2px; left:210px;"><a href="">动态</a></div>
		<div style="position:absolute; bottom:2px; left:280px;"><a href="">我的动态</a></div>
		<div style="position:absolute; bottom:2px; left:390px;"><a href="">消息</a></div>
	</div>
	<div id="content" style="width:100%; height:120px; margin:20px auto; position:relative;">
		 <div style="width:85%; border:1px solid #dddddd;"><textarea placeholder="请输入内容" class="layui-textarea"></textarea></div>
		 <div style="width:15%; height:100px; border:1px solid #dddddd; position:absolute; top:0px; right:0px; line-height:100px; text-align:center;"><i class="layui-icon layui-icon-camera" style="font-size: 80px; cursor:pointer;"></i></div>
	</div>
</body>
</html>