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
	if(document.getElementById("msg").value=="注册成功"){
		$.session.set("registermsg","注册成功，欢迎登录~");
		window.location.href="user_login.html";
	}else if(document.getElementById("msg").value=="该手机号已被注册！"){
		$.session.set("registerError","该手机号已被注册！");
		window.location.href="user_register.html";
	}else if(document.getElementById("msg").value=="手机号或验证码有误！"){
		$.session.set("registerError","手机号或验证码有误!");
		window.location.href="user_register.html";
	}
	
}
</script>
</body>
</html>