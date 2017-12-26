<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%if(session.getAttribute("adminid")==null){
	response.sendRedirect("../admin_login.html");
}%>
 <%@ page import="java.util.*"%>
<%@ page import="entity.Video"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../css/jquery.pagination.css" />
</head>
<body style="margin:20px 20px 20px 20px;">
	<table class="table table-hover">
		<thead style="background-color: #E9E9E9">
			<tr>
				<td>视频编号</td>
				<td>视频名</td>
				<td>视频</td>
				<td>查看评论</td>
				<td>查看弹幕</td>
			</tr>
		</thead>
		<tbody>
			<%ArrayList<Video> list = (ArrayList<Video>) request.getAttribute("video");
				for (int i = 0; i < Integer.parseInt(request.getAttribute("length")
						.toString()); i++) {
					out.println("<tr><td>"
							+ list.get(i).getVideoid()
							+ "</td><td>"
							+ list.get(i).getVideoname()
							+ "</td><td><video controls=controls height=110px width=200px src=../../"
							+ list.get(i).getFileurl() + "></td><td>"
							+ "<a href=../"+list.get(i).getVideoid()+"/commentview.htm><span class='glyphicon glyphicon-comment'>查看评论</span></a></td><td><a href=../"+list.get(i).getVideoid()+"/barrageview.htm><span class='glyphicon glyphicon-edit'>查看弹幕</span></a></td></tr>");
				}
			%>
		</tbody>
	</table>
	<div style="text-align: center">
		<input type="hidden" value="${pages}" id="pages">
		<input type="hidden" value="${pageNum}" id="pageNum">
		<input type="hidden" value="${typeid}" id="typeid">
		<div id="pagination3"></div>
	</div>
<script src="../js/jquery-1.7.2.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.pagination.min.js"></script>
<script>

		$().ready(function() {
			var pagen=parseInt($('#pageNum').val());
			var pages=parseInt($('#pages').val());
			$("#pagination3").pagination({
				currentPage: pagen,
				totalPage: pages,
				isShow: true,
				count: 7,
				homePageText: "首页",
				endPageText: "尾页",
				prevPageText: "上一页",
				nextPageText: "下一页",
				callback: function(current) {
					window.location.href=current+".htm";
				}
			});
		})
	</script>
</body>
</html>