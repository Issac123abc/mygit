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
<body style="width:60%;  margin:0px auto;">
	<%@ include file="top.jsp" %>
	<div style="width:100%; height:150px; margin:20px auto; position:relative; font-size:20px;">
		<div style="width:150px; height:150px; position:absolute;"><img alt="头像" src="${ctx }/${requestScope.user.photo}" style="width:150px; height:150px;"></div>
		<div style="position:absolute; top:10px; left:175px; font-size:25px;">${requestScope.user.username}</div>
		<div style="position:absolute; bottom:2px; left:210px;"><a href="">动态</a></div>
		<div style="position:absolute; bottom:2px; left:280px;"><a href="">我的动态</a></div>
		<div style="position:absolute; bottom:2px; left:390px;"><a href="">消息</a></div>
	</div>
	<div id="content" style="width:100%; height:100px; border:1px solid #dddddd; margin:20px auto 0px; position:relative;">
		 <div style="width:85%; "><textarea placeholder="请输入内容" class="layui-textarea"></textarea></div>
		 <div style="width:15%; height:100px;  position:absolute; top:0px; right:0px; line-height:100px; text-align:center;"><i class="layui-icon layui-icon-camera" style="font-size: 80px; cursor:pointer;"></i></div>
	</div>
	<div style="width:100%; margin:0px auto; border:1px solid #dddddd; border-top:0px; position:relative;">
		<div style="width:85%; height:40px; padding-left:15px;">
			<i class="layui-icon layui-icon-face-smile-b" style="font-size:25px; line-height:40px; cursor:pointer;"></i>
		</div>
		<div style="width:15%; position:absolute; top:5px; right:0px; text-align:center;">
			<button class="layui-btn" style="width:70%; height:30px; line-height:30px;">发表</button>
		</div>	
	</div>
	<div style="margin-top:20px;border:1px solid #dddddd;">
		<div style="margin-left:15px; margin-top:15px; position:relative;">
			<div style="width:50px; height:50px;"><img alt="" src="${ctx }/images/headphoto/库洛洛.jpg" style="width:50px; height:50px; border-radius:25px;"></div>
			<div style="position:absolute; top:5px; left:60px;">Issac123</div>
			<div style="position:absolute; bottom:5px; left:60px; color:gray;">2018-9-26 11:30</div>
		</div>
		<div>
			<div style="margin-left:15px; margin-top:10px; position:relative;">这是第一条动态</div>
			<div></div>
		</div>
		<div style="margin:10px auto; position:relative;">
			<div style="margin-left:15px;">浏览  次</div>
			<div style="position:absolute; bottom:-1px; right:120px;"><i class="layui-icon layui-icon-praise" style="font-size:22px; font-weight:bold; cursor:pointer;"></i></div>
			<div style="position:absolute; bottom:1px; right:70px;"><i class="layui-icon layui-icon-reply-fill" style="font-size:22px; color:gray;  cursor:pointer;"></i></div>
			<div style="position:absolute; bottom:1px; right:20px;"><i class="layui-icon layui-icon-star" style="font-size:22px; font-weight:bold; cursor:pointer;"></i></div>
		</div>	
	</div>
	
	
	
</body>
</html>