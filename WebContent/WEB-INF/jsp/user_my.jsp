<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="welcome/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
 <h4>我的信息！</h4>
<p>账户创建时间   ->  ${mylist.createtime}</p>
<p>登录次数    -> ${mylist.loginnum} 次</p>
<p>收藏  -> ${mylist.lovenum}条</p>
<p>厌恶 -> ${mylist.hatenum}条</p>
</div>

</body>
</html>