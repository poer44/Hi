$(function(){
	function isEmptyObject(e) {
		var t;
		for (t in e)
			return !1;
		return !0
	}
	
	$.ajax({
		url : '../userinfotop.htm',
		type : 'POST',
		success : function(data) {
			if (isEmptyObject(data)) {
				$('#userinfo').val("");
			} else {
				$('#userinfo').val(data.userid);
			}
		},
	});
});

document.onkeydown = function(e) {
	var theEvent = window.event || e;
	var code = theEvent.keyCode || theEvent.which;
	if (code == 13) {
		if ($('#query').val()!="") {
			  $("#encodeSearch").click();
		   }
	}
}
//搜索转码跳转
$("#encodeSearch").click(function(){  
    var searchText= $.trim($("#query").val()); 
   window.open(encodeURI("customer/search.html?query="+searchText));  
})  
