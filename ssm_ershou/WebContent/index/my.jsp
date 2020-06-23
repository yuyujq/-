<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/simpleCart.min.js"></script>
<script type="text/javascript">
	function a3() {
		var phone = ${user.phone};
		if (/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\d{8}$/
				.test(phone)) {
			$.ajax({
				url : "${pageContext.request.contextPath}/index/phone",
				data : {
					'phone' : ${user.phone}
				},
				success : function(data) {
					if (data == phone) {
						alert("短信发送成功！请注意查收")
					} else {
						alert("短信发送失败！！！")
					}
				}
			});
			alert(phone);
		} else {
			alert("请输入正确的号码")
		}

	}
</script>
</head>
<body>

	<jsp:include page="header.jsp" />

	<!--account-->
	<div class="account">
		<div class="container">
			<div class="register">
				<c:if test="${msg!=null}">
					<div class="alert alert-danger">${msg}</div>
				</c:if>
				<form action="my?xx=${phonen}" method="post">
					<input type="hidden" name="id" value="${user.id}">
					<div class="register-top-grid">
						<h3>个人中心</h3>

						<h4>收货信息</h4>
						<div class="input">
							<span>收货人<label></label></span> <input type="text" name="name"
								value="${user.name}" placeholder="请输入收货">
						</div>
						<div class="input" hidden>
							<span>现有手机</span> <input type="text" name="phone"
								value="${user.phone}" readonly="readonly">
						</div>
						<div class="input">
							<span>收货地址</span> <input type="text" name="address"
								value="${user.address}" placeholder="请输入收货地址">
						</div>
						<div class="register-but text-center">
							<input type="submit" value="提交">
						</div>
						<hr>
						<h4>安全信息</h4>
						<div class="input">
							<span>原密码</span> <input type="text" name="password"
								placeholder="请输入原密码">
						</div>
						<div class="input">
							<span>新密码</span> <input type="text" name="passwordNew"
								placeholder="请输入新密码">
						</div>
						<div class="clearfix"></div>
						<div class="register-but text-center">
							<input type="submit" value="提交">
						</div>
					</div>
				</form>

				<form action="myphone">
					<hr>
					<h4>更改手机</h4>
					<div class="register-top-grid">
						<input type="hidden" name="id1" value="${user.id}">
						<div class="input">
							<span>现有手机</span> <input type="text" name="phone1"
								value="${user.phone}" readonly="readonly">
							<p style="height: 10px"></p>
							<input style="width: 30%" type="text" id="phonen" name="phonen"
								required="required"> <input
								style="width: 20%; height: 36px; background-color: lightskyblue"
								type="button" value="获取验证码" onclick="a3()">
						</div>
						<div class="input">
							<span>新手机</span> <input type="text" name="phoneNew"
								placeholder="请输入新手机号码">
						</div>
						<div class="clearfix"></div>
						<div class="register-but text-center">
							<input type="submit" value="提交">
						</div>
					</div>
				</form>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--//account-->

	<jsp:include page="footer.jsp" />

</body>
</html>