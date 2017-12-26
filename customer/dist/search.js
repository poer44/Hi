$(function(){
	$(".showgh").hover(function(){
		$(".gh").css("display","block");
	});
	$(".showgh").mouseleave(function(){
		$(".gh").css("display","none");
	});
	$('#search-form').autocomplete({
		width: 300,
		height: 30,
		onSubmit: function(text){
			$('#message').html('Selected: <b>' + text + '</b>');			
		}
	});

	function getUrlParam(name) {
		//构造一个含有目标参数的正则表达式对象
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		//匹配目标参数 
		var r = window.location.search.substr(1).match(reg); 
		//返回参数值
		if (r != null) return unescape(decodeURI(r[2])); return null;
	}

	var urlData1 = getUrlParam("query");
	var urlData2 = getUrlParam("typeid");
	$(".keyword1").val(urlData1);       
	// 将数据填充到input框里面
	if(urlData1!=null){
		$(".ones .nav-cur").removeClass("nav-cur");
		$(".two .nav-cur").removeClass("nav-cur");
		$("#m1").addClass('nav-cur');
		$("#sAll").addClass('nav-cur');

		$(".autocomplete-input").val(urlData1);
		$("#hid").val(urlData1);
		$.ajax({
			url : '../searchViewname.htm',
			type : 'POST',
			dataType : 'json',
			data:{
				name:urlData1,
			},
			async : false,
			success : function(data) {
				//console.log(data);
				//alert("**"+eval(data)[eval(data).length-1]["total"]);
				var videopage=eval(data)[eval(data).length-1]["videopage"];
				$("#total").html("共"+eval(data)[eval(data).length-1]["total"]+"条记录");
				$('#sViews').html("");
				$("#pg").html("");
				$(".page1 > a").text("");
				if(videopage==0)
					videopage=1;
				var pmax=5;
				if(videopage<5)
					pmax=videopage;
				if(eval(data).length-1>0){
					for(var i=0;i<pmax/*videopage*/;i++){
						var tbBody="";
						if(i==0)
							tbBody="<li style='background-color: gray;' onclick='selectPage("+(i+1)+",this)'><a href='javascript:void(0)'>"+(i+1)+"</a></li>";
						else
							tbBody="<li  onclick='selectPage("+(i+1)+",this)'><a href='javascript:void(0)'>"+(i+1)+"</a></li>";
						$("#pg").append(tbBody);
					}
					$(".page1 > a").text("当前页"+1+"/"+videopage);
					$.each( data, function(i, n) {
						if((eval(data).length-1)>i){
							var tbBody="";
							var image=n.imgurl.replace(/\\/g, "/");

							tbBody ="<tr><td><a target='_blank' href='vedioPlay.html?videoid="+n.videoid+"'><img src='../"+image+"' width='180px' height='100px' alt='#'></a></td>" +
							"<td><a href='vedioPlay.html?videoid="+n.videoid+"' style='font-size: 20px;'>"+n.videoname+"</a>" +
							"<p><a style='font-size: 20px; color: red; margin-top: 10px;'>Author:"+n.author+"</a></p>" +
							"<p><a style='margin-top: 10px;'>点击量："+n.play+"</a> <a style='margin-top: 10px;'>收藏量："+n.collection+"</a></p></td></tr>";
							$("#sViews").append(tbBody);
						}
					})
				}
			},
		})
	}
	if(urlData2!=null){
		$(".two .nav-cur").removeClass("nav-cur");
		$("#"+urlData2).addClass('nav-cur');

		$.ajax({
			url : '../searchViewtype.htm',
			type : 'POST',
			dataType : 'json',
			data:{
				pagenu:1,
				MorA:$(".ones .nav-cur").attr("id"),
				typeid:urlData2,
				namelike:$("#hid").val(),
			},
			async : false,
			success : function(data) {
				//console.log(data);

				var videopage=eval(data)[eval(data).length-1]["videopage"];
				$("#total").html("共"+eval(data)[eval(data).length-1]["total"]+"条记录");
				$('#sViews').html("");
				$("#pg").html("");
				$(".page1 > a").text("");
				if(videopage==0)
					videopage=1;
				var pmax=5;
				if(videopage<5)
					pmax=videopage;
				if(eval(data).length-1>0){
					for(var i=0;i<pmax/*videopage*/;i++){
						var tbBody="";
						if(i==0)
							tbBody="<li style='background-color: gray;' onclick='selectPage("+(i+1)+",this)'><a href='javascript:void(0)'>"+(i+1)+"</a></li>";
						else
							tbBody="<li  onclick='selectPage("+(i+1)+",this)'><a href='javascript:void(0)'>"+(i+1)+"</a></li>";
						$("#pg").append(tbBody);
					}
					$(".page1 > a").text("当前页"+1+"/"+videopage);
					$.each( data, function(i, n) {
						if((eval(data).length-1)>i){
							var tbBody="";
							var image=n.imgurl.replace(/\\/g, "/");

							tbBody ="<tr><td><a target='_blank' href='vedioPlay.html?videoid="+n.videoid+"'><img src='../"+image+"' width='180px' height='100px' alt='#'></a></td>" +
							"<td><a href='vedioPlay.html?videoid="+n.videoid+"' style='font-size: 20px;'>"+n.videoname+"</a>" +
							"<p><a style='font-size: 20px; color: red; margin-top: 10px;'>Author:"+n.author+"</a></p>" +
							"<p><a style='margin-top: 10px;'>点击量："+n.play+"</a> <a style='margin-top: 10px;'>收藏量："+n.collection+"</a></p></td></tr>";
							$("#sViews").append(tbBody);
						}
					})
				}
			},
		})
	}


	//搜索框点击


	$(".autocomplete-button").click(function(){
		/*$(".ones .nav-cur").removeClass("nav-cur");
		$(".two .nav-cur").removeClass("nav-cur");
		$("#m1").addClass('nav-cur');
		$("#sAll").addClass('nav-cur');*/
		var cont=$('.autocomplete-input').val();
		if(cont=="")
			cont="all";
		else
			$('.autocomplete-input').val(cont);
		$("#hid").val(cont);
		var p;
		switch($(".two .nav-cur").text()){
		case("全部"):
			p=0;
		break;
		case("娱乐"):
			p=1;
		break;
		case("游戏"):
			p=2;
		break;
		case("动画"):
			p=3;
		break;
		case("音乐"):
			p=4;
		break;
		case("舞蹈"):
			p=5;
		break;
		}
		$.ajax({
			url : '../searchViewtype.htm',
			type : 'POST',
			dataType : 'json',
			data:{
				pagenu:1,
				MorA:$(".ones .nav-cur").attr("id"),
				typeid:p,
				namelike:$("#hid").val(),
			},
			async : false,
			success : function(data) {
				//console.log(data);
				//alert("**"+eval(data)[eval(data).length-1]["total"]);
				var videopage=eval(data)[eval(data).length-1]["videopage"];
				$("#total").html("共"+eval(data)[eval(data).length-1]["total"]+"条记录");
				$('#sViews').html("");
				$("#pg").html("");
				$(".page1 > a").text("");
				if(videopage==0)
					videopage=1;
				var pmax=5;
				if(videopage<5)
					pmax=videopage;
				if(eval(data).length-1>0){
					for(var i=0;i<pmax/*videopage*/;i++){
						var tbBody="";
						if(i==0)
							tbBody="<li style='background-color: gray;' onclick='selectPage("+(i+1)+",this)'><a href='javascript:void(0)'>"+(i+1)+"</a></li>";
						else
							tbBody="<li  onclick='selectPage("+(i+1)+",this)'><a href='javascript:void(0)'>"+(i+1)+"</a></li>";
						$("#pg").append(tbBody);
					}
					$(".page1 > a").text("当前页"+1+"/"+videopage);
					$.each( data, function(i, n) {
						if((eval(data).length-1)>i){
							var tbBody="";
							var image=n.imgurl.replace(/\\/g, "/");

							tbBody ="<tr><td><a target='_blank' href='vedioPlay.html?videoid="+n.videoid+"'><img src='../"+image+"' width='180px' height='100px' alt='#'></a></td>" +
							"<td><a href='vedioPlay.html?videoid="+n.videoid+"' style='font-size: 20px;'>"+n.videoname+"</a>" +
							"<p><a style='font-size: 20px; color: red; margin-top: 10px;'>Author:"+n.author+"</a></p>" +
							"<p><a style='margin-top: 10px;'>点击量："+n.play+"</a> <a style='margin-top: 10px;'>收藏量："+n.collection+"</a></p></td></tr>";
							$("#sViews").append(tbBody);
						}
					})
				}
			},
		})
	});
});
//分类查询
function searchByType(a,obj){
	$(".two .nav-cur").removeClass("nav-cur");
	$(obj).addClass('nav-cur');
	$.ajax({
		url : '../searchViewtype.htm',
		type : 'POST',
		dataType : 'json',
		data:{
			pagenu:1,
			MorA:$(".ones .nav-cur").attr("id"),
			typeid:a,
			namelike:$("#hid").val(),
		},
		async : false,
		success : function(data) {
			//console.log(data);

			var videopage=eval(data)[eval(data).length-1]["videopage"];
			$("#total").html("共"+eval(data)[eval(data).length-1]["total"]+"条记录");
			$('#sViews').html("");
			$("#pg").html("");
			$(".page1 > a").text("");
			if(videopage==0)
				videopage=1;
			var pmax=5;
			if(videopage<5)
				pmax=videopage;
			if(eval(data).length-1>0){
				for(var i=0;i<pmax/*videopage*/;i++){
					var tbBody="";
					if(i==0)
						tbBody="<li style='background-color: gray;' onclick='selectPage("+(i+1)+",this)'><a href='javascript:void(0)'>"+(i+1)+"</a></li>";
					else
						tbBody="<li  onclick='selectPage("+(i+1)+",this)'><a href='javascript:void(0)'>"+(i+1)+"</a></li>";
					$("#pg").append(tbBody);
				}
				$(".page1 > a").text("当前页"+1+"/"+videopage);
				$.each( data, function(i, n) {
					if((eval(data).length-1)>i){
						var tbBody="";
						var image=n.imgurl.replace(/\\/g, "/");

						tbBody ="<tr><td><a target='_blank' href='vedioPlay.html?videoid="+n.videoid+"'><img src='../"+image+"' width='180px' height='100px' alt='#'></a></td>" +
						"<td><a href='vedioPlay.html?videoid="+n.videoid+"' style='font-size: 20px;'>"+n.videoname+"</a>" +
						"<p><a style='font-size: 20px; color: red; margin-top: 10px;'>Author:"+n.author+"</a></p>" +
						"<p><a style='margin-top: 10px;'>点击量："+n.play+"</a> <a style='margin-top: 10px;'>收藏量："+n.collection+"</a></p></td></tr>";
						$("#sViews").append(tbBody);
					}
				})
			}
		},
	})
};
//视频搜索或作者搜索
function searchMorA(a,obj){
	$(".ones .nav-cur").removeClass("nav-cur");
	//$(".two .nav-cur").removeClass("nav-cur");
	$(obj).addClass('nav-cur');
	//$("#sAll").addClass('nav-cur');
	var cont=$('.autocomplete-input').val();
	if(cont=="")
		cont="all";
	else
		$('.autocomplete-input').val(cont);
	var p;
	switch($(".two .nav-cur").text()){
	case("全部"):
		p=0;
	break;
	case("娱乐"):
		p=1;
	break;
	case("游戏"):
		p=2;
	break;
	case("动画"):
		p=3;
	break;
	case("音乐"):
		p=4;
	break;
	case("舞蹈"):
		p=5;
	break;
	}
	$.ajax({
		url : '../searchViewtype.htm',
		type : 'POST',
		dataType : 'json',
		data:{
			pagenu:1,
			MorA:$(".ones .nav-cur").attr("id"),
			typeid:p,
			namelike:$("#hid").val(),
		},
		async : false,
		success : function(data) {
			console.log(data);
			var videopage=eval(data)[eval(data).length-1]["videopage"];
			$("#total").html("共"+eval(data)[eval(data).length-1]["total"]+"条记录");
			$('#sViews').html("");
			$("#pg").html("");
			$(".page1 > a").text("");
			if(videopage==0)
				videopage=1;
			var pmax=5;
			if(videopage<5)
				pmax=videopage;
			if(eval(data).length-1>0){
				for(var i=0;i<pmax/*videopage*/;i++){
					var tbBody="";
					if(i==0)
						tbBody="<li style='background-color: gray;' onclick='selectPage("+(i+1)+",this)'><a href='javascript:void(0)'>"+(i+1)+"</a></li>";
					else
						tbBody="<li  onclick='selectPage("+(i+1)+",this)'><a href='javascript:void(0)'>"+(i+1)+"</a></li>";
					$("#pg").append(tbBody);
				}
				$(".page1 > a").text("当前页"+1+"/"+videopage);
				$.each( data, function(i, n) {
					if((eval(data).length-1)>i){
						var tbBody="";
						var image=n.imgurl.replace(/\\/g, "/");

						tbBody ="<tr><td><a target='_blank' href='vedioPlay.html?videoid="+n.videoid+"'><img src='../"+image+"' width='180px' height='100px' alt='#'></a></td>" +
						"<td><a href='vedioPlay.html?videoid="+n.videoid+"' style='font-size: 20px;'>"+n.videoname+"</a>" +
						"<p><a style='font-size: 20px; color: red; margin-top: 10px;'>Author:"+n.author+"</a></p>" +
						"<p><a style='margin-top: 10px;'>点击量："+n.play+"</a> <a style='margin-top: 10px;'>收藏量："+n.collection+"</a></p></td></tr>";
						$("#sViews").append(tbBody);
					}
				})
			}
		},
	})
};

function selectPage(a,obj){
	$("#pg li").css("background-color","#d3cecf")
	$(obj).css("background-color","gray");
	var p;
	switch($(".two .nav-cur").text()){
	case("全部"):
		p=0;
	break;
	case("娱乐"):
		p=1;
	break;
	case("游戏"):
		p=2;
	break;
	case("动画"):
		p=3;
	break;
	case("音乐"):
		p=4;
	break;
	case("舞蹈"):
		p=5;
	break;
	}
	$.ajax({
		url : '../searchViewtype.htm',
		type : 'POST',
		dataType : 'json',
		data:{
			pagenu:a,
			MorA:$(".ones .nav-cur").attr("id"),
			typeid:p,
			namelike:$("#hid").val(),
		},
		async : false,
		success : function(data) {
			//console.log(data);

			var videopage=eval(data)[eval(data).length-1]["videopage"];
			$("#total").html("共"+eval(data)[eval(data).length-1]["total"]+"条记录");
			$('#sViews').html("");
			if(videopage>5){
				$("#pg").html("");
				if(videopage==0)
					videopage=1;
				var pmax=a+2;
				var pmin=a-3;
				if(a+2>videopage)
					pmax=videopage;
				if(a-3<0){
					pmin=0;
					pmax=5;
				}
				for(var i=pmin;i<pmax;i++){
					var tbBody="";
					if(i==a-1)
						tbBody="<li style='background-color: gray;' onclick='selectPage("+(i+1)+",this)'><a href='javascript:void(0)'>"+(i+1)+"</a></li>";
					else
						tbBody="<li  onclick='selectPage("+(i+1)+",this)'><a href='javascript:void(0)'>"+(i+1)+"</a></li>";
					$("#pg").append(tbBody);
				}
			}
			$(".page1 > a").text("当前页"+a+"/"+videopage);
			$.each( data, function(i, n) {
				if((eval(data).length-1)>i){
					var tbBody="";
					var image=n.imgurl.replace(/\\/g, "/");

					tbBody ="<tr><td><a target='_blank' href='vedioPlay.html?videoid="+n.videoid+"'><img src='../"+image+"' width='180px' height='100px' alt='#'></a></td>" +
					"<td><a href='vedioPlay.html?videoid="+n.videoid+"' style='font-size: 20px;'>"+n.videoname+"</a>" +
					"<p><a style='font-size: 20px; color: red; margin-top: 10px;'>Author:"+n.author+"</a></p>" +
					"<p><a style='margin-top: 10px;'>点击量："+n.play+"</a> <a style='margin-top: 10px;'>收藏量："+n.collection+"</a></p></td></tr>";
					$("#sViews").append(tbBody);
				}
			})
		},
	})
};



