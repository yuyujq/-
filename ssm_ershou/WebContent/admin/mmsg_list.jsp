<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>订单列表</title>
<link rel="stylesheet" href="css/bootstrap.css"/> 
<script type="text/javascript" src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container-fluid">
	<%@include file="header.jsp" %>
		<div>
			<form class="form-inline" style="float: left; margin-right: 5px"
				method="post" action="searchmmsg?status=${status}">
				<div class="form-group">
					<input type="number" class="form-control" name="id"
				placeholder="请输入商品ID" style="width: 180px;-moz-appearance: textfield;" required="required">
				</div>
				<button type="submit" class="btn btn-primary" style="width: 82px;">搜索留言</button>
			</form>
		</div>
		
		<br>
		<br>
		<br>
	
	<ul role="tablist" class="nav nav-tabs">
        <li <c:if test='${status==1}'>class="active"</c:if> role="presentation"><a href="mmsgList?status=1">未回复</a></li>
        <li <c:if test='${status==2}'>class="active"</c:if> role="presentation"><a href="mmsgList?status=2">已回复</a></li>
    </ul>
    
    <br>
	
	<table class="table table-striped ">

	<tr>
		<th width="10%" style="text-align:center;">商品ID</th>
		<th width="80%" style="text-align:center;">留言问题</th>
		<th width="10%" style="text-align:center;">操作</th>

	</tr>
	
	<c:forEach var="order" items="${tixingList}">
         <tr>
         	<td style="text-align:center;"><p>${order.amount}</p></td>
         	<td style="text-align:center;"><p>${order.name}</p></td>
			<td style="vertical-align:middle;">
			
				<a class="btn btn-success" href="changemmsg?id=${order.id}&status=${status}">回复</a>
			
				<a class="btn btn-danger" href="delmmsg?id=${order.id}&status=${status}">删除</a>
			</td>
       	</tr>
	</c:forEach>
     
</table>
     <div style="text-align:center">
     <c:if test="${Npage==1}"><h3  style="width:100%;height:200px;vertical-align:middle;">暂无相关搜索结果</h3></c:if>
     </div>
<br>${pageTool}<br>
</div>
</body>
</html>