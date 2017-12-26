<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%if(session.getAttribute("adminid")==null){
	response.sendRedirect("../admin_login.html");
}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/fileinput.min.css" rel="stylesheet">
</head>
<body style="margin:20px 20px 20px 20px;">
<form id="ap" action="admin_cpwd.htm" onsubmit="return check()">
<div class="form-group">
		<label for="name"><h4>管理员id：<%=session.getAttribute("adminid") %></h4><input type="hidden" value="<%=session.getAttribute("adminid") %>" name="adminid" id="adminid"></label>
		<input type="hidden" value="<%=session.getAttribute("password") %>" name="oldpasscheck"  id="oldpasscheck">
	</div>
	<div class="form-group">
		<label for="name"><h4>原密码</h4></label> <input type="password" class="form-control"  name="oldpwd" id="oldpwd" placeholder="请输入原密码">
	</div>
	<div class="form-group">
		<label for="name"><h4>新密码</h4></label> <input type="password" class="form-control"  name="newpwd"  id="newpwd" placeholder="请输入新密码">
	</div>
	<div class="form-group">
		<label for="name"><h4>确认密码</h4></label> <input type="password" class="form-control"  name="confirmpwd"  id="confirmpwd" placeholder="请输入确认密码">
	</div>
	<button class="btn btn-success" id="smt">修改</button>
	<button class="btn btn-danger" type="reset">重设</button>
	</form>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
function check(){
	if($('#oldpwd').val()==""||$('#newpwd').val()==""||$('#confirmpwd').val()==""){
		window.parent.empty();
		return false;
	}
	else if($('#newpwd').val()!=$('#confirmpwd').val()){
		window.parent.pwdce();
		return false;
	}
	else if($('#oldpwd').val()!=$('#oldpasscheck').val()){
		window.parent.oldpwde();
		return false;
	}
	else{
		$('#oldpasscheck').val($('#newpwd').val());
		return true;
	}
}
	</script>
</body>

</html>