<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user/delete.jsp</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<hr>
<h1>회원 탈퇴<br>
<form action="<%=request.getContextPath() %>/deleteProc" method="post">
pwd : <input type="text" name="password"><br>
<input type="submit" value="submit">
</form>
</h1>
</body>
</html>