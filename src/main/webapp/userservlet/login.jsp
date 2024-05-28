<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user/login.jsjp</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<hr>
<h1>로그인<br>
<form action="<%=request.getContextPath() %>/loginProc" method="post">
id: <input type="text" name="id" ><br>
pwd : <input type="text" name="password"><br>
<input type="submit" value="login">
</form>
</h1>
</body>
</html>