<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/simpleCart.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script type="text/javascript" src="js/cart.js"></script>
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	<!--products-->
	<div class="products">	 
		<div class="container">
			<div style="width:50%;display:inline">
			<c:if test="${type!=null}">
			<h2>${type.name}</h2>
			<form class="form-inline" style="float: right; margin-right: 80px"
				method="post" action="priceSel?id=${type.id}&temp=1">
				价格：
				<div class="form-group">
					<input type="number" class="form-control" name="lprice"
				placeholder="最低价格" style="width: 90px;-moz-appearance: textfield;" required="required">
				</div>
				-
				<div class="form-group">
					<input type="number" class="form-control" name="hprice"
				placeholder="最高价格" style="width: 90px;-moz-appearance: textfield;" required="required">
				</div>
				<button type="submit" class="btn btn-primary" style="width: 82px;">搜索商品</button>
			</form>
			</c:if>	
			<c:if test="${type==null}">
				<c:if test="${flag==7}"><h2>热销推荐</h2></c:if>		
				<c:if test="${flag==8}"><h2>新品推荐</h2></c:if>
				<form class="form-inline" style="float: right; margin-right: 80px"
				method="post" action="priceSel?id=${flag}&temp=2">
				价格：
				<div class="form-group">
					<input type="number" class="form-control" name="lprice"
				placeholder="最低价格" style="width: 90px;-moz-appearance: textfield;" required="required">
				</div>
				-
				<div class="form-group">
					<input type="number" class="form-control" name="hprice"
				placeholder="最高价格" style="width: 90px;-moz-appearance: textfield;" required="required">
				</div>
				<button type="submit" class="btn btn-primary" style="width: 82px;">搜索商品</button>
			</form>
			</c:if>	
			<c:if test="${sel==0}"><h2>"${selname}" 搜索结果如下</h2></c:if>

			
			</div>

			<div class="col-md-12 product-model-sec">
				<c:forEach var="good" items="${goodList}">
					<div class="product-grid" style="width:240px;height:420px">
						<a href="detail?goodid=${good.id}">				
							<div class="more-product"><span> </span></div>						
							<div class="product-img b-link-stripe b-animate-go  thickbox">
								<img src="../${good.cover}" class="img-responsive" alt="${good.name}" style="width:240px;height:220px">
							
								上架日期：${good.a1}&nbsp;&nbsp;&nbsp;
								<br/>
					销量：${good.a3}
					
								<div class="b-wrapper">
									<h4 class="b-animate b-from-left  b-delay03">							
										<button>查看详情</button>
									</h4>
								</div>
							</div>
						</a>				
						<div class="product-info simpleCart_shelfItem">
							<div class="product-info-cust prt_name">
								<h4>${good.name}</h4>								
								<span class="item_price">¥ ${good.price}</span>
								<input type="button" class="item_add items" value="加入购物车" onclick="buy(${good.id})">
								<div class="clearfix"> </div>
							</div>												
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"> </div>
			</div>
			<div>${pageTool}</div>
			<div style="text-align:center">
     <c:if test="${Npage==1}"><br><br><h3  style="width:100%;height:200px;vertical-align:middle;">暂无相关搜索结果</h3></c:if>
     </div>
		</div>
	</div>
	<!--//products-->	
	
	<jsp:include page="footer.jsp"/>

</body>
</html>