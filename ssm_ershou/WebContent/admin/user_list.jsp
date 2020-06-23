<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>客户列表</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/> 
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">

	<%@include file="header.jsp"%>
	
	<div>
	<form class="form-inline" style="float:left;margin-right:5px" method="post" action="userSel">
  <div class="form-group">
    <input type="text" class="form-control" name="name" placeholder="请输入用户名" style="width:180px" required="required">
  </div>
  <button type="submit" class="btn btn-primary" style="width:82px">搜索用户</button>
  </form>
	<a class="btn btn-warning" href="userAdd">添加客户</a></div>
		
	<br>
	
	<table class="table table-striped">

	<tr>
		<th width="5%">ID</th>
		<th width="10%">用户名</th>
		<th width="10%">电话</th>
		<th width="10%">地址</th>
		<th width="10%">操作</th>
	</tr>
	
	<c:forEach var="user" items="${userList}">
         <tr>
         	<td style="vertical-align:middle;"><p>${user.id}</p></td>
         	<td style="vertical-align:middle;"><p>${user.username}</p></td>
         	<td style="vertical-align:middle;"><p>${user.phone}</p></td>
         	<td style="vertical-align:middle;"><p>${user.address}</p></td>
			<td style="vertical-align:middle;">
				<a class="btn btn-info" href="userRe?id=${user.id}">重置密码</a>
				<a class="btn btn-success" href="userEdit?id=${user.id}">修改</a>
				<a class="btn btn-danger" href="userDelete?id=${user.id}">删除</a>
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