<%@page import="java.sql.DriverManager"%>
<%@page import="mvc.dto.UserDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/sessionCheck.jsp" %>
<%
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/kdigital?serverTimezone=UTC";
String user = "root";
String pw = "rpass";

UserDTO dto = null; // 개인 정보

try{
	// 1. 드라이버 로딩
	Class.forName(driver);
	// 2. connection
	conn = DriverManager.getConnection(url, user, pw);
	// 3. sql창
	String sql = "select idx,id,password,name,role,regdate from users where id=?";
	pstmt = conn.prepareStatement(sql);
	String id = (String)session.getAttribute("id");
	pstmt.setString(1, id);
	// 4. execute
	rs = pstmt.executeQuery();
	// 5. rs처리 : id값만 list에 저장
	if(rs.next()) {
		int idx = rs.getInt("idx");
		String password = rs.getString("password");
		String name = rs.getString("name");
		String role = rs.getString("role");
		String regDate = rs.getString("regdate");
		dto = new UserDTO(idx, id, password, name, role, regDate);
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
<title>user/join.jsp</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<hr>
<h1>UPDATE <br>
<form action="updateProc.jsp" method="post">
	*id: <%=session.getAttribute("id") %><br>
	*pw: <input type="text" name="password" value="<%=dto.getPassword()%>"><br>
	*name: <input type="text" name="name" value="<%=dto.getName()%>"><br>
	*role: 
	<input type="radio" name="role" value="User" 
	<%if(dto.getRole().equals("User")) {%> checked="checked" <%} %>>User
	<input type="radio" name="role" value="Admin"
	<%if(dto.getRole().equals("Admin")) {%> checked="checked" <%} %>>Admin<br>
	<input type="submit" value="Submit">
</form>
</h1>
</body>
</html>