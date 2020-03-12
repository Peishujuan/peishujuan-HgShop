/**
 * 使用教程
 * html中定义：
 	<input type="file" id="fileImg" /> 
	<img src="" alt="" id="preImg"
			style="width: 100px; height: 100px; border-radius: 50%;">
   js中：
$(document).ready(function(){

	//注册input type="file"的改变事件
	$("#fileImg").change(function(){
		//取出第一个文件
		var file = this.files[0];
		var url = getFileUrl(file);
		//url地址就会被浏览器所识别
		$("#preImg").attr("src",url);
	
	});
   
});
</script>

 * 
 * 获取 输入框返回浏览器能够识别的url地址
 * @returns
 */
function getFileUrl(file){
	var url ="";
	//根据不同的浏览器处理 url地址
	if(window.createObjectURL!=undefined){ //basic
		 url = window.createObjectURL(file)
	}else if(window.URL!=undefined){//mozilla(firefox)  
		 url = window.URL.createObjectURL(file) ;  
	}else if(window.webkitURL!=undefined){ //webkit or chrome 
	   //针对chrome 浏览器
	  url = window.webkitURL.createObjectURL(file);
    }
	return url;
}