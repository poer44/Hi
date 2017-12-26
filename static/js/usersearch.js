$(function(){
	
	//登陆后显示账户并且跳转回前一页面
	function isEmptyObject(e) {
		var t;
		for (t in e)
			return !1;
		return !0
	}
	var topinfo=$('#userinfo');
	topinfo.click(function(){
		 $.session.set('nowurl', window.location.pathname+window.location.search);
	});
	$.ajax({
		url : '../userinfotop.htm',
		type : 'POST',
		success : function(data) {
			if (isEmptyObject(data)) {
				topinfo.html("登录/注册");
				$('#userinfobottom').css("display","none");
			} else {
				topinfo.html(data.username);
				topinfo.attr('href',"information.jsp");
				topinfo.attr('target',"_blank");
				$('#uploadV').css("display","block");
			}
		},
	});
});
document.onkeydown = function(e) {
	var theEvent = window.event || e;
	var code = theEvent.keyCode || theEvent.which;
	if (code == 13) {
		if ($('.autocomplete-input').val()!="") {
			  $(".autocomplete-button").click();
		   }
	}
}
