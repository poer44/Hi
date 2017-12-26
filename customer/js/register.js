$(function() {
	
	var registermsg=$.session.get('registerError');
	if(registermsg!=""&&registermsg!="null"&&registermsg!=null){
		toastr.error(registermsg);
	}
	$.session.set('registerError',null);
	
	//表单验证
	$("#register-form").validate();
	
	$("#login").click(function(){
		 $(window).attr('location',"user_login.html");
	});
	//用户名
	$("#username").rules('add', {
		required: true,
		minlength: 4,
		maxlength: 10,
		messages: {
			required: '请输入帐号！',
			minlength: '昵称不能小于{0}位！',
			maxlength: '昵称不能大于{0}位！'
		}
	});
	//手机
	$("#phone").rules('add', {
		required: true,
		rangelength: [11, 11],
		digits: true,
		phone: true,
		messages: {
			required: '请输入您的手机号！',
			rangelength: '请输入正确的手机号！',
			digits: '请输入正确的手机号！',
			phone: '请输入正确的手机号！'
		}
	});
	
	$("#yzm").rules('add', {
		required: true,
		rangelength: [6, 6],
		digits: true,
		yzm: true,
		messages: {
			required: '请输入手机验证码！',
			rangelength: '请输入6位手机验证码！',
			digits: '验证码格式有误',
			yzm: '请输入正确手机验证码！'
		}
	});
	//邮箱
	$("#email").rules('add', {
		required: true,
		email: true,
		messages: {
			required: '请输入您的邮箱！',
			email: '请输入正确的邮箱！',
		}
	});

	//生日
	$("#birthday").rules('add', {
		required: true,
		date: true,
		messages: {
			required: '请选择生日，如1993-03-29！',
			date: '生日格式不正确'
		}
	});
	//密码
	$("#password").rules('add', {
		required: true,
		minlength: 6,
		maxlength: 10,
		pass: true,
		messages: {
			required: '请输入6-10位密码，数字或字母！',
			minlength: '密码不能小于{0}位！',
			maxlength: '密码不能超过{0}位！',
			pass: '密码不能含数字和字母以外的符号！'
		}
	});
	//确认密码
	$("#notpass").rules('add', {
		required: true,
		equalTo: '#password',
		messages: {
			required: '请再次输入密码',
			equalTo: '密码输入不一致',
		}
	});
	// $(".accept").parents(".form-group").find("i").css("margin-right","5px");
	$("#accept").rules('add', {
		required: true,
		messages: {
			required: '请接受用户协议！'
		},
		highlight: function(element, errorClass) {
			setTimeout(function() {
				if ($("#accept-error").get(0)) {
					$("#accept-error").css("display", "block")
					$(".accept").insertBefore("#accept-error");

				}
			}, 0);
		},
	});

	// 邮箱自动补全

	$("#email").autocomplete({
		delay: 0,
		//autoFoucs:true,
		source: function(request, response) {
			var hosts = ['qq.com', '163.com', '126.com', 'sina.com.cn', '263.com'],
				term = request.term, //获取用户输入的内容
				name = term, //邮箱的用户名
				host = '', //邮箱的域名
				ix = term.indexOf('@'), //@的位置
				result = [];

			//当有@的时候，重新分配用户名和域名
			if (ix > -1) {
				name = term.slice(0, ix);
				host = term.slice(ix + 1);
			}
			if (name) {
				//如果用户已经输入@和后面的域名，
				//那么就找到相关的提示，比如bnbbs@1,就提示bnbbs@163.com
				//如果用户还没有输入@，那就提示所有域名
				var findedHosts = [];
				if (host) {
					findedHosts = $.grep(hosts, function(value, index) {
						return value.indexOf(host) > -1
					});
				} else {
					findedHosts = hosts;
				}
				var findedResult = $.map(findedHosts, function(value, index) {
					return name + '@' + value;
				})
				if (findedResult == '') {
					result.push(term)
				}

				result = result.concat(findedResult);
			}
			response(result);

		}
	});

	//邮箱验证规则
	$.validator.addMethod('email', function(value, element) {
		var email = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		return this.optional(element) || (email.test(value));
	});
	//手机验证规则
	$.validator.addMethod('phone', function(value, element) {
		var phone = /^1[3|4|5|7|8][0-9]\d{8}$/;
		return this.optional(element) || (phone.test(value));
	});
	//验证码验证
	$.validator.addMethod('yzm', function(value, element) {
		var yzm = /^\d{6}$/;
		return this.optional(element) || (yzm.test(value));
	});
	//密码验证规则
	$.validator.addMethod('pass', function(value, element) {
		var pass = /^[\w]+$/
		return this.optional(element) || (pass.test(value));
	});
	//生日验证规则
	$.validator.addMethod('date', function(value, element) {
		var date = /^(19|20)\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/;
		return this.optional(element) || (date.test(value));
	});
	
});
var clock = '';
var nums = 60;
var btn;
function sendCode(thisBtn) {
	var phone = /^1[3|4|5|7|8][0-9]\d{8}$/;
	var tel={"tel":$("#phone").val()};
	if(phone.test($("#phone").val())){
		$.ajax({
			type : "get",
			url : "getyzm.htm",
			data : tel,
			success: function(user){
				if(user.sign=="notel"){
					toastr.warning("请先输入手机号");
				}else if(user.sign=="Existed"){
					toastr.error("该手机号已被注册！");
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
		toastr.warning("手机号格式有误");
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