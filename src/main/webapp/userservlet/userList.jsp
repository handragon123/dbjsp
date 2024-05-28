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
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/kdigital?serverTimezone=UTC";
String user = "root";
String pw = "rpass";

List<UserDTO> userList = new ArrayList<>();

try{
	// 1. 드라이버 로딩
	Class.forName(driver);
	// 2. connection
	conn = DriverManager.getConnection(url, user, pw);
	// 3. sql창
	String sql = "select idx,id,password,name,role,regdate from users";
	pstmt = conn.prepareStatement(sql);
	// 4. execute
	rs = pstmt.executeQuery();
	// 5. rs처리 : id값만 list에 저장
	while(rs.next()) {
		int idx = rs.getInt("idx");
		String id = rs.getString("id");
		String password = rs.getString("password");
		String name = rs.getString("name");
		String role = rs.getString("role");
		String regDate = rs.getString("regdate");
		UserDTO dto = new UserDTO(idx, id, password, name, role, regDate);
		userList.add(dto);
	}
}catch(Exception e) {
	e.printStackTrace();
}finally {
	try {
		rs.close();
		pstmt.close();
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
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