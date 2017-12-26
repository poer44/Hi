$(function(){
	// 全局错误提醒
	$.validator.setDefaults({    
        highlight : function(element,errorClass){  
            $(element).parents('.form-group').removeClass('has-success has-feedback');
            $(element).parents('.form-group').addClass('has-error has-feedback');
            if($(element).parents('.form-group').find("span:empty").prev().prop('tagName')!='I'){
                $(element).parents('.form-group').find("span:empty").removeClass('glyphicon glyphicon-ok form-control-feedback');
                $(element).parents('.form-group').find("span:empty").addClass('glyphicon glyphicon-exclamation-sign form-control-feedback');
            }
            else if($(element).parents('.form-group').find("span:empty").prev().prop('tagName')=='I'){
                $(element).parents('.form-group').find("i").show();
                $(element).parents('.form-group').find("span:empty").hide();
            }      
            setTimeout(function(){
            	$(element).parent().find("label").addClass('control-label');
            },0); 
        },
        unhighlight : function(element,errorClass){
        	$(element).parents('.form-group').removeClass('has-error has-feedback');
            $(element).parents('.form-group').addClass('has-success has-feedback');
            if($(element).parents('.form-group').find("span:empty").prev().prop('tagName')!='I'){
                $(element).parents('.form-group').find("span:empty").removeClass('glyphicon glyphicon-exclamation-sign form-control-feedback');
                $(element).parents('.form-group').find("span:empty").addClass('glyphicon glyphicon-ok form-control-feedback');        
            }
            else if($(element).parents('.form-group').find("span:empty").prev().prop('tagName')=='I'){
                $(element).parents('.form-group').find("i").hide();
                $(element).parents('.form-group').find("span:empty").addClass('glyphicon glyphicon-ok form-control-feedback').show();       
            }
        },
        focusInvalid : false,
    });

    //密码眼睛关闭睁开
    $("i.glyphicon").click(function(){
        if($(this).hasClass("glyphicon-eye-close")){
            $(this).removeClass("glyphicon-eye-close").addClass('glyphicon-eye-open');
            $(this).parent().find("input").prop("type","text")
        }
        else{
            $(this).removeClass("glyphicon-eye-open").addClass('glyphicon-eye-close');
             $(this).parent().find("input").prop("type","password")
        }
    });
    


   


   





})











	
