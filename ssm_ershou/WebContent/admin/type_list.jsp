<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>类目列表</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/> 
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">

	<%@include file="header.jsp"%>
	
	
	
	<div>
		<form class="form-inline" method="post" action="typeSave">
			<input type="text" class="form-control" id="input_name" name="name" placeholder="输入类目名称" required="required" style="width: 180px">
			<input type="submit" class="btn btn-primary" value="添加类目"/>
		</form>
	</div>
	
	<br>

	<table class="table table-striped" style="width:80%">

	<tr>
		<th width="15%">ID</th>
		<th width="20%">名称</th>
		<th width="5%">操作</th>
	</tr>
	
	<c:forEach var="type" items="${typeList}">
         <tr>
         	<td style="vertical-align:middle;"><p>${type.id}</p></td>
         	<td style="vertical-align:middle;"><p>${type.name}</p></td>
			<td style="vertical-align:middle;">
				<a class="btn btn-success" href="typeEdit?id=${type.id}">修改</a>
				<a class="btn btn-danger" href="typeDelete?id=${type.id}">删除</a>
			</td>
       	</tr>
     </c:forEach>
     
</table>

</div>
</body>
</html>