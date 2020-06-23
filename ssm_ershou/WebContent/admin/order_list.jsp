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
				method="post" action="orderSel">
				<div class="form-group">
					<input type="number" class="form-control" name="id"
				placeholder="请输入订单ID" style="width: 180px;-moz-appearance: textfield;" required="required">
				</div>
				<button type="submit" class="btn btn-primary" style="width: 82px;">搜索订单</button>
			</form>
		</div>
		
		<br>
		<br>
		<br>
	
	<ul role="tablist" class="nav nav-tabs">
<%--         <li <c:if test='${status==0}'>class="active"</c:if> role="presentation"><a href="orderList1">全部订单</a></li>
 --%>        <li <c:if test='${status==1}'>class="active"</c:if> role="presentation"><a href="orderList?status=1">未付款</a></li>
        <li <c:if test='${status==2}'>class="active"</c:if> role="presentation"><a href="orderList?status=2">已付款</a></li>
        <li <c:if test='${status==3}'>class="active"</c:if> role="presentation"><a href="orderList?status=3">配送中</a></li>
        <li <c:if test='${status==5}'>class="active"</c:if> role="presentation"><a href="orderList?status=5">已收货</a></li>        
        <li <c:if test='${status==4}'>class="active"</c:if> role="presentation"><a href="orderList?status=4">已完成</a></li>
    </ul>
    
    <br>
	
	<table class="table table-striped ">

	<tr>
		<th width="5%">ID</th>
		<th width="5%">总价</th>
		<th width="15%">商品详情</th>
		<th width="20%">收货信息</th>
		<th width="10%">订单状态</th>
		<th width="10%">支付方式</th>
		<th width="10%">下单用户</th>
		<th width="10%">下单时间</th>
		<th width="10%">操作</th>
	</tr>
	
	<c:forEach var="order" items="${orderList}">
         <tr>
         	<td style="vertical-align:middle;"><p>${order.id}</p></td>
         	<td style="vertical-align:middle;"><p>${order.total}</p></td>
         	<td style="vertical-align:middle;">
	         	<c:forEach var ="item" items="${order.itemList}">
		         	<p>${item.good.name}(${item.price}) x ${item.amount}</p>
	         	</c:forEach>
         	</td>
         	<td style="vertical-align:middle;">
         		<p>${order.name}</p>
         		<p>${order.phone}</p>
         		<p>${order.address}</p>
         	</td>
			<td style="vertical-align:middle;">
				<p>
					<c:if test="${order.status==1}">未付款</c:if>
					<c:if test="${order.status==2}"><span style="color:red;">已付款</span></c:if>
					<c:if test="${order.status==3}">配送中</c:if>
					<c:if test="${order.status==5}">已收货</c:if>					
					<c:if test="${order.status==4}">已完成</c:if>
				</p>
			</td>
			<td style="vertical-align:middle;">
				<p>
					<c:if test="${order.paytype==1}">微信</c:if>
					<c:if test="${order.paytype==2}">支付宝</c:if>
					<c:if test="${order.paytype==3}">货到付款</c:if>
				</p>
			</td>
			<td style="vertical-align:middle;"><p>${order.name}</p></td>
			<td style="vertical-align:middle;"><p><fmt:formatDate value="${order.systime}" pattern="yyyy-MM-dd HH:mm:ss" /></p></td>
			<td style="vertical-align:middle;">
				<c:if test="${order.status==2}">
					<a class="btn btn-success" href="orderDispose?id=${order.id}&status=${status}">发货</a>
				</c:if>
				<c:if test="${order.status==5}">
					<a class="btn btn-warning" href="orderFinish?id=${order.id}&status=${status}">完成</a>
				</c:if>
				<a class="btn btn-danger" href="orderDelete?id=${order.id}&status=${status}">删除</a>
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