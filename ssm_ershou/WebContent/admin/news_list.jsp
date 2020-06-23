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
	
	
	

	
	<br>

	<table class="table table-striped" style="width:80%;">

	<tr>
		<th width="20%">新闻栏</th>
		<th width="30%">新闻名</th>
		<th width="5%">操作</th>
	</tr>
	
	<c:forEach var="news" items="${newslist}">
         <tr>
         	<td style="vertical-align:middle;"><p>${news.paytype}</p></td>
         	<td style="vertical-align:middle;"><p>${news.name}</p></td>
			<td style="vertical-align:middle;">
				<a class="btn btn-success" href="newsEdit?id=${news.id}">修改</a>
			</td>
       	</tr>
     </c:forEach>
     
</table>

</div>
</body>
</html>