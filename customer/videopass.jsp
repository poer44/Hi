<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.util.*"%>
<%@ page import="entity.Video"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="../../../css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../../../css/jquery.pagination.css" />
</head>
<body style="margin:0px 20px 20px 20px;">

<table class="table table-hover">
		<thead style="background-color: #98C9EE">
			<tr>
				<td>视频名</td>
				<td>文件大小</td>
				<td>视频封面</td>
				<td>视频</td>
				<td>文件格式</td>
				<td>上传时间</td>
				<td>视频类型</td>
				<td>操作</td>
			</tr>
		<thead>
		<tbody>
			<%
				ArrayList<Video> list = (ArrayList<Video>) request.getAttribute("video");
					String gettypename=null;
					String time=null;
					for (int i = 0; i < Integer.parseInt(request.getAttribute("length").toString()); i++) {
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
						time=list.get(i).getUptime().substring(0,4)+'-'+list.get(i).getUptime().substring(4,6)+'-'+list.get(i).getUptime().substring(6,8)+' '+list.get(i).getUptime().substring(8,10)+':'+list.get(i).getUptime().substring(10,12)+':'+list.get(i).getUptime().substring(12,14);
						out.print("<tr><td>"+ list.get(i).getVideoname()
								+ "</td><td>"+ list.get(i).getFilesize()
								+"</td><td><img src=../../../../"+ list.get(i).getImgurl()+ " width=200px;height=100px;></td><td><video controls=controls height=110px width=200px src=../../../../"+ list.get(i).getFileurl() + "></td><td>"+list.get(i).getFormat().substring(6) + "</td><td>"
								+ time + "</td><td>"+ gettypename + 
								"</td><td><a onclick=delvpass('"+list.get(i).getVideoid()+"')><span class='glyphicon glyphicon-remove' style='color:red;'>删除</span></a></td></tr>");
					}
					
			%>
		</tbody>
	</table>
	<div style="text-align: center">
		<input type="hidden" value="${pages}" id="pages">
		<input type="hidden" value="${pageNum}" id="pageNum">
		<input type="hidden" value="${typeid}" id="typeid">
		<input type="hidden" value="${userid}" id="userid">
		<div id="pagination3"></div>
	</div>
<script src="../../../js/jquery.min.js"></script>
<script src="../../../js/jquery.pagination.min.js"></script>
<script>
	$().ready(function(){
		var s=$('#typeid').val();
		$('#ss').val(s);
		var pagen=parseInt($('#pageNum').val());
		var pages=parseInt($('#pages').val());
		$("#pagination3").pagination({
			currentPage: pagen,
			totalPage: pages,
			isShow: true,
			count: 3,
			homePageText: "首页",
			endPageText: "尾页",
			prevPageText: "上一页",
			nextPageText: "下一页",
			callback: function(current) {
				window.location.href="../../../"+current+"/"+$('#typeid').val()+"/"+$('#userid').val()+"/submission.htm";
			}
		});
	})
	function delvpass(videoid){
		var delc=confirm('确认要删除视频吗？视频的评论弹幕也将一并删除！');
		if(delc==true){
			$.ajax({
				type : "get",
				async : true,
				url : "../../../"+videoid+"/delvideo.htm",
				success : function() {
					window.location.reload();
				}
			});
			
		}
		
	}
</script>
</body>
</html>