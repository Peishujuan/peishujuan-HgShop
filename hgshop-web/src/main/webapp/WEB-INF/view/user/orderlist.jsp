<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   



   <table class="table">
	<tr>
		<th>id</th>
		<th>地址</th>
		<th>总价</th>
		<th>下单时间</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${pageInfo.list}" var="order">
		<tr>
			<td><input type="checkbox" name="cartId" value="${order.oid}">${order.oid}</td>
			<td>${order.address}</td>
			<td>${order.sumtotal}</td>
			<td>${order.createTime}</td>
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
			alert('请选择数据');
			return;
		}
		
		var address = prompt("请输入邮寄地址","北京");
		if(!address){
			return;
		}	
		
		
		$.post('/user/addorder',{cartIds:cartIds,address:address},
				function(msg){
			if(msg=="success"){
				alert('下订单成功')
				refresh()
			}else{
				alert(msg)
			}
		})
	}
	
	function refresh(){
		
	}
</script>