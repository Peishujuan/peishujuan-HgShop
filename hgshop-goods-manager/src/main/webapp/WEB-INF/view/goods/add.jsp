<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
    
<link href="/resource/css/bootstrap.css" rel="stylesheet" >    
<link href="/resource/bootstrap-treeview/css/bootstrap-treeview.css" rel="stylesheet" >    
<script src="/resource/bootstrap-treeview/js/bootstrap-treeview.js"></script>    

<div>
	<form id="addSpuFrm">
		<div class="form-row">
		    <div class="col">
		       <label>商品名称</label>	
		        <input type="text" name="goodsName" class="form-control" placeholder="商品名称">
		    </div>
		  </div>
		  
		  <div class="form-row">
		    <div class="col">
		       <label>标题</label>	
		        <input type="text" name="caption" class="form-control" placeholder="标题">
		    </div>
		  </div>
		  
		  <div class="form-row">
		    <div class="col">
		       <label>分类</label>	
		        <input type="hidden" id="categoryId" name="categoryId">
		        <input type="text" name="categoryName" id="category"
		          onclick="showTree()"
		         class="form-control" placeholder="分类">
		    </div>
		    <div id="addCategoryTree"
				style="display: none; position: absolute; z-index: 1010; background-color: white; width: 96%">
				</div>
		  </div>
		  
		  <div class="form-row">
		    <div class="col">
		       <label>品牌</label>	
		       <select id="brandId" name="brandId" class="form-control form-control-lg">
				 	<c:forEach items="${brands}" var="brand">
				 		<option value="${brand.id}">${brand.name}</option>
				 	</c:forEach>
				</select>
		    </div>
		  </div>
		   
		    <div class="form-row">
		    <div class="col">
		       <label>小图</label>	
		      <input type="file" name="file" class="form-control-file" id="exampleFormControlFile1">
		    </div>
		  </div>
		  
  	  	<div class="form-row">
		    <div class="col">
				<button type="button" class="btn btn-primary"  onclick="add()">添加 </button>
   			</div>
   		</div>
   	
	</form>
</div>    
<script>
function initTree() {
	//发送ajax获取树需要的数据
	alert($.ajaxSettings.async=true)
	$.post("/cat/treeData", {},
			function(treeData) {
				//初始化添加的时候分类的树
				$("#addCategoryTree").treeview({
					data : treeData,
					levels : 2,
					onNodeSelected : function(event, node) {
						 if (node.nodes.length==0) {
							$("#category").val(node.text);
							$("#categoryId").val(node.id);
							$("#addCategoryTree").hide();
						}
						
					}
				});

			}, "json");
}
initTree();

function showTree(){
	$('#addCategoryTree').show()
}

function add() {
	
	// 获取表单数据
	var formData = new FormData($("#addSpuFrm")[0]);
	$.ajax({url:"/goods/add",
		  //dataType:"json",
		  data:formData,
		  // 让jQuery 不要再提交数据之前进行处理
		  processData : false,
		  // 提交的数据不能加消息头
		  contentType : false,
		  // 提交的方式 
		  type:"post",
		  // 成功后的回调函数
		  success:function(data){
			  if(data=="success"){
					 alert("数据提交成功");
					 refresh();
				 }else{
					 alert("数据保存失败");
				 }  
			 
		  }
		  })
}
function refresh(){
	$("#main").load("/goods/list");
}
</script>
