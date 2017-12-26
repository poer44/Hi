function getRootPath(){
	var curPath = location.href;   //	http://localhost:8088/SSM_1_JSON/index.jsp
	var pathName = location.pathname;   //	SSM_1_JSON/index.jsp
	var pos = curPath.indexOf(pathName);  
	var localhostPath = curPath.substring(0, pos);   //	http://localhost:8088/
	var projectName = pathName.substring(0, pathName.substr(1).indexOf("/")+1);  //  SSM_1_JSON
	return (localhostPath+projectName);    //	http://localhost:8088/SSM_1_JSON
}
//jquery
document.write('<script type="text/javascript" src="'+getRootPath()+'/customer/js/jquery.min.js"></script>');
//bootstrap
document.write('<script type="text/javascript" src="'+getRootPath()+'/customer/js/bootstrap.js"></script>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'/customer/css/bootstrap.css" type="text/css"></link>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'/customer/css/bootstrap-theme.css" type="text/css"></link>');
//bootstrap-table
document.write('<script type="text/javascript" src="'+getRootPath()+'/customer/js/bootstrap-table.js"></script>');
document.write('<script type="text/javascript" src="'+getRootPath()+'/customer/js/bootstrap-table-zh-CN.js"></script>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'/customer/css/bootstrap-table.css" type="text/css"></link>');
//rest风格插件
document.write('<script type="text/javascript" src="'+getRootPath()+'/customer/js/restful.js"></script>');
//弹框插件（alert、confir、dialog）
document.write('<script type="text/javascript" src="'+getRootPath()+'/customer/js/tip.js"></script>');
//消息提示框
document.write('<script type="text/javascript" src="'+getRootPath()+'/customer/js/toastr.js"></script>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'/customer/css/toastr.css" type="text/css"></link>');

