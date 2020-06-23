<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>商品详情</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/flexslider.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.flexslider.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script type="text/javascript" src="js/cart.js"></script>

</head>
<body>
	 
	<jsp:include page="header.jsp"/>
	<br>
	<div style="width:80%;margin-left:10%;height;300px"><img src="../${news.phone}" width="1200px" height="250"></div>
	<br>
	<div style="width:80%;margin-left:10%">
	<div><h1 style="text-align:center;">${news.name}</h1></div>
	<br>
	<br>
	<div>${news.address}</div>
	
	</div>

	<jsp:include page="footer.jsp"/>

</body>
</html>