<%@page import="mvc.dao.UserDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="mvc.dto.UserDTO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
UserDAO dao = new UserDAO();
List<UserDTO> userList =  dao.getUsers();
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userList.jsp</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<hr>
<h1>User List
<table border="1">
<tr><th>IDX</th><th>ID</th><th>PW</th><th>Name</th><th>Role</th><th>Regdate</th></tr>
<%
	for(UserDTO dto : userList) {
		int idx = dto.getIdx();
		String id = dto.getId();
		String password = dto.getPassword();
		String name = dto.getName();
		String role = dto.getRole();
		String regdate = dto.getRegDate();
%>
<tr><td><%=idx %><td><%=id %></td><td><%=password %></td><td><%=name %></td><td><%=role %></td><td><%=regdate %></td></tr>
<%} %>
</table>
</h1>
</body>
</html>