<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="java.util.*"%>
<%@ page import="entity.Video"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../../css/jquery.pagination.css" />
</head>
<body style="margin:5px 20px 20px 20px;">
<form role="form">
  <div class="form-group">
    <table><tr><td>视频分类: </td><td>
    <select class="form-control"  id="ss" onchange="func();">
      <option value="0">全部</option>
      <option value="1">娱乐</option>
      <option value="2">游戏</option>
      <option value="3">动画</option>
      <option value="4">音乐</option>
      <option value="5">舞蹈</option>
    </select>
    </td>
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
				<td>大小</td>
				<td>封面</td>
				<td>视频</td>
				<td>格式</td>
				<td>播放量</td>
				<td>上传时间</td>
				<td>类型</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<%
				ArrayList<Video> list = (ArrayList<Video>) request
						.getAttribute("video");
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
					time=list.get(i).getUptime().substring(0,4)+'-'+list.get(i).getUptime().substring(4,6)+'-'+list.get(i).getUptime().substring(6,8)+' '+list.get(i).getUptime().substring(8,10)+':'+list.get(i).getUptime().substring(10,12)+':'+list.get(i).getUptime().substring(12,14);
					out.println("<tr><td width=194px>"
							+ list.get(i).getVideoname()
							+ "</td><td>"
							+ list.get(i).getAuthor()
							+ "</td><td>"
							+ list.get(i).getFilesize()
							+ "</td><td><img src=../../../"
							+ list.get(i).getImgurl()
							+ " width=200px;height=90px;></td><td><video controls=controls height=110px width=200px src=../../../"
							+ list.get(i).getFileurl() + "></td><td>"
							+ list.get(i).getFormat().substring(6) + "</td><td>"
							+ list.get(i).getPlay() + "</td><td width='100px'>"
							+ time + "</td><td>"
							+ gettypename + "</td><td><a onclick=delvideo('"+list.get(i).getVideoid()+"')><span class='glyphicon glyphicon-remove' style='color:red'>删除</span></a></td></tr>");
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
	<script type="text/javascript" src="../../js/jquery-1.7.2.min.js"></script>
	<script src="../../js/jquery.pagination.min.js"></script>
	<script>
	function delvideo(videoid){
		var delc=confirm('确认要删除视频吗？视频的评论弹幕也将一并删除！');
		if(delc==true){
			$.ajax({
				type : "get",
				async : true,
				url : "../../"+videoid+"/delvideo.htm",
				success : function(data) {
					window.location.reload();
					window.parent.delu();
				}
			});
		}
	}
	function func(){
		 var vs = $('select  option:selected').val(); 
		 window.location.href="../1/"+vs+".htm";
	}
	$().ready(function(){
		var s=$('#typeid').val();
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
	</script>
</body>

</html>