<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@  taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 


<div class="container-fluid"> 
	  <table class="table">
	  	<tr>	

	  	
	  		<th>id</th>
	  		<th>标题</th>
	  		<th>价格</th>
	  		<th>库存</th>
	  		<th>发布时间</th>
	  		<th>成本价</th>
	  		<th>品牌</th>
	  		<th>分类</th>
	  		<th>操作</th>
	  	</tr>
	  	<c:forEach items="${pageInfo.list}" var="sku">
	  		<tr>
	  			<td>${sku.id}</td>
	  			<td>${sku.title}</td>
	  			<td>${sku.price}</td>
	  			<td>${sku.stockCount}</td>
	  			<td>
	  				<fmt:formatDate  value="${sku.createTime}" pattern="yyyy-MM-dd"/> 
	  			
	  			</td>
	  			
	  			<td>${sku.costPrice}</td>
	  			<td>${sku.spu.brand.name}</td>
	  			<td>${sku.spu.category.name}</td>
	  			<td>
		  			<button type="button" class="btn btn-success" onclick="goDetail(${sku.id})">详情</button>
					<button type="button" class="btn btn-danger">删除</button>
					<button type="button" class="btn btn-warning">修改</button>
	  			</td>
	  		</tr>
	  	</c:forEach>
	  </table>
</div>

<script>
	function goDetail(id){
		$("#main").load("/goods/skuDetail?id="+id)
	}
</script>