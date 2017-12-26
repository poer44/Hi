<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%if(session.getAttribute("adminid")==null){
	response.sendRedirect("../admin_login.html");
}%>
     <%@ page import="java.util.*"%>
<%@ page import="entity.Videocomment"%>
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
				<td>评论</td>
				<td>评论时间</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<%ArrayList<Videocomment> list = (ArrayList<Videocomment>) request.getAttribute("comment");
				for (int i = 0; i < Integer.parseInt(request.getAttribute("length").toString()); i++) {
					out.println("<tr><td>"+list.get(i).getUserid()+"</td><td>"+list.get(i).getContent()+"</td><td>"+list.get(i).getCommenttime()+"</td><td><a onclick=delcom('"+list.get(i).getCommentid()+"')><span class='glyphicon glyphicon-remove' style='color:red'>删除</span></a></td></tr>");
				}
			%>
		</tbody>
	</table>
		<script src="../js/jquery-1.7.2.min.js"></script>
		<script>
	function back(){
		history.go(-1);
	}
	function delcom(comid){
		var delc=confirm('确认要删除该评论吗？');
		if(delc==true){
			$.ajax({
				type : "get",
				async : true,
				url : "../"+comid+"/delcom.htm",
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