<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>客户列表</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/> 
<script type="text/javascript" src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container-fluid">

	<%@include file="header.jsp"%>
	
			
	<br>
	
	<table class="table table-striped">

	<tr>
		<th width="5%">ID</th>
	 
		<th width="10%">提醒内容</th>
		<th width="10%">提醒时间</th>
		<th width="10%">操作</th>
	</tr>
	
	<c:forEach var="user" items="${tixingList}">
         <tr>
         	<td style="vertical-align:middle;"><p>${user.id}</p></td>
         	<td style="vertical-align:middle;"><p>${user.address}</p></td>
         	<td style="vertical-align:middle;"><p><fmt:formatDate value="${user.systime}" pattern="yyyy-MM-dd HH:mm:ss" /></p></td>
			<td style="vertical-align:middle;">

				<a class="btn btn-danger" href="tixingDelete?id=${user.id}">删除</a>
			</td>
       	</tr>
     </c:forEach>
     
</table>
<div style="text-align:center">
     <c:if test="${flag==0}"><h3  style="width:100%;height:100px;">暂无相关留言</h3></c:if>
     </div>


<br>${pageTool}<br>
</div>
</body>
</html>