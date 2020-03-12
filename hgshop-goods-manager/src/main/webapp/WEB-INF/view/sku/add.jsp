<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
    <!-- 添加sku -->
<div>
	<form id="addSkuFrm">
		<div class="form-row">
		    <div class="col">
		       <label>商品名称</label>	
		        <input type="hidden" name="spuId" value="${spu.id}">
		        <input type="text" disabled="disabled" value="${spu.goodsName}" name="goodsName" class="form-control" placeholder="商品名称">
		    </div>
		  </div>
		  
		  <div class="form-row">
		    <div class="col">
		       <label>标题</label>	
		        <input type="text" name="title" class="form-control" placeholder="标题">
		    </div>
		  </div>
		  
		   <div class="form-row">
		    <div class="col">
		       <label>卖点</label>	
		        <input type="text" name="sellPoint" class="form-control" 
		        placeholder="卖点">
		    </div>
		  </div>
		  
		  <div class="form-row">
		    <div class="col">
		       <label>价格</label>	
		        <input type="text" name="price" class="form-control" 
		        placeholder="价格">
		    </div>
		  </div>
		  <div class="form-row">
		     <div class="col">
		       <label>库存</label>	
		        <input type="text" name="stockCount" class="form-control" 
		        placeholder="库存">
		    </div>
		  </div>
		  <div class="form-row">
		    <div class="col">
		       <label>成本价格</label>	
		        <input type="text" name="costPrice" class="form-control" 
		        placeholder="成本价格">
		    </div>
		  </div>
		   <div class="form-row">
		    <div class="col">
		       <label>市场价格</label>	
		        <input type="text" name="marketPrice" class="form-control" 
		        placeholder="市场价格">
		    </div>
		  </div>
		  
		  
		  <br>
		  <!-- 一个属性，用于追加属性保存其中的html -->
		  <div id="oneSpec" style="display:none"> 
		    <div class="form-row">
			    <div class="col">
			       <label>属性</label>
			       <select  name="specIds" class="form-control form-control-lg"
			       onchange="specChange($(this))">
					 	<c:forEach items="${specs}" var="spec" >
					 		<option value="${spec.id}" >${spec.specName}</option>
					 	</c:forEach>
					</select>
			    </div>
			    <div class="col">
			       <label>属性选项（数值）</label>	
			       <select  name="specOptionIds" class="form-control form-control-lg">
					 	
					</select>
			    </div>
			    <div class="col">
			     <label>  </label><br> 
			    	<button type="button" class="btn btn-danger" onclick="delOption($(this))">删除</button>
			    </div>
			  </div>
			  
			  </div>
		  <div id="speclList">
			  
		  </div>
		  
		  <div>
		  	 <button type="button" class="btn btn-danger" onclick="addSpec()">增加属性</button>
		  </div>
		   
		    <div class="form-row">
		    <div class="col">
		       <label>小图</label>	
		      <input type="file" name="file" class="form-control-file" id="exampleFormControlFile1">
		    </div>
		  </div>
		  
  	  	<div class="form-row">
		    <div class="col">
				<button type="button" class="btn btn-primary"  onclick="commitData()">添加 </button>
   			</div>
   		</div>
   	
	</form>
</div> 

<script>
	function addSpec(){
		$("#speclList").append($("#oneSpec").html())
	}
	
	function specChange(specSel) {
		// 获取一个属性的所有的属性数值
		$.post("/spec/getSpec",{id:specSel.val()},function(specData){
			// 属性值的下拉框
			var specOption = specSel.parent().next().children().filter("select").first();
			specOption.empty()
			for (var i = 0; i < specData.options.length; i++) {
				//obj.parent().next().children().filter("input").first().val(obj.val()+"aaa");
				specOption.append("<option value='"+specData.options[i].id+"' >" +specData.options[i].optionName +"</option>")
				
				//console.log(specData.options[i].id)
			} ;
			
		});
	}
	
	function commitData(){
		
		var formData = new FormData($("#addSkuFrm")[0])
		//提交数据
		$.ajax({url:"/goods/addSku",
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
						 //刷新
						 $("#main").load("/goods/skulist");
					 }else{
						 alert("数据保存失败");
					 }  
				 
			  }
			  })
	}
	
	//删除当前的属性
	function delOption(delBtn){
		//
		delBtn.parent().parent().remove();
	}
</script>