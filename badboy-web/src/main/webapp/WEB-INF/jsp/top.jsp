<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="top" style="width:100%; height:35px; position:relative; background-color:#5FB878;">
		<div id="logo" style="position:absolute; left:5px; line-height:35px;">LOGO</div>
		<div id="homeimg" style="position:absolute; left:85px; line-height:35px; font-size:18px;">
			<a href="${ctx }/user/main"><i class="layui-icon layui-icon-home" style="font-size: 20px; font-family:SimHei;"></i>&nbsp;主页</a>
		</div>
		<div id="photo" style="position:absolute; height:30px; margin-top:4px;">			
			<img alt="" src="${ctx }/${requestScope.user.photo }" style="width:27px; height:27px;">
		</div>
		<div id="username" style="position:absolute; height:30px; line-height:35px;">
			<span id="name">${requestScope.user.username }</span>&nbsp;&nbsp;&nbsp;&nbsp;			
		</div>		

		<div id="setting" style="height:26px; position:absolute; top:0px; margin-top:4px; cursor:pointer;">
			<i class="layui-icon layui-icon-set-sm" style="font-size: 23px;"></i>
		</div>			
	</div>
