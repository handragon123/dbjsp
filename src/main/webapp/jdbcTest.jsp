<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jdbc/jdbcTest.jsp</title>
</head>
<body>
<%
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

// use db info : mysql
String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/kdigital?serverTimezone=UTC";
String user = "root";
String password = "rpass";

List<String> userId = new ArrayList<>();

try{
	// 1. driver loading
	Class.forName(driver);
	
	// 2. connection 
	conn = DriverManager.getConnection(url, user, password);
	out.print("conn ok!!");
	
	// 3. sql 창
	String sql = "select idx,id,password,name,role,regdate from users1";
	pstmt = conn.prepareStatement(sql);
	// 4. execute
	rs = pstmt.executeQuery();
	
	// 5. rs 처리 : id값만 List에 저장
	while(rs.next()){
		int idx = rs.getInt("idx");
		String id = rs.getString("id");
		String pw = rs.getString("password");
		String name = rs.getString("name");
		String role = rs.getString("role");
		String regdate = rs.getString("regdate");
		userId.add(id);
	}
	
} catch(Exception e){
	e.printStackTrace();
} finally {
	rs.close();
	pstmt.close();
	conn.close();
}
%>
<ul>
<%for(String id : userId) {%>
	<li><%=id %></li>

<%} %>
</ul>

</body>
</html>