<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加模板</title>
<style type="text/css">
	tr{height:40px;}
	input{height:20px;}
</style>
<script type="text/javascript" src="${ctx }/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx }/js/layer/layer.js"></script>
<script type="text/javascript">
	function check(){
		var title=$('#title').val();
		var images=$('#images').val();
		var content=$('#content').val();

		if(title==''||title==null){
			layer.alert("标题不能为空！", {offset: '150px'});
			return false;
		}
		if(images==''||images==null){
			layer.alert("图片不能为空！", {offset: '150px'});
			return false;
		}
		if(content==''||content==null){
			layer.alert("介绍内容不能为空！", {offset: '150px'});
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div style="width:80%; margin:120px auto;">
		<form action="moudle" method="POST" enctype="multipart/form-data" onsubmit="return check();">
			<table style="width:320px; margin:0px auto;">
				<tr>
					<td style="width:20%; height:30px;">标题：</td>
					<td style="width:80%;"><input style="width:97%;" id="title" type="text" name="title" /></td>
				</tr>
				<tr>
					<td style="width:20%;">图片：</td>
					<td style="width:80%;"><input style="width:97%;" id="images" type="file" name="images" /></td>
				</tr>
				
				<tr style="height:100px;">
					<td style="width:20%;">介绍：</td>
					<td style="width:80%;"><textarea id="content" name="content" style="width:97%; height:70px;"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input style="width:100%; height:30px;" type="submit" value="提 交" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>