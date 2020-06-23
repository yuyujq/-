<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>商品列表</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script type="text/javascript" src="js/cart.js"></script>
	
<link rel="stylesheet" href="css/pageSwitch.min.css">
	<style type="text/css">
	*{
		padding: 0;
		margin: 0;
	}
	html,body{
		height: 100%;
	}
	#containersss{
			width: 100%;
			height: 500px;
			overflow: hidden;
	}
	.sections,.section {
		height:100%;
	}
	#containersss,.sections {
		position: relative;
	}
	.section {
		background-color: #000;
		background-size: cover;
		background-position: 50% 50%;
		text-align: center;
		color: white;
	}
	
</style>
	
</head>
<body >
	
	<jsp:include page="header.jsp"/>
	<p style="width:80%;height:2%"></p>

	<div id="containersss" style="width:80%;height:50%;margin-left:10%">
	
	<div class="sections">
	
		<a href="newsop?id=${newsList1.id}"><div class="section" id="section0" style="background-image: url('../${newsList1.phone}')"><h3></h3></div></a>
		<a href="newsop?id=${newsList2.id}"><div class="section" id="section1" style="background-image: url('../${newsList2.phone}')"><h3></h3></div></a>
		<a href="newsop?id=${newsList3.id}"><div class="section" id="section2" style="background-image: url('../${newsList3.phone}')"><h3></h3></div></a>
		<a href="newsop?id=${newsList4.id}"><div class="section" id="section3" style="background-image: url('../${newsList4.phone}')"><h3></h3></div></a>
	</div>
</div>

<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="js/pageSwitch.min.js"></script>
<script>
$(function(){
	
	

	$("#containersss").PageSwitch({
		direction:'horizontal',
		easing:'ease-in',
		duration:1000,
		autoPlay:true,
		loop:'false'
	});})
</script>

	<br>
	<div class="container" style="width:100%;">
	<div class="alert alert-info" style="width:82%;margin-left:9%;text-align:center;"><h3>今日精选</h3></div>
	<c:forEach var="top" items="${top1List}" end="1">
		<div class="banner" style="background-color:white; margin:20px;height:500px">
			<div class="container">
				<h2 class="hdng"><a href="detail?goodid=${top.good.id}">${top.good.name}</a><span></span></h2>
				<p>今日精选推荐</p>
				<a class="banner_a" href="javascript:;" onclick="buy(${top.good.id})">加入购物车</a>
				<div class="banner-text">		
					<a href="detail?goodid=${top.good.id}">
						<img src="../${top.good.cover}" alt="${top.good.name}" width="350" height="350">
					<br/>
					</a>	
				</div>
			</div>
		</div> 
	</c:forEach>	
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
<!-- 	<div class="subscribe2"></div>
 -->	
	<!--gallery-->
	<div class="gallery">
		<div class="container">
			<div class="alert alert-info"style="width:100%;height:50%;text-align:center;"><h3>热销推荐</h3></div>
			<div class="gallery-grids">
				<c:forEach var="top" items="${top2List}" end="2">
					<div class="col-md-4 gallery-grid glry-two" style="width:33%;height:80%">
						<a href="detail?goodid=${top.good.id}" >
							<img src="../${top.good.cover}" class="img-responsive" alt="${top.good.name}" style="width:350px; height:300px"/>
				 
				
						</a>
							上架日期：${top.good.a1}&nbsp;&nbsp;&nbsp;
					销量：${top.good.a3}
					
						<div class="gallery-info galrr-info-two">
							<p>
								<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
								<a href="detail?goodid=${top.good.id}">查看详情</a>
							</p>
							<a class="shop" href="javascript:;" onclick="buy(${top.good.id})">加入购物车</a>
							<div class="clearfix"> </div>
						</div>
						<div class="galy-info">
							<p>${top.good.type.name} > ${top.good.name}</p>
							<div class="galry">
								<div class="prices">
									<h5 class="item_price">¥ ${top.good.price}</h5>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="clearfix"></div>
			<div class="alert alert-info" style="width:100%;height:50%;text-align:center;"><h3>新品推荐</h3></div>
			<div class="gallery-grids">	
				<c:forEach var="top" items="${top3List}" end="7">
					<div class="col-md-3 gallery-grid " style="width:25%;height:60%">
						<a href="detail?goodid=${top.good.id}">
							<img src="../${top.good.cover}" class="img-responsive" alt="${top.good.name}" style="height:220px"/>
						</a>
							上架日期：${top.good.a1}&nbsp;&nbsp;&nbsp;<br/>
					销量：${top.good.a3}
					
						<div class="gallery-info">
							<p>
								<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 
								<a href="detail?goodid=${top.good.id}">查看详情</a>
							</p>
							<a class="shop" href="javascript:;" onclick="buy(${top.good.id})">加入购物车</a>
							<div class="clearfix"> </div>
						</div>
						<div class="galy-info">
							<p>${top.good.type.name} > ${top.good.name}</p>
							<div class="galry">
								<div class="prices">
									<h5 class="item_price">¥ ${top.good.price}</h5>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</c:forEach>
				
			</div>	
		</div>
	</div>
	<!--//gallery-->
	
	<!--subscribe-->
	<div class="subscribe"></div>
	<!--//subscribe-->
	
	<jsp:include page="footer.jsp"/>

</body>
</html>