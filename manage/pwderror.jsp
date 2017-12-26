<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error</title>
</head>
<body onload="myfunction()">
<script>
function myfunction(){
	alert('用户名或密码错误，请检查');
	window.location.href="admin_login.html";
}
</script>
</body>
</html>