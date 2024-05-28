<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 1. 값을 받고 찍어 본다. 꼭~~
request.setCharacterEncoding("utf-8"); // 한글 처리
String id = request.getParameter("id");
String password = request.getParameter("password");

int idx = 0;
String name = "";
String role = "";
boolean isLogin = false;
%>
<%
// 값을 꼭 찍어 보시오.
//out.print(id+"<br>");
//out.print(password+"<br>");
%>
<%
// 2. DB 처리를 한다.
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null; // select

String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/kdigital?serverTimezone=UTC";
String user = "root";
String pw = "rpass";

try{
	// 1. driver loading
	Class.forName(driver);	
	// 2. connection
	conn = DriverManager.getConnection(url, user, pw);
	out.print("conn ok!!");
	// 3. sql 창
	String sql = "select idx,id,password,name,role,regdate from users where id=?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1,id);
	// 4. execute
	rs = pstmt.executeQuery();		
	
	if(rs.next()){ // id 존재
		String dbPw = rs.getString("password");
		if(dbPw.equals(password)){ // user ok~
			idx = rs.getInt("idx");
			name = rs.getString("name");
			role = rs.getString("role");
			isLogin = true;		
		}
	}
	
}catch(Exception e){
	e.printStackTrace();
}finally{
	rs.close();
	pstmt.close();
	conn.close();
}
%>
<%
if(isLogin){ // 꼭 써야하는 거
	session.setAttribute("idx", idx);
	session.setAttribute("id", id);
	session.setAttribute("name", name);
	session.setAttribute("role", role);	
	response.sendRedirect("main.jsp");// jsp : get
}else{	
	response.sendRedirect("login.jsp");// jsp : get
}
%>






