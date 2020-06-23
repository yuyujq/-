<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>商品添加</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
<div class="container-fluid">
	<%@include file="header.jsp"%>

	<br><br>
	<form class="form-horizontal" action="newsSave?id=${news.id}" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">新闻名称</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="name"  required="required" value="${news.name }">
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">详细内容</label>
			<div class="col-sm-6">
				<textarea rows="10" class="form-control" id="input_name" value="${news.address}" name="content">${news.address}</textarea>
			</div>
		</div>
	
		
		<div class="form-group">
			<label for="input_file" class="col-sm-1 control-label">封面图片</label> 
			<div class="col-sm-6"><img src="../${news.phone}" width="100" height="100"/>
				<input type="file" name="cover"  id="input_file">推荐尺寸: 500 * 500
			</div>		
		</div>

		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="submit" class="btn btn-success">提交保存</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>