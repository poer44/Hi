<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="customer/js/jquery.min.js"></script>
<script src="static/js/JquerySession.js"></script>
<title></title>
</head>
<body onload="myfunction()">
<input type="hidden" value=${msg } id="msg">
<script>
function myfunction(){
	if(document.getElementById("msg").value=="账号或密码错误~！"){
		$.session.set("errormsg","账号或密码错误~！");
		window.location.href="user_login.html";
	}else if(document.getElementById("msg").value=="账号不存在~！"){
		$.session.set("errormsg","账号不存在~！");
		window.location.href="user_login.html";
	}else if(document.getElementById("msg").value=="手机号或获取验有误~！"){
		$.session.set("errormsg","手机号或获取验有误~！");
		window.location.href="user_login.html";
	}
	window.location.href="user_login.html";
}
</script>
</body>
</html>