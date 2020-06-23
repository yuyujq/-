<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
</head>
<body>

	<nav class="navbar navbar-default" role="navigation" style="background-color:#6495ed;font-size:18px;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="" style="color:black">火禾商城后台</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li <c:if test="${flag==1}">class="active"</c:if>><a href="orderList?status=1" style="color:black">订单管理</a></li>
<%-- 				<li <c:if test="${flag==6}">class="active"</c:if>><a href="orderListmap">统计</a></li>
 --%>				<li <c:if test="${flag==2}">class="active"</c:if>><a href="userList" style="color:black">客户管理</a></li>
					<li <c:if test="${flag==3}">class="active"</c:if>><a href="goodList" style="color:black">商品管理</a></li>
					<li <c:if test="${flag==4}">class="active"</c:if>><a href="typeList" style="color:black" >类目管理</a></li>
					<li <c:if test="${flag==7}">class="active"</c:if>><a href="tixingList" style="color:black">提醒管理</a></li>
					<li <c:if test="${flag==8}">class="active"</c:if>><a href="mmsgList" style="color:black">留言管理</a></li>
					<li <c:if test="${flag==9}">class="active"</c:if>><a href="newsList" style="color:black">新闻管理</a></li>
					<li <c:if test="${flag==5}">class="active"</c:if>><a href="adminRe" style="color:black">修改密码</a></li>
			    
					<li><a href="logout" style="color:green">退出</a></li>
				</ul>	
			</div>
		</div>
	</nav>


    <div style="position:absolute;width:200px;height:200px;right:100px; bottom:200px;border:1px solid #000000;visibility:hidden;">
${msg}

    </div>
    
    
</body>
</html>