package userservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginProc")
public class LoginProc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginProc() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 값을 받고 찍어 본다. 꼭~~
		request.setCharacterEncoding("utf-8"); // 한글 처리
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		int idx = 0;
		String name="";
		String role="";
		boolean isLogin = false;

		// 2. DB 처리를 한다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select , 회원가입은 insert할 것이므로 주석 !

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/kdigital?serverTimezone=UTC";
		String user = "root";
		String pw = "rpass";

		try {
			// 1. driver loading
			Class.forName(driver);
			// 2. connection
			conn = DriverManager.getConnection(url, user, pw);
			System.out.print("conn ok!!");
			// 3. sql 창
			String sql = "select idx,id,password,name,role,regdate from users where id=?";
			pstmt = conn.prepareStatement(sql);
			// 문자니까 setString, 날짜면 setDate 등등 ...
			pstmt.setString(1, id);
			// 4. execute
			rs = pstmt.executeQuery(); // select

			// 있는지 판단
			if (rs.next()) { // id 존재
				String dbPw = rs.getString("password");
				if (dbPw.equals(password)) { // user ok~
					// session
					idx = rs.getInt("idx");
					name = rs.getString("name");
					role = rs.getString("role");
					isLogin = true;
				} 
			} 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		String jspFile = "";
		if (isLogin){ // 꼭 써야하는거 
			HttpSession session =  request.getSession();
			session.setAttribute("idx", idx);
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("role", role);
			jspFile = "main.jsp";
		}else {
			jspFile = "login.jsp";
		}
		
		// 3. move
		String path = request.getContextPath() +"/userservlet/" + jspFile;
		response.sendRedirect(path);

	}

}
