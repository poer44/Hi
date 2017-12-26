$(function(){
	//登陆后显示账户并且跳转回前一页面
	var topinfo=$('#userinfo');
	topinfo.click(function(){
		 $.session.set('nowurl',window.location.pathname+window.location.search);
	});
	$.ajax({
		url : 'userinfotop.htm',
		type : 'POST',
		success : function(data) {
			if (isEmptyObject(data)) {
				topinfo.html("登录/注册");
				$('#userinfobottom').css("display","none");
			} else {
				topinfo.html(data.username);
				topinfo.attr('href',"customer/information.jsp");
				topinfo.attr('target',"_blank");
				$('#uploadV').css("display","block");
			}
		},
	})
	
	//页面加载随机的类型视频
	//类型排行榜
	var b=1;
	for (; b<6; )
	{
		$.ajax({
		url : 'ranLimit.htm',
		type : 'POST',
		data: {
			typeid:b
		},
		dataType : 'json',
		async : false,
		success : function(data) {
			if (isEmptyObject(data)) {
			} else {
				$('#big1'+b).html("");
				$.each( data, function(i, n) {
				var tbBody="";
				var image=n.imgurl.replace(/\\/g, "/");
				if(b==4||b==5){
					if(i<10){
					tbBody ="<li class='area-menu__item'><a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' class='img'>"+
					" <img src='"+image+"' alt='#'>" +
					" <span class='mask'> <em class='time'>"+n.videolength+"</em> </span> " +
					"<span class='icon-recommend'>独家推荐</span> </a>" +
					" <div class='info'> <a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' class='change-title'>"+n.videoname+"</a>" +
					" <span class='play-info clearfix'> <i class='iconfont icon-play fl'>"+n.play+"</i>" +
					" <i class='iconfont icon-collect fr'>"+n.collection+"</i> </span> </div> </li>";
				}}else{
				if(i<11){
					tbBody ="<li class='area-menu__item'><a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' class='img'>"+
					" <img src='"+image+"' alt='#'>" +
					" <span class='mask'> <em class='time'>"+n.videolength+"</em> </span> " +
					"<span class='icon-recommend'>独家推荐</span> </a>" +
					" <div class='info'> <a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' class='change-title'>"+n.videoname+"</a>" +
					" <span class='play-info clearfix'> <i class='iconfont icon-play fl'>"+n.play+"</i>" +
					" <i class='iconfont icon-collect fr'>"+n.collection+"</i> </span> </div> </li>";
				}
				}
				$('#big1'+b).append(tbBody);
				})
			}
		},
	})
	$.ajax({
		url : 'typeTop.htm',
		type : 'POST',
		data: {
			typeid:b
		},
		dataType : 'json',
		async : false,
		success : function(data) {
			if (isEmptyObject(data)) {
			} else {
				$('#top'+b).html("");
				$.each( data, function(i, n) {
				var tBody="";
				var image=n.imgurl.replace(/\\/g, "/");
				if(i<3){
					tBody="<li class='has-img'";
					if(i==2&&b==4){
						tBody +="id='5'";
					}
					tBody +="><a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"'" +
						" class='img fl'> <img src='"+image+"' alt='#'> " +
						"<span class='rank'>"+(i+1)+"</span> </a> <div class='info fr'>" +
						" <a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' href='#' class='text-overflow'>"+n.videoname+"</a> <p>" +
						" <a>Author:"+n.author+"</a> </p> <p>" +
						" <i class='iconfont icon-play fl'>"+n.play+"</i> " +
						"<i class='iconfont icon-collect fr'>"+n.collection+"</i> </p> </div> </li>";
				}else{
				if(b==4||b==5){
					if(i<6){
						tBody ="<li><a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' class='rank-item text-overflow'>" +
								" <span class='rank-num'>"+(i+1)+"</span>"+n.videoname+" </a></li>";
					}
				}else{
				if(i<12){
					tBody +="<li";
					if(i==8){
						tBody +=" id='"+(b+1)+"'";
					}
						tBody +="><a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' class='rank-item text-overflow'>" +
								" <span class='rank-num'>"+(i+1)+"</span>"+n.videoname+" </a></li>";
					}
				}
				}
				$('#top'+b).append(tBody);
				})
			}
		},
	})
	b++;
	}

//搜索量排行
	$.ajax({
		url : 'searchHot.htm',
		type : 'POST',
		dataType : 'json',
		async : false,
		success : function(data) {
			if (isEmptyObject(data)) {
				$('.search-feedback').css("display","none");
			} else {
				$('.search-menu').html("");
				$.each( data, function(i, n) {
				var tbBody="";
				if(i<=6){
				if(i<=2){
					tbBody ="<a target='_blank' href='customer/search.html?query="+n.videoname+"' class='item' style='color:red;'> <span>"+(i+1)+"</span> <em>"+n.videoname+"</em></a> ";
				}else{
					tbBody ="<a target='_blank' href='customer/search.html?query="+n.videoname+"' class='item'> <span>"+(i+1)+"</span> <em>"+n.videoname+"</em></a> ";
				}
				$(".search-menu").append(tbBody);
				}
				})
				
			}
		},
	})
	//轮播图播放量排行
	$.ajax({
	url : 'collTop.htm',
	type : 'POST',
	dataType : 'json',
	async : false,
	success : function(data) {
		if (isEmptyObject(data)) {
		} else {
			$.each( data, function(i, n) {
				if(i<6){
					var image=n.imgurl.replace(/\\/g, "/");
					var tbody="";
					tbody="<li class='slider-menu__item'><a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' class='item'>" +
							"<img src='"+image+"' alt='#'>"+
							"<div class='mask-gradient'> <p>"+n.videoname+"</p>"+
								"<div class='text-box'> <em>Author: "+ n.author+"</em> <span class='info'>" +
								" <i class='iconfont icon-collect'>"+n.collection+"</i></span></div></div>"
					"</a></li>";
				$("#slidermenu").append(tbody);
				}
			})
			
		}
	},
})
var imges=new Array();
$.ajax({
	url : 'playTop.htm',
	type : 'POST',
	dataType : 'json',
	async : false,
	success : function(data) {
		if (isEmptyObject(data)) {
			imges=[ 'uploadfiles/001.jpg',
					'customer/images/a2.png',
					'customer/images/a3.png',
					'customer/images/a4.png',
					'customer/images/a5.png' ,
					'customer/images/a5.png' ];
		} else {
			$.each( data, function(i, n) {
				var tbody="";
				if(i<5){
					var image=n.imgurl.replace(/\\/g, "/");
					imges[i]=image;
					tbody="<li class='area-menu__item'>" +
					"<a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' class='img'>" +
					" <img src='"+image+"' alt='#'> <span" +
					" class='mask'> <em class='time'>"+n.videolength+"</em>  </span> </a> <div class='info'>" +
					" <a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' class='title' style='max-width: 100%;word-break: break-all;" +
					" word-wrap: normal; white-space: nowrap; text-overflow: ellipsis; overflow: hidden;'>"+n.videoname+"</a> <a" +
					" class='userName'>Author: "+n.author+"</a> </div> </li>";
				}
				$("#recommend").append(tbody);
			})
		}
	},
})

var banenr2 = new FragmentBanner({
	container : "#banner2",// 选择容器 必选
	imgs :imges,// 图片集合
});
//类型中播放量排行第一
$.ajax({
	url : 'topFirst.htm',
	type : 'POST',
	dataType : 'json',
	async : false,
	success : function(data) {
		if (isEmptyObject(data)) {
		} else {
			$.each( data, function(i, n) {
					var image=n.imgurl.replace(/\\/g, "/");
					var tbody="";
					if(n.typeid==1){
				tbody="<a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' class='img'>" +
						" <img src='"+image+"' alt='#' height='240' width='340'>" +
						" <i class='iconfont icon-play'></i>" +
						" <span class='mask'> <em class='time'>"+n.videolength+"</em></span></a>" +
								"<br> <div class='info'> <a class='change-title' style='margin-bottom:10px;'>" +
								""+n.videoname +"</a><i class='iconfont icon-play fl'>" +
								""+n.play+"</i> <i class='iconfont icon-collect fr'>"+n.collection+"</i></div>";
				$("#big1").append(tbody);
					}else if(n.typeid==2){
				tbody="<a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' class='img'>" +
						" <img src='"+image+"' alt='#' height='240' width='340'>" +
						" <i class='iconfont icon-play'></i>" +
						" <span class='mask'> <em class='time'>"+n.videolength+"</em></span></a>" +
								"<br> <div class='info'> <a class='change-title' style='margin-bottom:10px;'>" +
								""+n.videoname +"</a><i class='iconfont icon-play fl'>" +
								""+n.play+"</i> <i class='iconfont icon-collect fr'>"+n.collection+"</i></div>";
				$("#big2").append(tbody);
					}if(n.typeid==3){
				tbody="<a target='_blank' href='customer/vedioPlay.html?videoid="+n.videoid+"' class='img'>" +
						" <img src='"+image+"' alt='#' height='240' width='340'>" +
						" <i class='iconfont icon-play'></i>" +
						" <span class='mask'> <em class='time'>"+n.videolength+"</em></span></a>" +
								"<br> <div class='info'> <a class='change-title' style='margin-bottom:10px;'>" +
								""+n.videoname +"</a><i class='iconfont icon-play fl'>" +
								""+n.play+"</i> <i class='iconfont icon-collect fr'>"+n.collection+"</i></div>";
				$("#big3").append(tbody);
					}
			})
		}
	},
})
	
});
function isEmptyObject(e) {
	var t;
	for (t in e)
		return !1;
	return !0
}

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

//按类型“换一换”
function ranType(a){
	$.ajax({
		url : 'ranLimit.htm',
		type : 'POST',
		data:{
			typeid:a
		},
		dataType : 'json',
		async : false,
		success : function(data) {
			if (isEmptyObject(data)) {
			} else {
				$('#big1'+a).html("");
				$.each( data, function(i, n) {
				var tbBody="";
				var image=n.imgurl.replace(/\\/g, "/");
				if(a==4||a==5){
					if(i<10){
						tbBody ="<li class='area-menu__item'><a href='customer/vedioPlay.html?videoid="+n.videoid+"' target='_blank' class='img'>"+
						" <img src='"+image+"' alt='#'>" +
						" <span class='mask'> <em class='time'>"+n.videolength+"</em> </span> " +
						"<span class='icon-recommend'>独家推荐</span> </a>" +
						" <div class='info'> <a class='change-title'>"+n.videoname+"</a>" +
						" <span class='play-info clearfix'> <i class='iconfont icon-play fl'>"+n.play+"</i>" +
						" <i class='iconfont icon-collect fr'>"+n.collection+"</i> </span> </div> </li>";
					}}else{
				if(i<11){
					tbBody ="<li class='area-menu__item'><a href='customer/vedioPlay.html?videoid="+n.videoid+"' target='_blank' class='img'>"+
					" <img src='"+image+"' alt='#'>" +
					" <span class='mask'> <em class='time'>"+n.videolength+"</em> </span> " +
					"<span class='icon-recommend'>独家推荐</span> </a>" +
					" <div class='info'> <a class='change-title'>"+n.videoname+"</a>" +
					" <span class='play-info clearfix'> <i class='iconfont icon-play fl'>"+n.play+"</i>" +
					" <i class='iconfont icon-collect fr'>"+n.collection+"</i> </span> </div> </li>";
				}
				}
				$('#big1'+a).append(tbBody);
				})
			}
		},
	})
}
