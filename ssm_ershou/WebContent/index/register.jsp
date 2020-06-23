<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户注册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/simpleCart.min.js"></script>
	
	<script type="text/javascript">
	function a3() {
		var phone = $("#phone").val();
		if(/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\d{8}$/.test(phone)){
			$.ajax({
				url:"${pageContext.request.contextPath}/index/phone",
				data:{'phone':$("#phone").val()},
				success:function(data){
					if(data==phone){
						alert("短信发送成功！请注意查收")	
					}
					else{
						alert("短信发送失败！！！")
					}
				}
			}); 
			alert(phone);
		}else{
			alert("请输入正确的号码")
		}
			
	}
	</script>
</head>
<body>

	<jsp:include page="header.jsp"/>
	
	<!--account-->
	<div class="account">
		<div class="container">
			<div class="register">
				<c:if test="${msg!=null}"><div class="alert alert-danger">${msg}</div></c:if>
				<form action="register?phonen=${phonen}" method="post"> 
					<div class="register-top-grid">
						<h3>注册新用户</h3>
						<div class="input">
							<span>用户名 <label style="color:red;">*</label></span>
							<input type="text" name="username" placeholder="请输入用户名" required="required" > 
						</div>
						<div class="input">
							<span>密码 <label style="color:red;">*</label></span>
							<input type="password" name="password" placeholder=" 请输入密码" required="required" style="width:96%;height:37px"> 
						</div>
						<div class="input">
							<span>收货人<label></label></span>
							<input type="text" name="name" placeholder="请输入收货"> 
						</div>
						<div class="input">
							<span>收货电话<label style="color:red;">*</label></span>
							<input type="text" name="phone" placeholder="请输入收货电话" id="phone" required="required"> 
							<p style="height:10px"></p>
							<input style="width:30%" type="text" name="phonen" placeholder="请输入验证码" required="required"> 
							<input style="width:20%;height:36px;background-color:lightskyblue" type="button" value="获取验证码"  onclick="a3()">
						</div>
						<div class="input">
							<span>收货地址<label></label></span>
							<input type="text" name="address" placeholder="请输入收货地址"> 
						</div>
						<div class="clearfix"> </div>
					</div>
					<div class="register-but text-center">
					   <input type="submit" value="提交">
					   <div class="clearfix"> </div>
					</div>
				</form>
				<div class="clearfix"> </div>
			</div>
	    </div>
	</div>
	<!--//account-->

	<jsp:include page="footer.jsp"/>
	
</body>
</html>