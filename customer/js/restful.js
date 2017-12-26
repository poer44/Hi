/**
 * 将form里面的内容序列化成json
 * 相同的checkbox用分号拼接起来
 * @param {dom} 指定的选择器
 * @param {obj} 需要拼接在后面的json对象
 * @method serializeJson
 * */
//$.fn等价于 jQuery.prototype。所以该用法用于给每个jQuery对象添加属性及方法
//以下面内容为例：即可以做到针对任意的jQuery对象：$(选择器).serializeJson(..)
$.fn.serializeJson=function(otherString){
	var serializeObj={},
	array=this.serializeArray();
	$(array).each(function(){
		if(serializeObj[this.name]){
			serializeObj[this.name]+=';'+this.value;
		}else{
			serializeObj[this.name]=this.value;
		}
	});

	if(otherString!=undefined){
		var otherArray = otherString.split(';');
		$(otherArray).each(function(){
			var otherSplitArray = this.split(':');
			serializeObj[otherSplitArray[0]]=otherSplitArray[1];
		});
	}
	return serializeObj;
};

/**
 * 将josn对象赋值给form
 * @param {dom} 指定的选择器
 * @param {obj} 需要给form赋值的json对象
 * @method serializeJson
 * */
$.fn.setForm = function(jsonValue){
	var obj = this;
	if(jsonValue){
		$.each(jsonValue,function(name,ival){
			var $oinput = obj.find("input[name="+name+"]");
			if($oinput.attr("type")=="checkbox"){
				if(ival !== null){
					var checkboxObj = $("[name="+name+"]");
					var checkArray = ival.split(";");
					for(var i=0;i<checkboxObj.length;i++){
						for(var j=0;j<checkArray.length;j++){
							if(checkboxObj[i].value == checkArray[j]){
								checkboxObj[i].click();
							}
						}
					}
				}
			}
			else if($oinput.attr("type")=="radio"){
				$oinput.each(function(){
					var radioObj = $("[name="+name+"]");
					for(var i=0;i<radioObj.length;i++){
						if(radioObj[i].value == ival){
							radioObj[i].click();
						}
					}
				});
			}
			else if($oinput.attr("type")=="textarea"){
				obj.find("[name="+name+"]").html(ival);
			}
			else{
				obj.find("[name="+name+"]").val(ival);
			}
		});
	}else{
		console.log("your parameter is undefined or is null");
	}
};

/**
 * 发起rest请求
 * @param newurl 服务地址
 * @param model 参数
 * @method callback 回调函数
 * */
//$.extend不等于$.fn.extend, 这两种方式的共同点都是用于开发jQuery插件
//不同点：前者开发的插件更像是静态属性或方法，可以做到直接由jQuery调用
//以下面内容为例：即可以这么用：$.add(function(xx,xx,xx){...})
$.extend({
	add : function(newurl, model, callback) {  
		$.ajax({  
			type: 'POST',  
			url: newurl,  
			data: JSON.stringify(model), // '{"name":"' + model.name + '"}',  
			dataType: 'text',
			processData: false,  
			contentType: 'application/json',  
			success: function(data){callback(isJSON(data)?$.parseJSON(data):data);},  
			error: function(req, status, ex) {toastr.error("操作异常！");},  
			timeout:60000  
		});  
	},

	edit : function(newurl, model, callback) {  
		$.ajax({  
			type: 'PUT',  
			url: newurl,  
			data: JSON.stringify(model), // '{"name":"' + model.name + '"}',  
			dataType: 'text',  
			processData: false,  
			contentType: 'application/json',  
			success: function(data){callback(isJSON(data)?$.parseJSON(data):data);},  
			error: function(req, status, ex) {toastr.error("操作异常！");},  
			timeout:60000  
		});  
	},

	queryById : function(newurl, id, callback) {  
		$.ajax({  
			type: 'GET',  
			url: id + '/' + newurl,  
			contentType: 'application/json',  
			success: function(data){callback(isJSON(data)?$.parseJSON(data):data);},  
			error: function(req, status, ex) {toastr.error("操作异常！");},  
			timeout:60000  
		});  
	},

	query : function(newurl, callback) {  

		$.ajax({  
			type: 'GET',  
			url: newurl,  
			contentType: 'application/json',  
			success: function(data){callback(isJSON(data)?$.parseJSON(data):data);},
			error: function(req, status, ex) {toastr.error("操作异常！");},  
			timeout:60000  
		});  
	},

	del : function(newurl, id, callback) {  
		$.ajax({  
			type: 'DELETE',  
			url:  id + '/' + newurl,  
			contentType: 'application/json',  
			success: function(data){callback(isJSON(data)?$.parseJSON(data):data);}, 
			error: function(req, status, ex) {toastr.error("操作异常！");},  
			timeout:60000  
		});  
	},

	loadTmpl : function(newurl, callback) {  
		$.ajax({  
			url: newurl,  
			success: function(data){callback(isJSON(data)?$.parseJSON(data):data);},  
			error: function(req, status, ex) {toastr.error("操作异常！");},  
			timeout:60000  
		});
	}

});

function isJSON(str) {
    if (typeof str == 'string') {
        try {
            var obj=JSON.parse(str);
            if(str.indexOf('{')>-1){
                return true;
            }else{
                return false;
            }

        } catch(e) {
            console.log(e);
            return false;
        }
    }
    return false;
}