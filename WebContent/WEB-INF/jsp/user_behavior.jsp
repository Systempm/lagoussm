<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="assets/js/jquery-1.8.2.min.js" ></script>
    <script src="assets/js/supersized.3.2.7.min.js" ></script>
  <link rel="stylesheet" href="welcome/css/style.css">
<script type="text/javascript">
function onload(){
	
	$.get (
			
	"${pageContext.request.contextPath}/UserBehavior",
	{},
	function (data){
		if(data!=null)
			{
			for (var i=0;i<data.length;i++)
			{
				var j =JSON.parse(data[i]);
				
				$("#uspan").append(j.time  +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"  +    j.username+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+  j.behavior +"<br>");
				
			}
			
			}
	}
	,
	"json"
	
	);
	
}

</script>
<body onload="onload()">
用户行为 (切面缓存 ——>仅显示最近30条记录)   
</br>
<h3><span id="uspan"></span></h3>



 QAQ


</body>
</html>