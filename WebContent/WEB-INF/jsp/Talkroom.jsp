<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="welcome/js/jquery.js"></script>
<script type="text/javascript">
function jtroom1(){
$.ajax({
    type: "POST",
    url: "/login1.php",
    data: "username=" + user + "&userpws=" + pass ,
    dataType: "json",
    beforeSend: function(){
    $("confirm").text("Please wait...");
    },
   success: function(msg){
      alert(msg)
       if(msg=="index"){
         window.location.href="/";
       }
       else if(msg=="gwc"){
         window.location.href="/gwc";
       }
       else{
        $("confirm").text("Incorrect email address or password.");
           }      
    }
     
});

}
</script>
<body>
<button id="room1" onclick="jtroom1()" >room1</button>  
<button id="room2">room2</button>
<span id="room">
</span>


<span id="message">
</span>
</body>
</html>