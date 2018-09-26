<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>圈子</title>
<%@ include file="topjs.jsp" %>
</head>
<body style="width:70%;  margin:0px auto;">
	<%@ include file="top.jsp" %>
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
					<td onclick="window.location.href='${ctx}/moudle/${m.title }/'">
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