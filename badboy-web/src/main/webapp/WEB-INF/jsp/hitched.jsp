<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顺风车</title>
</head>
<body style="width:60%;  margin:0px auto;">
	<div id="top" style="width:100%; height:350px; border:1px solid red; position:relative;">
		<img alt="" src="${ctx }/images/moudle/hitched.jpg" style="width:100%; height:350px;">
		<a href="${ctx }/user/main"><img alt="" src="${ctx }/images/settings/back.jpg" style="width:50px; height:50px; position:absolute; top:5px; left:5px;"></a>
		<a href=""><img alt="" src="${ctx }/images/settings/add.jpg" style="width:50px; height:50px; position:absolute; top:5px; right:5px;"></a>
	</div>
</body>
</html>