<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%if(session.getAttribute("adminid")==null){
	response.sendRedirect("../admin_login.html");
}%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上传视频</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/upload.js"></script>
</head>
<body style="margin:20px 0 0 20px;">
<div >
<!--登陆弹框-->
		<%
   		  Object message = request.getAttribute("msg");
    	 if(message!=null && !"".equals(message)){
  			%>
     	 <script type="text/javascript">
       	   alert("<%=message%>");
    	 </script>
  		<%} %>
  	<!--登陆弹框结束-->
<form method="post"  id="add" enctype="multipart/form-data" class="form-horizontal col-sm-8" role="form">
	<input type="hidden" name="manageid" value=<%=session.getAttribute("adminid") %>>
	<div class="form-group">
			<label for="name" class="control-label col-sm-2">视频标题:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="title" placeholder="请输入视频标题" required="required" >
			</div>
	</div>
	<div class="form-group">
		<label for="name" class="control-label col-sm-2">所属类别:</label>
			<div class="col-sm-10">
				<select id="mvtype"  name="mvtype" class="form-control">
					<option value="1">娱乐</option>
					<option value="2">游戏</option>
					<option value="3">动画</option>
					<option value="4">音乐</option>
					<option value="5">舞蹈</option>
				</select>
			</div>
	</div>
	
	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">视频:</label>
			<div class="col-sm-10">
			<video src="" id="song_url" controls="controls" class="form-control" style="width:500px;height:300px;" oncanplaythrough="getlength(this)"></video>
			<input type="text" id="filesize" name="filesize" class="form-control" readonly="readonly" >
			<input type="text" id="format" name="format" class="form-control" readonly="readonly" >
			<input type="text" id="videolength" name="videolength" class="form-control" readonly="readonly" >
			</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">请选择视频:</label>
			<div class="col-sm-10">
				<div class="form-group">
					<div class="col-sm-10">
						<input type="file" id="songfile" name="songfile"  accept="video/*" class="btn btn-default" onchange="showSong(this)">
					</div>
				</div>
			</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<input type="submit" id="addsongbtn" name="addsongbtn" class="btn btn-default"  onclick="judge()">
		</div>
	</div>
	</form>
</div>
</body>
</html>