<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>相关留言</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/index/css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/index/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/index/css/flexslider.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/index/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/index/js/jquery.flexslider.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/index/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/index/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/index/js/cart.js"></script>
	<script type="text/javascript">
		$(function() {
		  $('.flexslider').flexslider({
			animation: "slide",
			controlNav: "thumbnails"
		  });
		});
		
	</script>
</head>
<body>
	 

	<jsp:include page="header.jsp"/>
	<!--//single-page-->
	<table class="table table-striped">

	</p>
	</tr>
	<tr>
		<th width="50%">留言</th>
		<th width="50%">回复</th>
	</tr>
	<c:forEach var="user" items="${tixingList}">
         <tr>
			
         	<td style="vertical-align:middle;"><p style="height:50px">${user.name}</p></td>
         	<td style="vertical-align:middle;"><p style="height:50px">${user.address}</p></td>

			
       	</tr>
     </c:forEach>
     
</table>
     <div style="text-align:center">
     <c:if test="${flag==0}"><h3  style="width:100%;height:100px;">暂无相关留言</h3></c:if>
     </div>

	<div>${pageTool}</div>
	<jsp:include page="footer.jsp"/>

</body>
</html>