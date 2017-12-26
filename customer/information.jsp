<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hi~Information</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/Information.css">
<link rel="stylesheet" href="font/iconfont.css">
<link rel="stylesheet" type="text/css" href="css/banner.css">
<link rel="stylesheet" href="css/toastr.css" />
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="laydate/laydate.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/restful.js"></script>
<script type="text/javascript" src="js/tip.js" ></script>
<script type="text/javascript" src="js/toastr.js" ></script>
<style type="text/css">
.toast-top-right{
	margin-top:50px;
}
#personalsign{
	word-wrap:break-word;
	word-break:break-all;
	text-align:center;
}
#personalsign span font{
	color:#bdd7f2;
}
.Intopli a{
	text-decoration:none;
}
#container3 a{
	text-decoration:none;
	margin-left:43%;
}
</style>
</head>
<body>
<input type="hidden" id="userinfo">
	<div id="header">
		<!-- 最顶部 -->
		<div class="header-top"
			style="background-color: #fff; border-bottom: 2px solid gainsboro;">
			<!--顶部栏-->
			<div class="auto-width">
				<h1 class="logo fl">
					<a href="../index.html"><img src="images/logo1.png"
						alt="图标：hihi"></a>
				</h1>
				<!--logo图标-->
				<div class="header-guide fr" style="margin-top: 7px;">
					<!--搜索栏-->
					<form action="search.html" method="get">
						<!--搜索表单-->
						<input style="height: 32px;color:black;" type="text" autocomplete="off" class="fl search-text"
							name="query" placeholder="Search...">
						<!--搜索框-->
						<button class="fl search-btn">
							<!--搜索按钮-->
							<i class="iconfont icon-search"></i>
						</button>
					</form>
					<div class="search-feedback">
						<!--搜索时显示的下拉框（表示最近最热的搜索）-->
						<span class="search-hot">今日热搜</span>
						<div class="search-menu" id="searchContent"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<div class="InWelcome">
		<img src="images/welcom.PNG" style="padding: 20px 350px;">
	</div>
	<div class="Inmain">
		 <div id="Intentity1">
			<div class="Top">
				<ul class="Intop">
					<li class="Intopli"><a style="font-size: 20px;">个人资料</a></li>
				</ul>
			</div>
			<div class="container1">
				<!-- 基本信息修改的DIV -->
				<a style="font-size: 17px; padding: 20px 20px; color: #999 ;text-align:center;">基本信息修改<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --></a>
				<hr style="width: 90%; margin: auto;">
				<div class="con4" style="text-align:center;">
					<div class="mark1"><img id="chose" src="../${user.imgurl}" style="width: 100px;height: 100px;border-radius:50px;border:2px solid grey;"/></div>
					<br>
					<div style="height:80px;text-align:center;margin-left:80px;">
					<span class="btn upload">
					<p style="margin-top:-7px;">选择头像</p>
					<input type="file" id="myUpload" name="file" class="upload_pic" accept="image/*" onchange="getImgURL(this)">
					</span>
					<span class="btn upload" style="margin-top: 10px;">
					<p style="margin-top:-7px;">保存</p>
					<input type="button"  id="bc021" class="upload_pic" onclick="return ajaxFileUpload();"/>
					</span>
					</div>
				</div>

				<div class="page__demo">
					<div class="main-container page__container">
						<div class="page__section">
							<br> <label class="field field_type1"> 
							<input class="field__input" value="${user.username }" id="username" placeholder="输入昵称">
							<span class="field__label" id="checkusername">输入昵称</span>
							</label><br>
							<br>
							<br> <label class="field field_type1">
							 <select class="field__input" id="sex">
									<option id="male" value="男" >男</option>
									<option id="female" value="女" >女</option>
									<option id="sexsecrecy" value="保密" selected="selected">保密</option>
							</select>
							</label><br>
							<input type="hidden" id="usersex" value="${user.sex}">
							<br>
							<br>
							<br><label class="field field_type1"> 
							<input class="field__input" id="demo" value="${user.birthday}" id="birthday" placeholder="输入生日"> 
							<span class="field__label">输入生日</span>
							</label><br>
							<br>
							<br>
							<br> 
							<label class="field field_type1"> 
							<input class="field__input" id="address" placeholder="输入地址" value="${user.address}" >
								<span class="field__label">设置您的地址- ( ゜- ゜)つロ</span>
							</label><br>
							<br>
							<br>
							<br> <label class="field field_type1"> 
							<select class="field__input" id="emotion">
									<option id="single" value="单身" >单身</option>
									<option id="married" value="已婚" >已婚</option>
									<option id="emotionsecrecy" value="保密"  selected="selected">保密</option>
							</select>
							</label><br>
							<input type="hidden" id="useremotion" value="${user.emotion}">
							<br>
							<br>
							<br> <label class="field field_type1"> 
							<input class="field__input" value="${user.email }" placeholder="输入邮箱" id="email" > 
							<span class="field__label" id="checkemail">输入邮箱</span>
							</label><br>
							<br>
							<br>
							<br> <label class="field field_type1"> 
							<input class="field__input" id="sign" placeholder="输入个性签名" value="${user.sign}" >
							<span class="field__label">设置您的签名- ( ゜- ゜)つロ</span>
							</label>
						</div>
						<button type="button" id="modify_user"  class="modify_btn" >点击修改</button>
					</div>
				</div>
			</div>
		</div> 

	 	<div id="Intentity11">
			<div class="Top">
				<ul class="Intop">
					<li class="Intopli"><a style="font-size: 20px;">个人资料</a></li>
				</ul>
			</div>
		<div class="container1">
				<div class="page__demo">
					<div class="main-container page__container">
						<div class="page__section">
						<!-- 密码修改的DIV -->
						<a style="font-size: 17px; padding: 20px 20px; color: #999">密码修改</a>
						<hr style="width: 90%; margin: auto;">
							<br> <label class="field field_type1"> 
							<input class="field__input" type="password"  id="oldpwd" placeholder="请输入原密码">
							<span class="field__label" id="checkoldpwd">原密码</span>
							</label><br>
							<br>
							<br> <label class="field field_type1"> 
							<input class="field__input" type="password"  id="pwd" placeholder="请输入新密码">
							<span class="field__label" id="checkpwd">新密码</span>
							</label><br>
							<br>
							<br> <label class="field field_type1"> 
							<input class="field__input" type="password"  id="repwd" placeholder="请输入确认密码">
							<span class="field__label" id="checkrepwd">确认密码</span>
							</label><br>
							<br>
							
						</div>
						<button type="button" id="modify_pwd"  class="modify_btn" >修改密码</button>
					</div>
				</div>
			</div>
		</div >

		<div id="Intentity2">
			<!--上传投稿的DIV -->
			<div class="Top">
				<ul class="Intop">
					<li class="Intopli" id="Intoplili1"><a
						style="font-size: 20px;">开始投稿</a></li>
					<li class="Intopli" id="Intoplili2"><a
						style="font-size: 20px;">管理投稿</a></li>
					<li class="Intopli" id="Intoplili3"><a
						style="font-size: 20px;">管理评论</a></li>
					<li class="Intopli" id="Intoplili4"><a
						style="font-size: 20px;">管理弹幕</a></li>
				</ul>
			</div>
			<div class="container2" id="container2">
				<div class="main-container page__container">
				<div class="page__section">
				<form method="post"  id="add" enctype="multipart/form-data" >
						<label class="field field_type1"> 
						<input type="hidden" id="userid" name="userid" class="field__input" value=${user.userid }> 
						</label><br>	
							<label class="field field_type1"> 
							<input type="text" class="field__input" name="videoname" placeholder="请输入视频标题" required=""> 
							<span class="field__label">请输入视频标题</span>
							</label><br>
							<br>
							<a style="font-size: 13px;position: absolute;">所属类别:</a><br><br>
							<label class="field field_type1"> 
							<select id="typeid"  name="typeid"  class="field__input">
								<option value="1">娱乐</option>
								<option value="2">游戏</option>
								<option value="3">动画</option>
								<option value="4">音乐</option>
								<option value="5">舞蹈</option>
							</select>
							</label><br>
							<br>
							<br>
							<label class="field field_type1"> 
							<input type="file" id="songfile" name="songfile" class="field__input" accept="video/*" class="btn btn-default" onchange="showSong(this)">
							</label><br>
							<br>
						
							<video src="" id="song_url" controls="controls" class="field__input"  oncanplaythrough="getlength(this)"  width="576" height="291" ></video>
							文件大小:<input type="text" id="filesize" name="filesize"  readonly="readonly" >
							文件类型:<input type="text" id="format" name="format"  readonly="readonly" >
							文件长度:<input type="text" id="videolength" name="videolength"  readonly="readonly" >
							<br>
						<input type="submit" class="modify_btn" value="点击上传" onclick="judge()"/>
						</form>
				</div>
				</div>
			</div>
		</div>


		<div id="Intentity3">
			<!--管理自己投稿的DIV -->
			<div class="Top">
				<ul class="Intop">
					<li class="Intopli" id="Intoplilili1"><a
						style="font-size: 20px;">开始投稿</a></li>
					<li class="Intopli" id="Intoplilili2"><a
						style="font-size: 20px;">管理投稿</a></li>
					<li class="Intopli" id="Intoplilili3"><a
						style="font-size: 20px;">管理评论</a></li>
					<li class="Intopli" id="Intoplilili4"><a
						style="font-size: 20px;">管理弹幕</a></li>
				</ul>
			</div>
			<div class="container3" id="container3">
				<a style="font-size: 14px; padding: 10px 10px; color: #0080C4">未通过的投稿</a>
				<iframe  frameborder="no" class="focus" src="1/0/${user.userid}/submission.htm" scrolling="no"></iframe>
				<br><br> <a style="font-size: 14px; padding: 10px 10px; color: #0080C4">审核中的投稿</a>
				<iframe  frameborder="no" class="focus" src="1/2/${user.userid}/submission.htm" scrolling="no"></iframe>
				<br><br> <a style="font-size: 14px; padding: 10px 10px; color: #0080C4">已通过的投稿</a>
				<iframe   frameborder="no" class="focued" src="1/1/${user.userid}/submission.htm" scrolling="no"></iframe><br>
				
			</div>
		</div>

		<div id="Intentity4">
			<!--管理视频评论的DIV -->
			<div class="Top">
				<ul class="Intop">
					<li class="Intopli" id="Intoplililili1"><a
						style="font-size: 20px;">开始投稿</a></li>
					<li class="Intopli" id="Intoplililili2"><a
						style="font-size: 20px;">管理投稿</a></li>
					<li class="Intopli" id="Intoplililili3"><a
						style="font-size: 20px;">管理评论</a></li>
					<li class="Intopli" id="Intoplililili4"><a
						style="font-size: 20px;">管理弹幕</a></li>
				</ul>
			</div>
			<iframe  frameborder="no" class="container4" id="container4"
				src="VideocommentIframe.html"></iframe>
		</div>

		<div id="Intentity5">
			<!--管理视频弹幕的DIV -->
			<div class="Top">
				<ul class="Intop">
					<li class="Intopli" id="Intoplilililili1"><a
						style="font-size: 20px;">开始投稿</a></li>
					<li class="Intopli" id="Intoplilililili2"><a
						style="font-size: 20px;">管理投稿</a></li>
					<li class="Intopli" id="Intoplilililili3"><a
						style="font-size: 20px;">管理评论</a></li>
					<li class="Intopli" id="Intoplilililili4"><a
						style="font-size: 20px;">管理弹幕</a></li>
				</ul>
			</div>
			<iframe  frameborder="no" class="container5" id="container5"
				src="OneBarrageIframe.html"></iframe>
		</div>

		<div id="Intentity6">
			<!--我的关注的DIV -->
			<div class="Top">
				<ul class="Intop">
					<li class="Intopli"><a style="font-size: 20px;">我的关注</a></li>
				</ul>
			</div>
			<div class="container6">
				<!-- <a style="font-size: 17px;padding: 20px 20px;color: #999">@我关注的</a>
<iframe class="focus" src="focusIframe.html">我关注的
</iframe>
<br>
<a style="font-size: 17px;padding: 20px 20px;color: #999">@关注我的</a>
<iframe class="focued" src="focuedIframe.html">关注我的
</iframe> -->


			</div>
		</div>
		<div id="Intentity7">
			<!--我的收藏的DIV -->
			<div class="Top">
				<ul class="Intop">
					<li class="Intopli"><a style="font-size: 20px;">我的收藏</a></li>
				</ul>
			</div>
			<iframe frameborder="no" class="container7" src="1/${user.userid}/collection.htm"></iframe>
		</div>



		<div class="InContent">
			<div style="text-align: center; padding: 30px">
				<img src="../${user.imgurl}" id="userpic" width=100px height=100px style="border:2px solid grey;border-radius:50%;"><br>
				<br>
				<span style="font-size: 30px" id="user_name">${user.username}</span><br>
				<hr>
				<div id="personalsign">
				<span style="font-size: 15px;margin-bottom:10px;" id="user_sign">个性签名:
				<br><br><font style="color:#bdd7f2;">${user.sign}</font></span><br>
				</div>
			</div>
		</div>

		<div class="leftMenu">
			<div class="menu">
				<div class="menuParent">
					<div class="ListTitlePanel">
						<div class="ListTitle">
							<strong>个人中心</strong>
							<div class="leftbgbt"></div>
						</div>
					</div>
					<div class="menuList">
						<div id="m1">
							<a>修改个人信息</a>
						</div>
						<div id="m11">
							<a>修改密码</a>
						</div>
					</div>
				</div>

				<div class="menuParent">
					<div class="ListTitlePanel">
						<div class="ListTitle">
							<strong>投稿·管理</strong>
							<div class="leftbgbt2"></div>
						</div>
					</div>
					<div class="menuList">
						<div>
							<a id="Intopli1">开始投稿</a>
						</div>
						<div>
							<a id="Intopli2">管理投稿</a>
						</div>
						<div>
							<a id="Intopli3">管理评论</a>
						</div>
						<div>
							<a id="Intopli4">管理弹幕</a>
						</div>
					</div>
				</div>

				<div class="menuParent"  id="m2">
					<div class="ListTitlePanel">
						<div class="ListTitle">
							<strong>我的关注</strong>
							<div class="leftbgbt2"></div>
						</div>
					</div>
				</div>
				<div class="menuParent"  id="m3">
					<div class="ListTitlePanel">
						<div class="ListTitle">
							<strong style="color:#185697">我的收藏</strong>
							<div class="leftbgbt2"></div>
						</div>
					</div>
				</div>
				<div class="menuParent">
					<div class="ListTitlePanel">
					
					</div>
				</div>
			</div>
		</div>

	</div>
	 <script type="text/javascript" src="../static/js/userinfo.js"></script> 
	<script type="text/javascript" src="js/userupload.js"></script>
	<!-- <script>
		//获取上传按钮
		var input1 = document.getElementById("upload");

		if (typeof FileReader === 'undefined') {
			//result.innerHTML = "抱歉，你的浏览器不支持 FileReader"; 
			input1.setAttribute('disabled', 'disabled');
		} else {
			input1.addEventListener('change', readFile, false);
			/*input1.addEventListener('change',function (e){
				console.log(this.files);//上传的文件列表
			},false); */
		}
		function readFile() {
			var file = this.files[0];//获取上传文件列表中第一个文件
			if (!/image\/\w+/.test(file.type)) {
				//图片文件的type值为image/png或image/jpg
				alert("文件必须为图片！");
				return false;
			}
			// console.log(file);
			var reader = new FileReader();//实例一个文件对象
			reader.readAsDataURL(file);//把上传的文件转换成url
			//当文件读取成功便可以调取上传的接口
			reader.onload = function(e) {
				// console.log(this.result);//文件路径
				// console.log(e.target.result);//文件路径
				/*var data = e.target.result.split(',');
				var tp = (file.type == 'image/png')? 'png': 'jpg';
				var imgUrl = data[1];//图片的url，去掉(data:image/png;base64,)
				//需要上传到服务器的在这里可以进行ajax请求
				// console.log(imgUrl);
				// 创建一个 Image 对象 
				var image = new Image();
				// 创建一个 Image 对象 
				// image.src = imgUrl;
				image.src = e.target.result;
				image.onload = function(){
					document.body.appendChild(image);
				}*/

				var image = new Image();
				// 设置src属性 
				image.src = e.target.result;
				var max = 200;
				// 绑定load事件处理器，加载完成后执行，避免同步问题
				image.onload = function() {
					// 获取 canvas DOM 对象 
					var canvas = document.getElementById("cvs");
					// 如果高度超标 宽度等比例缩放 *= 
					/*if(image.height > max) {
						image.width *= max / image.height; 
						image.height = max;
					} */
					// 获取 canvas的 2d 环境对象, 
					var ctx = canvas.getContext("2d");
					// canvas清屏 
					ctx.clearRect(0, 0, canvas.width, canvas.height);
					// 重置canvas宽高
					/*canvas.width = image.width;
					canvas.height = image.height;
					if (canvas.width>max) {canvas.width = max;}*/
					// 将图像绘制到canvas上
					// ctx.drawImage(image, 0, 0, image.width, image.height);
					ctx.drawImage(image, 0, 0, 200, 200);
					// 注意，此时image没有加入到dom之中
				};
			}
		};
	</script> -->
	<script type="text/javascript" src="js/userpic.js"></script>
<script type="text/javascript" src="js/userinfo2.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	<script>
;!function(){
laydate({
   elem: '#demo'
})
}();
</script>
</body>
</html>