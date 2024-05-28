<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
<h1>
<ul>
<li><a href="./servlet/login.jsp">Hello - Servlet POST</a></li>
<li><a href="<%=request.getContextPath() %>/hello?id=hong">Hello - Servlet</a></li>
<li><a href="./jdbc/jdbcTest.jsp">JDBC TEST</a></li>
<li><hr></li>
<li><a href="./user/main.jsp">user jsp</a></li>
<li><a href="./userservlet/main.jsp">user servelt</a></li>
<li><a href="./userdao/main.jsp">user dao</a></li>
</ul>
</h1>
</body>
</html>