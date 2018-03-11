<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="welcome/js/jquery.js"></script>
  <script type="text/javascript">
            $(function () {
            
                (function longPolling() {
               var id = 1;
                    $.ajax({
                        url: "${ pageContext.request.contextPath }/userss/"+id,
                        data: {"timed": new Date().getTime()},
                        dataType: "text",
                        timeout: 20000,
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            $("#state").append("[state: " + textStatus + ", error: " + errorThrown + " ]<br/>");
                            if (textStatus == "timeout") { // 请求超时
                            	setInterval(longPolling(),3000); 
                                
                                // 其他错误，如网络错误等
                                } else { 
                                	setInterval(longPolling(),3000); 
                                    
                                }
                            },
                        success: function (data, textStatus) {
                            $("#state").append("[state: " + textStatus + ", data: { " + data + "} ]<br/>");
                            
                            if (textStatus == "success") { // 请求成功
                            	setInterval(longPolling(),3000); 
                            }
                        }
                    });
                })();
                
            });
        </script>
<body onload="a()">
1111
<span id = "state"></span>
</body>
</html>