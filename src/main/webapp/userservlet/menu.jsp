<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>
<ul>
<li>
<a href="<%=request.getContextPath()%>">Index</a> |
<a href="./main.jsp">Main</a> |
<% if(session.getAttribute("idx") == null) {%>
<a href="./join.jsp">Join</a> | <a href="./login.jsp">Login</a>
<%}else{ %>
<%=session.getAttribute("id") %>(<%=session.getAttribute("name") %>) |
<a href="./update.jsp">Update(내정보수정)</a> | <a href="./delete.jsp">Delete(회원탈퇴)</a>
| <a href="./logout.jsp">LogOut</a>
<%} %>
| <a href="./userList.jsp">User List</a>
</li>
</ul>
</h1>