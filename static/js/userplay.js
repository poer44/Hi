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
				$.session.set('playuser', null);
			} else {
				topinfo.html(data.username);
			    $.session.set('playuser', data.userid);
				$("#userid").val(data.userid);
				topinfo.attr('href',"information.jsp");
				topinfo.attr('target',"_blank");
				$('#uploadV').css("display","block");
				$.ajax({
					url : "getcollection.htm",
					data : {"userid":$("#userid").val()},
					async : false,
					success : function(collection) {
						 $.each(collection, function(i, item) {
							if(item.collection==$('#getUrlData').val()){
								$(".loveImg").attr("src","images/love.png");
							}
						}); 
					}
				});
			}
		},
	})
});

