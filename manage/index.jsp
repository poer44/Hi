<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%if(session.getAttribute("adminid")==null){
	response.sendRedirect("../admin_login.html");
}%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>Hi视频后台管理</title>

<!-- Bootstrap core CSS -->
<link href="manage/assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="manage/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="manage/assets/css/zabuto_calendar.css">
<link rel="stylesheet" type="text/css"
	href="manage/assets/js/gritter/css/jquery.gritter.css" />
<link rel="stylesheet" type="text/css" href="manage/assets/lineicons/style.css">

<!-- Custom styles for this template -->
<link href="manage/assets/css/style.css" rel="stylesheet">
<link href="manage/assets/css/style-responsive.css" rel="stylesheet">

<script src="manage/assets/js/chart-master/Chart.js"></script>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body style="margin:20px 20px 20px 20px;">

	<section id="container">
		<!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
		<!--header start-->
		<header class="header black-bg">
			<div class="sidebar-toggle-box">
				<div class="fa fa-bars tooltips" data-placement="right"
					data-original-title="显示/隐藏导航"></div>
			</div>
			<!--logo start-->
			<a onclick="home()" class="logo" href="adminhome.htm" target="mainframe"><b>Hi视频后台</b></a>
			<!--logo end-->

			<div class="top-menu">
				<ul class="nav pull-right top-menu">
					<li><a class="logout" href="manage/changepwd.jsp" target="mainframe">修改密码</a></li>
					<li><a class="logout" href="adminlogout.htm">退出登录</a></li>
				</ul>
			</div>
		</header>
		<!--header end-->

		<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
		<!--sidebar start-->
		<aside>
			<div id="sidebar" class="nav-collapse ">
				<!-- sidebar menu start-->
				<ul class="sidebar-menu" id="nav-accordion">

					<p class="centered">
						<img src="manage/assets/img/user_default.png" class="img-circle"
							width="60">
					</p>
					<h5 class="centered">管理员${admin.adminid}</h5>
					<%session.setAttribute("password", request.getAttribute("password")); %>
					<li class="mt"><a class="active" href="adminhome.htm" target="mainframe" id="red">
							<i class="fa fa-dashboard"></i> <span>主页</span>
					</a></li>
					<li class="sub-menu"><a href="javascript:;" id="vm"> <i
							class="fa fa-desktop"></i> <span>视频管理</span>
					</a>
						<ul class="sub">
							<li><a href="manage/upload.jsp" target="mainframe">上传视频</a></li>
							<li><a href="manage/videoinfo/1/0.htm" target="mainframe">视频信息</a></li>
							<li><a href="manage/contribute/1/0.htm" target="mainframe" id="ug">用户投稿</a></li>
							<li><a href="manage/videocomment/1.htm" target="mainframe">视频评论与弹幕</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;" id="um"> <i
							class="fa fa-cogs"></i> <span>用户管理</span>
					</a>
						<ul class="sub">
							<li><a href="manage/userinfo.html" target="mainframe" id="ui">用户信息</a></li>
						</ul></li>

				</ul>
				<!-- sidebar menu end-->
			</div>
		</aside>
		<!--sidebar end-->

		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<iframe class="col-lg-12 main-chart" src="adminhome.htm"
						style="height: 720px; width: 100%" name="mainframe"></iframe>
				</div>
			</section>
		</section>

		<!--main content end-->
		<!-- js placed at the end of the document so the pages load faster -->
		<script src="manage/js/jquery-1.7.2.min.js"></script>
		<script src="manage/js/bootstrap.min.js"></script>
		<!--手风琴js-->
		<script class="include" type="text/javascript"
			src="manage/js/jquery.dcjqaccordion.2.7.js"></script>
		<script src="manage/js/jquery.scrollTo.min.js"></script>
		<!--滚动条js-->
		<script src="manage/js/jquery.nicescroll.js" type="text/javascript"></script>

		<!--common script for all pages-->
		<script src="manage/assets/js/common-scripts.js"></script>
		<!--弹出框js-->
		<script type="text/javascript"
			src="manage/assets/js/gritter/js/jquery.gritter.js"></script>
		<script type="text/javascript" src="manage/assets/js/gritter-conf.js"></script>

		<script type="text/javascript">
			$(document).ready(function() {
				$('#red').click();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the notification
					title: '欢迎使用Hi视频后台管理!',
					// (string | mandatory) the text inside the notification
					text: '您可以在此页面管理视频信息、用户信息、用户投稿、视频评论、视频弹幕。',
					// (string | optional) the image to display on the left
					image: 'manage/assets/img/white.png',
					// (bool | optional) if you want it to fade out on its own or just sit there
					sticky: true,
					// (int | optional) the time you want it to be alive for before fading out
					time: '',
					// (string | optional) the class name you want to apply to that specific message
					class_name: 'my-sticky-class'
				});
				return false;
			});
			function ug() {
				$('#vm').click();
				$('#ug').click();
			}
			function ui() {
				$('#um').click();
				$('#ui').click();
			}
			function delu(){
				$(".gritter-item-wrapper").remove();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the notification
					title: '删除成功!',
					// (string | mandatory) the text inside the notification
					text: '该记录及其所有关联数据均已被删除。',
					// (string | optional) the image to display on the left
					image: 'manage/assets/img/Delete.png',
					// (bool | optional) if you want it to fade out on its own or just sit there
					sticky: true,
					// (int | optional) the time you want it to be alive for before fading out
					time: '',
					// (string | optional) the class name you want to apply to that specific message
					class_name: 'my-sticky-class'
				});
			}
			function delufail(){
				$(".gritter-item-wrapper").remove();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the notification
					title: '删除失败!',
					// (string | mandatory) the text inside the notification
					text: '删除失败，请联系管理员',
					// (string | optional) the image to display on the left
					image: 'manage/assets/img/error.png',
					// (bool | optional) if you want it to fade out on its own or just sit there
					sticky: true,
					// (int | optional) the time you want it to be alive for before fading out
					time: '',
					// (string | optional) the class name you want to apply to that specific message
					class_name: 'my-sticky-class'
				});
			}
			function danhang(){
				$(".gritter-item-wrapper").remove();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the notification
					title: '无效操作!',
					// (string | mandatory) the text inside the notification
					text: '只能选择一行数据进行删除',
					// (string | optional) the image to display on the left
					image: 'manage/assets/img/error.png',
					// (bool | optional) if you want it to fade out on its own or just sit there
					sticky: true,
					// (int | optional) the time you want it to be alive for before fading out
					time: '',
					// (string | optional) the class name you want to apply to that specific message
					class_name: 'my-sticky-class'
				});
			}
			function youxiao(){
				$(".gritter-item-wrapper").remove();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the notification
					title: '无效操作!',
					// (string | mandatory) the text inside the notification
					text: '请选择有效数据',
					// (string | optional) the image to display on the left
					image: 'manage/assets/img/error.png',
					// (bool | optional) if you want it to fade out on its own or just sit there
					sticky: true,
					// (int | optional) the time you want it to be alive for before fading out
					time: '',
					// (string | optional) the class name you want to apply to that specific message
					class_name: 'my-sticky-class'
				});
			}
			function empty(){
				$(".gritter-item-wrapper").remove();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the notification
					title: '无效操作!',
					// (string | mandatory) the text inside the notification
					text: '请填写完整',
					// (string | optional) the image to display on the left
					image: 'manage/assets/img/error.png',
					// (bool | optional) if you want it to fade out on its own or just sit there
					sticky: true,
					// (int | optional) the time you want it to be alive for before fading out
					time: '',
					// (string | optional) the class name you want to apply to that specific message
					class_name: 'my-sticky-class'
				});
			}
			function pwdce(){
				$(".gritter-item-wrapper").remove();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the notification
					title: '无效操作!',
					// (string | mandatory) the text inside the notification
					text: '确认密码与新密码输入不一致，请检查',
					// (string | optional) the image to display on the left
					image: 'manage/assets/img/error.png',
					// (bool | optional) if you want it to fade out on its own or just sit there
					sticky: true,
					// (int | optional) the time you want it to be alive for before fading out
					time: '',
					// (string | optional) the class name you want to apply to that specific message
					class_name: 'my-sticky-class'
				});
			}
			function oldpwde(){
				$(".gritter-item-wrapper").remove();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the notification
					title: '修改失败!',
					// (string | mandatory) the text inside the notification
					text: '请输入正确的原密码',
					// (string | optional) the image to display on the left
					image: 'manage/assets/img/error.png',
					// (bool | optional) if you want it to fade out on its own or just sit there
					sticky: true,
					// (int | optional) the time you want it to be alive for before fading out
					time: '',
					// (string | optional) the class name you want to apply to that specific message
					class_name: 'my-sticky-class'
				});
			}
			function cpwdsuccess(){
				$(".gritter-item-wrapper").remove();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the notification
					title: '成功!',
					// (string | mandatory) the text inside the notification
					text: '修改密码成功',
					// (string | optional) the image to display on the left
					image: 'manage/assets/img/success.png',
					// (bool | optional) if you want it to fade out on its own or just sit there
					sticky: true,
					// (int | optional) the time you want it to be alive for before fading out
					time: '',
					// (string | optional) the class name you want to apply to that specific message
					class_name: 'my-sticky-class'
				});
			}
			function opsuccess(){
				$(".gritter-item-wrapper").remove();
				var unique_id = $.gritter.add({
					// (string | mandatory) the heading of the notification
					title: '成功!',
					// (string | mandatory) the text inside the notification
					text: '操作成功完成',
					// (string | optional) the image to display on the left
					image: 'manage/assets/img/success.png',
					// (bool | optional) if you want it to fade out on its own or just sit there
					sticky: true,
					// (int | optional) the time you want it to be alive for before fading out
					time: '',
					// (string | optional) the class name you want to apply to that specific message
					class_name: 'my-sticky-class'
				});
			}
			function removeall(){
				$(".gritter-item-wrapper").remove();
			}
			function home(){
				$("#red").click();
			}
		</script>
</body>

</html>