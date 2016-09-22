<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试上传</title>
	<script type="text/javascript" src="${ctx}/resource/system/common/js/jquery-1.10.1.min.js" ></script>
	<script type="text/javascript" src="${ctx}/resource/module/plupload/js/plupload.full.min.js" ></script>
	<script type="text/javascript" src="${ctx}/resource/module/plupload/js/moxie.min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/module/qiniu/dist/qiniu.min.js"></script>
</head>
<body>
	   <form>
	   		<input type="file" name="uploadfile" id="uploadfile">
	   </form>
	   <button id="selectImage"></button>
</body>
<script>
	function getUptoken(){
		
	}


	var upload = Qiniu.uploader({
		runtimes : 'html5',
		browse_button: 'selectImage', //上传选择的的点选按钮 ???
		//uptoken : 'upload token',  //由后台程序获取
		//uptoken_url:  //ajax请求uptoken的Url
		uptoken_func:function(file) {
			
		},
		get_new_uptoken:false,  //设置上传那文件时，是否每次都重新获取新的uptoken
		//downtoken_url: '/downtoken', //私有空间使用
		unique_names:true,		//默认false，key为文件名。 若开启该选项js-sdk会为每个文件自动生成key(文件名)
		//save_key:true, 			//默认false,
		//domain:'your bucket domain',		//bucket域名下载资源时用到
		container: 'container',		//上传区域Dom ID,默认是browser_button的父元素
		max_file_size:'100mb',		//最大文件体积限制
		max_retries:3,		//上传失败最大重试次数
		dragdrop:true,		//开启可拖拽上传
		drop_element:'container',	//拖拽上传区域元素的Id,拖拽文件或文件夹后可触发上传
		chunk_size: '4mb',	//分块上传时，每块的体积
		auto_start:true,	//选择文件后自动上传，若关闭需要自己绑定事件触发上传
		//x_vars : {
			//'time' :function(up,file){
				
			//},
			//'size' :function(up,file) {
				
			//}
		//},
		init:{
			'FilesAdded':function(up,files) {
				plupload.each(files,function(file){
					//文件添加进队列胡，处理相关的事情
				});				
			},
			'BeforeUpload':function(up,file) {
					//每个文件上传前，处理相关的事情
			},
			'UploadProgress':function(up,file){
					//每个文件上传时，处理相关事情
			},
			'FileUploaded':function(up,file,info) {
					//每个文件上传成功后，处理相关的事情
					//其中info是文件上传成功后，服务端返回的json,形如：
					//{
					//	"hash":"Fh8xVqod2MQ1mocfI4S4KpRL6D98",
					//	"key":"gogoher.jpg"
					//}
					var domain = up.getOption('domain');
					var res = parseJSON(info);
					var sourceLink = domain + res.key; //获取上传成功后的文件的url
					
			},
			'Error':function(up,err,errTip) {
					//上传出错时，处理相关的事情
			},
			'UploadComplete':function(){
					//队列文件处理完毕后，处理相关的事情
			},
			'Key':function(up,file) {
					//若想在前端对每个文件的key进行个性化处理，可以配置该函数
					//该配置必须要在unique_names:fales,save_key:false时才生效
					var key = "";
					//do something with key here
					return key
			}
			//domain为七牛空间对应的域名，选择某个空间后，可通过 空间设置->基本设置->域名设置 查看获取
			//uploader为一个plupload对象，继承了所有plupload的方法
		} 
	})
</script>
</html>