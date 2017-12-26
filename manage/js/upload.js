function judge(){
	if(document.getElementById("songfile").value.length>0){
		document.getElementById('add').action="uploadvideo.htm";
	}else{
		toastr.warning("请上传视频文件");
		return  false;
	}
}
var songurl = "";  
function showSong(node) {
	var f = document.getElementById("songfile").files;  
	document.getElementById("filesize").value=f[0].size;
	document.getElementById("format").value=f[0].type;
	var arr = ["avi","mov","asf","wmv","rm","rmvb","mp4"];
	var judge="no";
	for(var i=0;i<arr.length;i++){			
		if(node.value.indexOf(arr[i])!=-1){
			judge="yes";
		}
	}
	if(judge=="no"){  
		  var file=document.getElementById('songfile');
          if(file.outerHTML){
                 file.outerHTML=file.outerHTML;
          }
          else{ 
                file.value="";
          }
		document.getElementById("filesize").value="";
		document.getElementById("format").value="";
		toastr.warning("请上传视频文件！");
	}else{
	var f = document.getElementById("songfile").files; 
	if(f[0]!=null&&f[0]!=""){
	var songsize=(f[0].size/(1024*1024)).toFixed(2)+"M";
		document.getElementById("filesize").value=songsize;
		document.getElementById("format").value=f[0].type;
	}
    var songURL = "";      
    try{     
        var file = null;  
        if(node.files && node.files[0] ){  
            file = node.files[0];   
        }else if(node.files && node.files.item(0)) {                                  
            file = node.files.item(0);     
        }     
        //Firefox 因安全性问题已无法直接通过input[file].value 获取完整的文件路径  
        try{  
            //Firefox7.0   
            songURL =  file.getAsDataURL();    
            //alert("//Firefox7.0"+imgRUL);                           
        }catch(e){  
            //Firefox8.0以上                                
            songURL = window.URL.createObjectURL(file);  
            //alert("//Firefox8.0以上"+imgRUL);  
        }  
     }catch(e){      //这里不知道怎么处理了，如果是遨游的话会报这个异常                   
        //支持html5的浏览器,比如高版本的firefox、chrome、ie10  
        if (node.files && node.files[0]) {                            
            var reader = new FileReader();   
            reader.onload = function (e) {                                        
            	songURL = e.target.result;    
            };  
            reader.readAsDataURL(node.files[0]);   
        }    
     }  
    //imgurl = imgURL;  
    creatSong(songURL);  
    return songURL; 
	}
} 
function creatSong(songRUL){   
	$("#song_url").attr('src',songRUL);
} 
function getlength(ele) {  
	document.getElementById("videolength").value=Math.floor(ele.duration)+"s";
}  

