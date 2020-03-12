<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   


	<div>
		<button type="button" class="btn btn-danger" onclick="createOrder()">下订单</button>
	</div>
   <table class="table">
	<tr>
		<th>id</th>
		<th>商品名称</th>
		<th>购买数量</th>
		<th>总价格</th>
		<th>加入时间</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${pageInfo.list}" var="cart">
		<tr>
			<td><input type="checkbox" name="cartId" value="${cart.id}">${cart.id}</td>
			<td>${cart.title}</td>
			<td>${cart.pnum}</td>
			<td>${cart.sumTotal}</td>
			<td></td>
			<td><button type="button" class="btn btn-success">详情</button>
				<button type="button" class="btn btn-danger">删除</button>
			</td>
		</tr>
	</c:forEach>
</table>   

<script>

	function createOrder(){
		//构建已经被选中的数组
		var cartIds = new Array();
		$("[name=cartId]:checked").each(function(){
			cartIds.push($(this).val());
		})
		
		if(cartIds.length<=0){
			alert('请选择一个订单');
			return;
		}
		
		var address = prompt("请输入邮寄地址","eg.北京市海淀区...");
		if(!address){
			return;
		}
		
		$.post('/user/addorder',{cartIds:cartIds,address:address},function(msg){
			if(msg=="success"){
				alert('下单成功')
				refresh()
			}else{
				alert(msg)
			}
		})
	}
	
	function refresh(){
		
	}
</script>