<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="java.util.*"%>
<%@ page import="entity.Barrage"%>
<%if(session.getAttribute("adminid")==null){
	response.sendRedirect("../admin_login.html");
}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="margin:20px 20px 20px 20px;">
<button class="btn btn-primary" onclick="back()"><span class="glyphicon glyphicon-chevron-left">后退</span></button>
	<table class="table table-hover">
		<thead>
			<tr>
				<td>用户id</td>
				<td>弹幕</td>
				<td>弹幕相对视频时间</td>
				<td>弹幕发表时间</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
	<%ArrayList<Barrage> list = (ArrayList<Barrage>) request.getAttribute("barrage");
				for (int i = 0; i < Integer.parseInt(request.getAttribute("length").toString()); i++) {
					out.println("<tr><td>"+list.get(i).getUserid()+"</td><td>"+list.get(i).getContent()+"</td><td>"+list.get(i).getVideotime()+"</td><td>"+list.get(i).getBarragetime()+"</td><td><a onclick=delbag('"+list.get(i).getBarrageid()+"')><span class='glyphicon glyphicon-remove' style='color:red'>删除</span></a></td></tr>");
				}
			%>
		</tbody>
	</table>
	<script src="../js/jquery-1.7.2.min.js"></script>
	<script>
	function back(){
		history.go(-1);
	}
	function delbag(bagid){
		var delc=confirm('确认要删除该弹幕吗？');
		if(delc==true){
			$.ajax({
				type : "get",
				async : true,
				url : "../"+bagid+"/delbag.htm",
				success : function(data) {
					window.location.reload();
					window.parent.delu();
				}
			});
		}
	}
	</script>
</body>
</html>