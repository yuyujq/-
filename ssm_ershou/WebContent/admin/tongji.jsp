<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css"/> 


 
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
 
<script type="text/javascript" src="js/echarts.min.js"></script>

<script type="text/javascript">

$(function(){
 
	
	var len=10;//data.total;
		 
		var a=10,b=20;
		var dataone = ${dataones};
    var datatwo = [];
		var html="";
	  
		datatwo.push(${shuliang}); 
		//alert('dataone: '+dataone+'  datatwo: '+datatwo);
	 
		  var dom = document.getElementById("container");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        option = {
            xAxis: {
                type: 'category',
                data: dataone
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: datatwo,
                type: 'bar'
            }]
        };
        ;
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
		 
	$("table tbody").html(html);
	App.init();
	$("#loading").hide();
	 
})


</script>




</head>
<body>
<div class="container-fluid">

	<%@include file="header.jsp" %>
	
	<br>
	
		 <span class="btn-group listsearch horizontal" style="font-size:16px;">统计图：<br/></span> 
	 <div id="container" style="height: 600px"></div>
	</div>
</body>
</html>