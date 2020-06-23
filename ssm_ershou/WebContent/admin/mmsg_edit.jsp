<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<title>商品添加</title>
<meta charset="utf-8" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/bootstrap.css">
</head>
<body>
<div class="container-fluid">
	<%@include file="header.jsp"%>
	<br><br>
	<form class="form-horizontal" action="mmsgSave?status=${status}" method="post">
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">留言ID</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="id" readonly="readonly" value="${tixing.id}">
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">商品ID</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="amount" readonly="readonly" value="${tixing.amount}">
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">留言</label>
			<div class="col-sm-6">
				<textarea class="form-control" rows="3" name="name">${tixing.name}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">回复</label>
			<div class="col-sm-6">
				<textarea class="form-control" rows="3" name="address">${tixing.address}</textarea>
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