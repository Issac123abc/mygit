<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${ctx }/js/layui/css/layui.css"  media="all">
<script type="text/javascript" src="${ctx }/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx }/js/layui/layui.js"></script>
<style type="text/css">
	#homeimg a:hover{color:white;}
	td{ width:20%; height:200px; border:0.1px solid gray; text-align: center;}
	.setting td{height:15px; border:0px;}
	.setting a{text-decoration: none;}
</style>
<script type="text/javascript">
	$(function(){
		var bodyWidth=$(document.body).width();
		var width=$('#name').outerWidth();
		$('#photo').css('left',bodyWidth*0.6+'px');
		$('#username').css('left',(bodyWidth*0.6+40)+'px');
		$('#setting').css('left',(bodyWidth*0.6+40+width+20)+'px');
		$('#setting').hover(function() {
			$('#setting').append('<table style="width:120px; background-color:white;" class="setting">'+
			'<tr><td><a href="${ctx }/user/edituser">修改资料</a></td></tr><tr><td><a href="${ctx }/user/logout">退出登录</a></td></tr></table>');
		},function(){
			$('#setting').find(".setting").remove();
		});
	});

	
</script>