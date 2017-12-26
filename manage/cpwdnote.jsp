<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error</title>
</head>
<body onload="myfunction()">
<script style="margin:20px 20px 20px 20px;">
function myfunction(){
	window.parent.cpwdsuccess();
	window.location.href="changepwd.jsp";
}
</script>
</body>
</html>