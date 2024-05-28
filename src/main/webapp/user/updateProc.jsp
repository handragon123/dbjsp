<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/sessionCheck.jsp" %>    
<%
// 1. 값을 받고 찍어 본다. 꼭~~
request.setCharacterEncoding("utf-8"); // 한글 처리
String id = (String)session.getAttribute("id");
String password = request.getParameter("password");
String name = request.getParameter("name");
String role = request.getParameter("role");
%>
<%
// 값을 꼭 찍어 보시오.
%>
<%
// 2. DB 처리를 한다.
Connection conn = null;
PreparedStatement pstmt = null;
//ResultSet rs = null; // select

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
	String sql = "update users set password=?, name=?, role=? where id =?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1,password);
	pstmt.setString(2,name);
	pstmt.setString(3,role);
	pstmt.setString(4,id);
	// 4. execute
	pstmt.executeUpdate();	// insert, update, delete
	
}catch(Exception e){
	e.printStackTrace();
}finally{
	//rs.close();
	pstmt.close();
	conn.close();
}
%>
<%
session.setAttribute("name", name);
response.sendRedirect("update.jsp");// jsp : get
%>
