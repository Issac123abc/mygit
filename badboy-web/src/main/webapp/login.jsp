<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<style type="text/css">
	tr{height:40px;}
	input{height:20px;}
</style>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	function check(){
		var flag=false;
		var name=$('#name').val();
		var password=$('#password').val();

		if(name==''||name==null){
			alert('账号不能为空！');
			return false;
		}
		if(password==''||password==null){
			alert('密码不能为空');
			return false;
		}
		
		$.ajax({
			url : 'user/existReg',
			type : 'post',
			async : false,
			data : {'name':name,'password':password},
			success: function(data){				
				if(data.obj=='regError'){
					alert('该用户还没有注册，请先注册！')
				}
				if(data.obj=='pwError'){
					alert('密码错误!');
				}
				if(data==''||data==null){
					flag=true;
				}
			}
		});
		return flag;
	}
</script>
</head>
<body>
	<div style="width:80%; margin:100px auto;">
		<form action="user/main" method="post" onsubmit="return check();">
			<table style="width:280px; margin:0px auto;">
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
	</div>
</body>
</html>