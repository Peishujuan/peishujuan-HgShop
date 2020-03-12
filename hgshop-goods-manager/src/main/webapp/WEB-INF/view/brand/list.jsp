<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>品牌管理列表</title>
</head>
<body>
<h3>品牌管理列表</h3>
<div>
	<input id="queryName" value="${queryName}">
	<button type="button" class="btn btn-primary" onclick="find()">
		查询
	</button>
	
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#staticBackdrop">
		添加
	</button>
	
<!--添加开始  -->
	<div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">添加品牌</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	<form action="" id="addbrand">
			<div class="form-group">
				<label for="specName">品牌名称</label> 
				<input type="text" class="form-control" id="name" name="name" aria-describedby="specNamelHelp">
				<small id="specNamelHelp" class="form-text text-muted">请输入品牌名称</small>
			</div>
			<div class="form-group">
				<label for="name">品牌首字母</label>
				<input type="text" class="form-control" name="firstChar" id="firstChar" aria-describedby="specNamelHelp">
				<small id="specNamelHelp" class="form-text text-muted">请输入品牌首字母</small>
			</div>
			<div class="form-group">
				<label for="inputAddress">数字</label>
				<input type="text" name="deleted_flag" class="form-control" id="inputAddress" aria-describedby="specNamelHelp">
				<small id="specNamelHelp" class="form-text text-muted">请输入品牌数字</small>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="commitBrand()">提交</button>
      </div>
    </div>
  </div>
</div>
	<!--添加结束  -->
	
	<!-- 修改 开始 -->
<div class="modal fade" id="staticBackdropupdate" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">修改品牌</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          	<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        		<form action="" id="updatespec">
        				<input type="hidden" name="id" id="updateid"><!-- id -->
						<div class="form-group">
							<label for="name">品牌名称</label> 
							<input type="text" class="form-control" id="updatname" name="name" aria-describedby="specNamelHelp">
								<small id="specNamelHelp" class="form-text text-muted">请输入品牌名称</small>
						</div>
						<div class="form-group">
							<label for="inputAddress">首字母</label>
							<input type="text" name="firstChar" class="form-control" id="firstChar11" placeholder="1234 Main St">
						</div>
						<div class="form-group">
							<label for="inputAddress">数字</label> 
							<input type="text" name="deletedFlag" class="form-control" id="deletedFlag" placeholder="1234 Main St">
						</div>
					</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="commitSpecUpdate()">提交</button>
      </div>
    </div>
  </div>
</div>
<!-- 修改 结束 -->
</div>

<table class="table">
  <tr>
    <th>id</th>
    <th>名称</th>
    <th>首字母</th>
    <th>数字</th>
  </tr>
  <c:forEach items="${pageInfo.list}" var="list">
  	<tr>
    <td>${list.id}</td>
    <td>${list.name}</td>
    <td>${list.firstChar}</td>
    <td>${list.deletedFlag}</td>
    <td>
    	<button type="button" class="btn btn-danger" onclick="delsec(${list.id})">删除</button>
		<button type="button" class="btn btn-warning" onclick="openUpdate(${list.id})">修改</button>
	</td>
  </tr>
  </c:forEach>
  <tr>
  	<td colspan="100">
  		<jsp:include page="../common/page.jsp"></jsp:include><!-- 分页 -->
	</td>
  </tr>
</table>
<script type="text/javascript">

/*查找*/
	function find(){
		var url="/brand/list?firstChar="+$("#queryName").val();
		$("#main").load(url);
	}
	
/*分页*/
function goPage(pagenum){
	var url="/brand/list?first_char="+$("#queryName").val()+'&pageNum='+pagenum;
	$("#main").load(url);
}
	
/*添加*/
function commitBrand(){
	$.post("/brand/add",
	$("#addbrand").serialize(),
	function(data){
		if(data){
			alert("成功");
			 $('#staticBackdrop').modal('hide');
		}else{
			alert("失败");
		} 
	})
}
		
//模态框关闭后 调用  添加的刷新
$("#staticBackdrop").on('hidden.bs.modal',function(e){
	refresh();
})
//模态框关闭后 调用  修改的刷新
$("#staticBackdropupdate").on('hidden.bs.modal',function(e){
	refresh();
})
	
/* 刷新 and 保持查询条件和页码 */
function refresh(){
	var url="/brand/list?name=${queryName}"+'&pageNum=${pageInfo.pageNum}';
	$("#main").load(url);
}

/*回显*/
function openUpdate(id){
	$.post("/brand/upda",
			{id:id},
			function(data){
				alert(data.firstChar)
				$("#updateid").val(data.id);
				$("#updatname").val(data.name);
				$("#firstChar11").val(data.firstChar);
				$("#deletedFlag").val(data.deletedFlag);
				
			},"json");
	$("#staticBackdropupdate").modal('show');
}
		
		function commitSpecUpdate(){//修改
			$.post("/brand/upd",
				$("#updatespec").serialize(),
				function(data){
					if(data){
						alert("成功");
						 $('#staticBackdropupdate').modal('hide');
					}else{
						alert("失败");
					} 
				})
			}

/*删除*/
function delsec(id){
	if(confirm("确定删除数据")){
		$.post("/brand/delspec",
				{id:id},
				function(data){
					if(data){
						alert("成功");
						refresh();
					}else{
						alert("失败")
					}
				})
	}else{
		return;
	}
}
</script>