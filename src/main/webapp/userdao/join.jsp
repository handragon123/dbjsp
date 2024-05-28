<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user/join.jsp</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<hr>
<h1>JOIN <br>
<form action="<%=request.getContextPath() %>/JoinProc" method="post">
	*id: <input type="text" name="id"><br>
	*pw: <input type="text" name="password"><br>
	*name: <input type="text" name="name"><br>
	*role: 
	<input type="radio" name="role" value="User" checked="checked">User
	<input type="radio" name="role" value="Admin">Admin<br>
	<input type="submit" value="Submit">
</form>
</h1>
</body>
</html>