var cookieUtil = {
  //添加cookie    name是key   value是key对应的值   expires 过期时间
  setCookie: function (name, value, expires) {
	//通过encodeURIComponent字符串作为 URI 组件进行编码  name=value
    var cookieText = encodeURIComponent(name) + "=" + encodeURIComponent(value);
    //设置过期时间
    if (expires && expires instanceof Date) {
        cookieText += "; expires=" + expires;
    }
    //设置cookie对象
    document.cookie = cookieText;
  },
  //获取cookie
  getCookie: function (name) {
	//函数可对 encodeURIComponent() 函数编码的 URI 进行解码
    var cookieText = decodeURIComponent(document.cookie);
    //name=value;path=/;expire=; 对cookie按照;拆分
    var cookieArr = cookieText.split("; ");
    //遍历cookie的所有的值
    for (var i = 0; i < cookieArr.length; i++) {
      //name=value  path=/   expire=
      //再以=好拆分
      var arr = cookieArr[i].split("=");
      //name
      if (arr[0] == name) {
    	//返回cookie
        return arr[1];
      }
    }
    //返回空值
    return null;
  },
  //删除cookie
   deleteCookie:function (name) {
	//设置cookie的过期时间为0
    document.cookie = encodeURIComponent(name) + "=; expires=" + new Date(0);
  }
};
//根据cookie名称获取指定的cookie 但此cookie是数组
function getCartCookie(name){
	return cookieUtil.getCookie(name) ? JSON.parse(cookieUtil.getCookie(name)) : [];
}