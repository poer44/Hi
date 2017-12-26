/**
 * Created by Administrator on 2017/4/28.
 */
'user strict';

window.onload = function () {
    var videoid={"videoid":$('#getUrlData').val()};
    var fileurl=new Array();
    var videoname="";
    var authorimg="";
    var reg=/^[0-9]*$/;
    if($('#getUrlData').val()!=""&&reg.test($("#getUrlData").val())){
	$.ajax({
		url : "getvideo.htm",
		data : videoid,
		async : false,
		success: function(video){
			if(video.msg=="success"){
				fileurl[0]="../"+video.fileurl.replace(/\\/g, '/');
				videoname=video.videoname;
				$("#videoname").html(video.videoname)
				var typename="";
				if(video.typeid==1)typename="娱乐";
				else if(video.typeid==2)typename="游戏";
				else if(video.typeid==3)typename="动画";
				else if(video.typeid==4)typename="音乐";
				else if(video.typeid==5)typename="舞蹈";
				$("#typename").html("类型>"+typename);
				$("#author").html(video.author);
				if(video.author.indexOf("管")==-1){
					var userid=video.author.split("户")[1];
							$.ajax({
								url : "../playVideoAuthor.htm",
								data : {
									userid:userid
								},
								type : 'POST',
								async : false,
								success: function(user){
									$('#userim').attr('src',"../"+user.imgurl);
								}
						});
					}
				$("#uptime").html("发布时间："+video.uptime.substring(0,4)+"-"+video.uptime.substring(4,6)+"-"+video.uptime.substring(6,8)+" "+video.uptime.substring(8,10)+":"+video.uptime.substring(10,12)+":"+video.uptime.substring(12,14));
				$("#play").html(video.play);
				$("#collection").html(video.collection);
			}else{
				toastr.error("该视频不存在");
				$("#commentarea").css("display","none");
				$("#v2").css("display","none");
			}
		}
	});	
		$.ajax({
			url : "getvideocomment.htm",
			data : videoid,
			async : false,
			success: function(videocomment){
				$("#commentcount").html(videocomment.length);
			}
		});	
		$.ajax({
			url : "getbarrage.htm",
			data : videoid,
			async : false,
			success: function(barrage){
				$("#barrage").html(barrage.length);
			}
		});
    }
   var video = $('#video1').videoCt({
       title: videoname,              //标题
       volume: 0.2,                //音量
       barrage: true,              //弹幕开关
       comment: true,              //弹幕
       reversal: true,             //镜像翻转
       playSpeed: true,            //播放速度
       update: true,               //下载
       autoplay: false,            //自动播放
       clarity: {
           type: ['360P','480P'],            //清晰度
           src: fileurl    //链接地址
       },
       commentFile: 'comment.json'           //导入弹幕json数据
   });
    //扩展
    video.title;                    //标题
    video.status;                   //状态
    video.currentTime;              //当前时长
    video.duration;                 //总时长
    video.volume;                   //音量
    video.clarityType;              //清晰度
    video.claritySrc;               //链接地址
    video.fullScreen;               //全屏
    video.reversal;                 //镜像翻转
    video.playSpeed;                //播放速度
    video.cutover;                  //切换下个视频是否自动播放
    video.commentTitle;             //弹幕标题
    video.commentId;                //弹幕id
    video.commentClass;             //弹幕类型
    video.commentSwitch;            //弹幕是否打开
    $('#video1').bind('ended', function() {
        console.log('弹幕json数据：\n'+ video.comment());              //获取弹幕json数据
    });
}
