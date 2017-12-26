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
		<meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

		<title></title>
		<!-- Bootstrap core CSS -->
		<link href="manage/assets/css/bootstrap.css" rel="stylesheet">
		<!--external css-->
		<link href="manage/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="manage/assets/css/zabuto_calendar.css">
		<link rel="stylesheet" type="text/css" href="manage/assets/js/gritter/css/jquery.gritter.css" />
		<link rel="stylesheet" type="text/css" href="manage/assets/lineicons/style.css">

		<!-- Custom styles for this template -->
		<link href="manage/assets/css/style.css" rel="stylesheet">
		<link href="manage/assets/css/style-responsive.css" rel="stylesheet">

		<script src="manage/assets/js/chart-master/Chart.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	</head>

	<body style="margin:20px 20px 20px 20px;">

			<!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->

			<!--main content start-->
			<div class="row">
				<div class="col-lg-12 main-chart">
					<div class="row mtbox">
					
					<div class="col-md-2 col-sm-2 col-md-offset-1 box0">
							<div class="box1">
								<img src="manage/assets/img/user.png" style="width:120px;height:100px;">
								<h3 style="color:black">${countuser}</h3>
							</div>
							<p style="font-size:20px">注册用户数</p>
						</div>
						
						<div class="col-md-2 col-sm-2 box0">
							<div class="box1">
								<img src="manage/assets/img/video.png" style="width:120px;height:100px;">
								<h3 style="color:black">${countvideo}</h3>
							</div>
							<p style="font-size:20px">全站视频数</p>
						</div>
						
						<div class="col-md-2 col-sm-2 box0">
							<div class="box1">
								<img src="manage/assets/img/comment.png" style="width:120px;height:100px;">
								<h3 style="color:black">${countcomment}</h3>
							</div>
							<p style="font-size:20px">全站视频评论数</p>
						</div>
						
						<div class="col-md-2 col-sm-2 box0">
							<div class="box1">
								<img src="manage/assets/img/danmu.png" style="width:120px;height:100px;">
								<h3 style="color:black">${countbarrage}</h3>
							</div>
							<p style="font-size:20px">全站弹幕数</p>
						</div>
						
						
						
						<div class="col-md-2 col-sm-2 box0">
							<div class="box1">
								<img src="manage/assets/img/database.png" style="width:120px;height:100px;">
								<h3 style="color:black">OK!</h3>
							</div>
							<p style="font-size:20px">数据库连接正常</p>
						</div>

					</div>
					<!-- /row mt -->

					<div class="row mt">
						<!-- SERVER STATUS PANELS -->
						<a onclick="nav()" href="manage/contribute/1/1.htm" target="mainframe"><div class="col-md-4 col-sm-4 mb">
							<div class="white-panel pn donut-chart" style="background-image: url(manage/assets/img/2.jpg);background-size: cover;">
								<div class="white-header">
									<h1>新投稿</h1>
								</div>
								<div class="row">
									<h1 style="color:black;">${countsubmission}</h1>
								</div>
							</div>
							<! --/grey-panel -->
						</div></a>
						<!-- /col-md-4-->

						<a onclick="ui()" href="manage/userinfo.html" target="mainframe"><div class="col-md-4 col-sm-4 mb">
							<div class="white-panel pn donut-chart" style="background-image: url(manage/assets/img/1.jpg);background-size: cover;">
								<div class="white-header">
									<h1>本周新用户</h1>
								</div>
								<div class="row">
									<h1 style="color:black;">${countnewuser}</h1>
								</div>
							</div>
							<! --/grey-panel -->
						</div></a>
						<!-- /col-md-4 -->

						<div class="col-md-4 col-sm-4 mb" >
							<div class="white-panel pn donut-chart" style="background-image: url(manage/assets/img/3.jpg);background-size: cover;">
								<div class="white-header">
									<h1>查看用户首页</h1>
								</div>
								<div class="row">
									<a href="index.html" target="_black"><button class="btn btn-success">主页</button></a>
								</div>
							</div>
							<! --/grey-panel -->
						</div>
						<!-- /col-md-4 -->

					</div>
					<!-- /row -->

				
				</div>

			</div>


		<!-- js placed at the end of the document so the pages load faster -->
		<script src="manage/js/jquery-1.7.2.min.js"></script>
		<script src="manage/assets/js/bootstrap.min.js"></script>
		<script class="include" type="text/javascript" src="manage/js/jquery.dcjqaccordion.2.7.js"></script>
		<script src="manage/js/jquery.scrollTo.min.js"></script>
		<script src="manage/js/jquery.nicescroll.js" type="text/javascript"></script>
		<script src="manage/assets/js/jquery.sparkline.js"></script>

		<!--common script for all pages-->
		<script src="manage/assets/js/common-scripts.js"></script>

		<script type="text/javascript" src="manage/assets/js/gritter/js/jquery.gritter.js"></script>
		<script type="text/javascript" src="manage/assets/js/gritter-conf.js"></script>

		<!--script for this page-->
		<script src="manage/assets/js/sparkline-chart.js"></script>
		<script src="manage/assets/js/zabuto_calendar.js"></script>
		<script>
		function nav(){
			window.parent.ug();
		}
		function ui(){
			window.parent.ui();
		}
		</script>
		

	</body>

</html>