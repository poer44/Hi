function checklogin1(){
		if($("#checketel1").html()==""&&$("#checkpwd").html()==""){
			return true;
		}else{
			return false;
		}
}

$(function(){
	console.log($.session.get('nowurl'));
	var registermsg=$.session.get('registermsg');
	var errormsg=$.session.get('errormsg');
	if(registermsg!=""&&registermsg!="null"&&registermsg!=null){
		toastr.success(registermsg);
	}
	$.session.set('registermsg',null);
	if(errormsg!=""&&errormsg!="null"&&errormsg!=null){
		toastr.error(errormsg);
	}
	$.session.set('errormsg',null);
	
	$("#requesturl").attr("value",$.session.get('nowurl'));
	$("#register1").click(function(){
		 $(window).attr('location',"user_register.html");
	});
	$("#register2").click(function(){
		 $(window).attr('location',"user_register.html");
	});
	$("#pwd-change").click(function(){
		$("#pwd-login").css("display","none");
		$("#tel-login").css("display","block");
	});
	$("#tel-change").click(function(){
		$("#pwd-login").css("display","block");
		$("#tel-login").css("display","none");
	});
	$("#tel1").change(function(){
    	 var reg=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
    	 if(!reg.test($("#tel1").val())){
			$("#checketel1").html("手机号格式错误");
			return;
		}else{
			$("#checketel1").html("");
			return;
		}
    });
    $("#pwd").change(function(){
    	var reg=/^[a-zA-Z0-9]{6,10}$/;
   	 if(!reg.test($("#pwd").val())){
			$("#checkpwd").html("密码必须为6~10位含字母或数字");
			return;
		}else{
			$("#checkpwd").html("");
			return;
		}
   	});
    $("#tel2").change(function(){
    	 var reg=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
    	 if(!reg.test($("#tel2").val())){
			$("#checketel2").html("手机号格式错误");
			return;
		}else{
			$("#checketel2").html("");
			return;
		}
    });
});
var clock = '';
var nums = 60;
var btn;
function sendCode(thisBtn) {
	var phone = /^1[3|4|5|7|8][0-9]\d{8}$/;
	var tel={"tel":$("#tel2").val()};
	if(phone.test($("#tel2").val())){
		$.ajax({
			type : "get",
			url : "logingetyzm.htm",
			data : tel,
			success: function(user){
				if(user.sign=="notel"){
					toastr.warning("请先输入手机号");
				}else if(user.sign=="noExist"){
					toastr.warning("该手机号尚未注册！");
				}else{
					btn = thisBtn;
					btn.disabled = true; //将按钮置为不可点击
					btn.value = nums + '秒后可获取';
					clock = setInterval(doLoop, 1000); //一秒执行一次
					$("#showyzm").attr("value",user.sign);
				}
			}
		});	
	}else{
		toastr.error("手机号格式有误");
	}		
}
function doLoop() {
	nums--;
	if (nums > 0) {
		btn.value = nums + '秒后可获取';
	} else {
		clearInterval(clock); //清除js定时器
		btn.disabled = false;
		btn.value = '发送验证码';
		nums = 60; //重置时间
	}
}