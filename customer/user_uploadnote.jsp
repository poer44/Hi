<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body onload="myfunction()">
<input type="hidden" value=${msg } id="msg">
<script>
function myfunction(){
	alert(document.getElementById("msg").value);
	window.location.href="information.jsp";		
}
</script>
</body>
</html>