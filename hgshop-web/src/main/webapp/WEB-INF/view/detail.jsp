<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>$$豪哥商城系统$$</title>

    <!-- Bootstrap core CSS -->
	<link href="/resource/bootstrap4/css/bootstrap.css" rel="stylesheet" >



    <!-- Favicons -->
<link rel="apple-touch-icon" href="https://v4.bootcss.com/docs/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="https://v4.bootcss.com/docs/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="https://v4.bootcss.com/docs/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="https://v4.bootcss.com/docs/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="https://v4.bootcss.com/docs/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
<link rel="icon" href="https://v4.bootcss.com/docs/assets/img/favicons/favicon.ico">
<script type="text/javascript" src="/resource/jquery/jquery-3.4.1.js"></script>
<script type="text/javascript" src="/resource/bootstrap4/js/bootstrap.js"></script>

<meta name="msapplication-config" content="/docs/assets/img/favicons/browserconfig.xml">
<meta name="theme-color" content="#563d7c">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="/resource/css/dashboard.css"  rel="stylesheet">
    
  <style type="text/css">/* Chart.js */
	@-webkit-keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}@keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}.chartjs-render-monitor{-webkit-animation:chartjs-render-animation 0.001s;animation:chartjs-render-animation 0.001s;}</style></head>
  <body>
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
  <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Company name</a>
  <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
  <ul class="navbar-nav px-3">
    <li class="nav-item text-nowrap">
      
    </li>
  </ul>
</nav>

<div class="container" style="margin-top:80px">
	<div class="row">
		<div class="col-md-6">
			<img  width="400" height="400" alt="" src="/pic/${spu.smallPic}">
		</div>
		<div>
			<div>${spu.goodsName} </div>
			<div style="width: 350px;height: 40px">
				<font color="red" size="5px">${spu.caption}</font> 
			</div>
			<!-- 以下显示sku -->
			<div>
				<c:forEach items="${skus }" var="sku">
					<div style="border:1px solid rgb(255,0,0); margin:4px" onclick="selSku(${sku.id},${sku.price})">
						<c:forEach items="${sku.specs }" var="spec"> 
							&nbsp;${spec.specName }：${spec.optionName }<br>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
			
			<!-- 显示 sku的买点等 -->
			<div id="skuDetail">
				
			</div>
			
			
			<!-- 加入购物车 -->
			<div>
				购买数量:<input type="number" id="buyNum">
				<button type="button" class="btn btn-danger btn-lg" onclick="addCart()">加入购物车</button>
			</div>
		</div>
	</div>
</div>

</body>
<script type="text/javascript">
var buySkuId=0;

function addCart(){
	$.post("/user/addCart",{skuId:buySkuId,buyNum:$("#buyNum").val()},function(msg){
		if(msg=="success"){
			alert("购买成功");
		}else{
			alert(msg)
		}
	});
}


function selSku(id,price){
	$("#skuDetail").html("价格：" + price)
	buySkuId=id
	
	
	
}
	
</script>

</html>
