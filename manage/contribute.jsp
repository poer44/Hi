<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%if(session.getAttribute("adminid")==null){
	response.sendRedirect("../admin_login.html");
}%>
<%@ page import="entity.Submission"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../../css/jquery.pagination.css" />
</head>
<body style="margin:20px 20px 20px 20px;">
	<form role="form">
		<div class="form-group">
			<table>
				<tr>
					<td>视频分类:</td>
					<td><select class="form-control" id="ss" onchange="func();">
							<option value="0">全部</option>
							<option value="1">待审核</option>
							<option value="2">已通过</option>
							<option value="3">未通过</option>
					</select></td>
				</tr>
			</table>
		</div>
	</form>

	<input type="hidden" id="typeid" value="${typeid}">
	<table class="table table-hover">
		<thead style="background-color: #E9E9E9">
			<tr>
				<td>视频名</td>
				<td>作者</td>
				<td>文件大小</td>
				<td>视频封面</td>
				<td>视频</td>
				<td>文件格式</td>
				<td>上传时间</td>
				<td>视频类型</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
		<thead>
		<tbody>
			<%
				ArrayList<Submission> list = (ArrayList<Submission>) request
							.getAttribute("submission");
				String gettypename=null;
				String time=null;
					for (int i = 0; i < Integer.parseInt(request.getAttribute("length")
							.toString()); i++) {
						if(list.get(i).getTypeid()==1){
							gettypename="娱乐";
						}
						else if(list.get(i).getTypeid()==2){
							gettypename="游戏";
						}
						else if(list.get(i).getTypeid()==3){
							gettypename="动画";
						}
						else if(list.get(i).getTypeid()==4){
							gettypename="音乐";
						}
						else if(list.get(i).getTypeid()==5){
							gettypename="舞蹈";
						}
						time=list.get(i).getSubmissiontime().substring(0,4)+'-'+list.get(i).getSubmissiontime().substring(4,6)+'-'+list.get(i).getSubmissiontime().substring(6,8)+' '+list.get(i).getSubmissiontime().substring(8,10)+':'+list.get(i).getSubmissiontime().substring(10,12)+':'+list.get(i).getSubmissiontime().substring(12,14);
						out.print("<tr><td  width=194px>"
								+ list.get(i).getVideoname()
								+ "</td><td>"
								+ list.get(i).getUserid()
								+ "</td><td>"
								+ list.get(i).getFilesize()+"</td>");
						if(list.get(i).getState().equals("未通过")){
							out.print("<td>-</td><td>-</td><td>");
						}
						else{
							out.print("<td><img src=../../../"+ list.get(i).getImgurl()+ " width=200px;height=100px;></td><td><video controls=controls height=110px width=200px src=../../../"+ list.get(i).getFileurl() + "></td><td>");
						}
								out.print(list.get(i).getFormat().substring(6) + "</td><td>"
								+ time + "</td><td>"
								+ gettypename + "</td><td>"
								+ list.get(i).getState() + 
								"</td><td>");
						if(list.get(i).getState().equals("待审核")){
							out.print("<div class='btn-group'><button type='button' class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>未审核 <span class='caret'></span></button><ul class='dropdown-menu' role='menu'><li><a onclick=pass('"+list.get(i).getSubmissionid()+"')>通过</a></li><li><a onclick=unpass('"+list.get(i).getSubmissionid()+"')>不通过</a></li></ul></div></td></tr>");
						}
						else if(list.get(i).getState().equals("未通过")){
							out.print("-");
						}
						else if(list.get(i).getState().equals("已通过")){
							out.print("<button class='btn btn-danger' onclick=delvbys('"+list.get(i).getSubmissionid()+"')>删除</button>");
						}
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
	<script src="../../js/jquery-1.7.2.min.js"></script>
	<script src="../../js/bootstrap.min.js"></script>
	<script src="../../js/jquery.pagination.min.js"></script>
	<script>
		function func() {
			var vs = $('select  option:selected').val();
			window.location.href = "../1/" + vs + ".htm";
		}
		$().ready(function() {
			var s = $('#typeid').val();
			$('#ss').val(s);
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
					window.location.href="../"+current+"/"+$('#typeid').val()+".htm";
				}
			});
		})
		function pass(subid) {
			$.ajax({
				type : "get",
				async : true,
				url : "../../1/" + subid + "/subpass.htm",
				success : function(data) {
					window.location.reload();
					window.parent.opsuccess();
				}
			});
		}
		function unpass(subid) {
			$.ajax({
				type : "get",
				async : true,
				url : "../../0/" + subid + "/subpass.htm",
				success : function(data) {
					window.location.reload();
					window.parent.opsuccess();
				}
			});
		}
		function delvbys(str){
			alert(str);
			$.ajax({
				type : "get",
				async : true,
				url : "../../" + str + "/delvbys.htm",
				success : function(data) {
					window.location.reload();
					window.parent.opsuccess();
				}
			});
		}
	</script>
</body>

</html>