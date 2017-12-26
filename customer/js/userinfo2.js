$(function() {	
	ajaxFileUpload=function(){
		//下面两行代码是先拿到id为myUpload的文件的文件名，注意文件名是这样取的，file控件的values值是一个伪路径，file控件+脚本从来就无法直接操作文件  
		var filename = $("#myUpload").val();
		filename = filename.substring(filename.lastIndexOf("\\") + 1);
		//下面是取得文件的后缀名，如果后缀名不是图片后缀名，那么则不运行ajax上传代码，之间到下面的else中弹窗  
		exname = filename.substring(filename
				.lastIndexOf(".") + 1);
		if (exname.indexOf("png") != -1
				|| exname.indexOf("jpg") != -1
				|| exname.indexOf("gif") != -1
				|| exname.indexOf("bmp") != -1
				|| exname.indexOf("jpeg") != -1) {
			$.ajaxFileUpload({
						type : "post",
						url : "modifyuserpic.htm?userid="+$("#userid").val(),
						dataType: 'JSON',
						secureuri : false,
						//这里的结构与普通ajax是一样的，到uploadsuc.jsp中响应，注意下面的fileElementId就行，指明传递的文件是id为myUpload的东西  
						fileElementId : 'myUpload',
						success : function(user) {
							$("#userpic").attr("src",$("#chose").attr("src"));	
							toastr.success("保存成功");
							var file=document.getElementById('myUpload');
					          if(file.outerHTML){
					                 file.outerHTML=file.outerHTML;
					          }
					          else{ 
					                file.value="";
					          }
						},
						error : function(user) {
							toastr.error("修改失败");
						}
					});
		} else {
			toastr.warning("请先上传头像");
		}
		return false;
	}
	$("#username").bind('input propertychange',function(){
		if($("#username").val().length>=4&&$("#username").val().length<=10){
			$("#checkusername").html("输入昵称");
		}else{
			$("#checkusername").html("昵称必须在4-10位含汉子或字母或数字");
		}
	});
	$("#email").bind('input propertychange',function(){
		var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if(!reg.test($("#email").val())){
			$("#checkemail").html("email格式有误");
		}else{
			$("#checkemail").html("输入邮箱");
		}
	});
	if($("#usersex").val()!=null&&$("#usersex").val()!=""){
		if($("#usersex").val()=="男"){
			$("#male").attr("selected", true);
		}
		if($("#usersex").val()=="女"){
			$("#female").attr("selected", true);
		}
		if($("#usersex").val()=="保密"){
			$("#sexsecrecy").attr("selected", true);
		}
	}	
	if($("#useremotion").val()!=null&&$("#useremotion").val()!=""){
		if($("#useremotion").val()=="单身"){
			$("#single").attr("selected", true);
		}
		if($("#useremotion").val()=="已婚"){
			$("#married").attr("selected", true);
		}
		if($("#useremotion").val()=="保密"){
			$("#emotionsecrecy").attr("selected", true);
		}
	}	
	$("#modify_user").click(function(){
		if($("#checkemail").html()=="输入邮箱"&&$("#checkusername").html()=="输入昵称"){
			$.ajax({
				type : "post",//post方法
				url : "modifyuserinfo.htm",
				data : {"userid":$("#userid").val(),
					"username":$("#username").val(),
					"sex":$("#sex").val(),
					"birthday":$("#demo").val(),
					"address":$("#address").val(),
					"emotion":$("#emotion").val(),
					"email":$("#email").val(),
					"sign":$("#sign").val()
					},
				//ajax成功的回调函数
				success: function(user){
					if(user.msg=="success"){
						toastr.success("修改成功");
						$("#user_name").text(user.username);
						$("#user_sign").html("个性签名:<br><br><font >"+user.sign+"</font>");
					}else{
						toastr.warning("请先登录");
					}
	        	}
			});	
		}else{
			if($("#checkusername").html()=="昵称必须在4-10位含汉子或字母或数字"){
				$("#username").focus();
			}else if($("#checkemail").html()=="email格式有误"){
				$("#email").focus();
			}
		}
	});
	$("#oldpwd").bind('input propertychange',function(){ //判断原密码是否有误
		var reg = /^[a-z0-9A-Z]{6,10}$/;
    	if(!reg.test($("#oldpwd").val())){
    		$("#checkoldpwd").html("密码格式错误");
    	}else{
    		$("#checkoldpwd").html("原密码");
    	}
    });    
	$("#pwd").bind('input propertychange',function(){
		var reg = /^[a-z0-9A-Z]{6,10}$/;
    	if(!reg.test($("#pwd").val())){
    		$("#checkpwd").html("密码格式错误");
    	}else{
    		$("#checkpwd").html("新密码");
    		if($("#repwd").val()!=""){
    			if($("#pwd").val()!=$("#repwd").val()){
    				$("#checkrepwd").html("两次密码不同");
        		}else{
        			$("#checkrepwd").html("确认密码");
        		}
    		}
    	}
    });
    $("#repwd").bind('input propertychange',function(){
    	var reg = /^[a-z0-9A-Z]{6,10}$/;
    	if(!reg.test($("#repwd").val())){
    		$("#checkrepwd").html("密码格式错误");
    	}else{
    		if($("#pwd").val()!=$("#repwd").val()){
    			$("#checkrepwd").html("两次密码不同");
    		}else{
    			$("#checkrepwd").html("确认密码");
    		}
    	}
    });
	$("#modify_pwd").click(function(){  //修改密码按钮
		if($("#checkoldpwd").html()=="原密码"&&$("#checkpwd").html()=="新密码"&&$("#checkrepwd").html()=="确认密码"){
			if($("#oldpwd").val()!=""&&$("#pwd").val()!=""&&$("#repwd").val()!=""){
				$.ajax({
					type : "post",//post方法
					url : "modifyuserpwd.htm",
					data : {"userid":$("#userid").val(),
						"oldpwd":$("#oldpwd").val(),
						"newpwd":$("#repwd").val()
						},
					//ajax成功的回调函数
					success: function(user){
						if(user.msg=="success"){
							$("#oldpwd").val("");
							$("#pwd").val("");
							$("#repwd").val("");
							toastr.success("修改成功");
						}else if(user.msg=="nologin"){
							toastr.warning("请先登录");
						}else if(user.msg=="errorpwd"){
							toastr.error("原密码有误");
							$("#oldpwd").val("");
							$("#oldpwd").focus();
						}
		        	}
				});	
			}else{
				if($("#oldpwd").val()=="")$("#oldpwd").focus();
				else if($("#pwd").val()=="")$("#pwd").focus();
				else if($("#newpwd").val()=="")$("#newpwd").focus();
			}
    	}else{
    		if($("#checkoldpwd").html()=="密码格式错误")$("#oldpwd").focus();
			else if($("#checkpwd").html()=="密码格式错误")$("#pwd").focus();
			else if($("#checkrepwd").html()=="密码格式错误")$("#repwd").focus();
			else if($("#checkrepwd").html()=="两次密码不同")$("#repwd").focus();
    	}
	});
	
	$("#Intentity1").show();
	$("#Intentity2").hide();
	$("#Intentity3").hide();
	$("#Intentity4").hide();
	$("#Intentity5").hide();
	$("#Intentity6").hide();
	$("#Intentity7").hide();
	$("#Intentity11").hide();
	var menuParent = $('.menu > .ListTitlePanel > div');//获取menu下的父层的DIV
	var menuList = $('.menuList');
	$('.menu > .menuParent > .ListTitlePanel > .ListTitle').each(
			function(i) {//获取列表的大标题并遍历
				$(this).click(function() {
					if ($(menuList[i]).css('display') == 'none') {
						$(menuList[i]).slideDown(300);
					} else {
						$(menuList[i]).slideUp(300);
					}
				});
			});
	$("#m1").click(function() {
		$("#Intentity1").show();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#m11").click(function() {
		$("#Intentity7").hide();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity1").hide();
		$("#Intentity11").show();
	});
	$("#m3").click(function() {
		$("#Intentity7").show();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity1").hide();
		$("#Intentity11").hide();
	});
	$("#Inconnect").click(function() {
		$("#Intentity7").show();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity1").hide();
		$("#Intentity11").hide();
	});
	
	$("#m2").click(function() {
		$("#Intentity6").show();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity1").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intopli1").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").show();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplili1").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").show();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplilili1").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").show();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplililili1").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").show();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplilililili1").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").show();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Incontribute").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").show();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intopli2").click(function() {
		$("#Intentity1").hide();
		$("#Intentity3").show();
		$("#Intentity2").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplili2").click(function() {
		$("#Intentity1").hide();
		$("#Intentity3").show();
		$("#Intentity2").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplilili2").click(function() {
		$("#Intentity1").hide();
		$("#Intentity3").show();
		$("#Intentity2").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplililili2").click(function() {
		$("#Intentity1").hide();
		$("#Intentity3").show();
		$("#Intentity2").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplilililili2").click(function() {
		$("#Intentity1").hide();
		$("#Intentity3").show();
		$("#Intentity2").hide();
		$("#Intentity4").hide();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intopli3").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").show();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplili3").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").show();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplilili3").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").show();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplililili3").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").show();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplilililili3").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").show();
		$("#Intentity5").hide();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intopli4").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").show();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplili4").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").show();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplilili4").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").show();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplililili4").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").show();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
	$("#Intoplilililili4").click(function() {
		$("#Intentity1").hide();
		$("#Intentity2").hide();
		$("#Intentity3").hide();
		$("#Intentity4").hide();
		$("#Intentity5").show();
		$("#Intentity6").hide();
		$("#Intentity7").hide();
		$("#Intentity11").hide();
	});
});