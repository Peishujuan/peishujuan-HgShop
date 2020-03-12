<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@  taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

 <div class="container">
 		<div class="row">
 			<div class="col-md-3">
 				<label>名称</label>
 				<label>${sku.title}</label>
 			</div>
 		</div>
 		<div class="row">
 			<div class="col-md-3">
 				<label>价格</label>
 				<label>${sku.price}</label>
 			</div>
 		</div>
 		
 		<div class="row">
 			以下是规格
 		</div>
 		<c:forEach items="${sku.specs}" var="op"> 
 			<div class="row">
	 			<div class="col-md-3">
	 				<label>${op.specName}:</label>
	 				<label>${op.optionName}</label>
	 			</div>
 			</div>
 		</c:forEach>
 		
 </div>