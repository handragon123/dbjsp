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

@WebServlet("/deleteProc")
public class DeleteProc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteProc() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 값을 받고 찍어 본다. 꼭~~
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("utf-8"); // 한글 처리
		String id = (String) session.getAttribute("id");
		String password = request.getParameter("password");
		boolean isCheck = false;
		
		// 2. DB 처리를 한다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select

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

			// 3-1. 패스워드 확인
			String sql = "select password from users where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (password.equals(rs.getString("password"))) {
					isCheck = true;
				}
			}
			if(isCheck){
				// 3.2 탙퇴 처리
				sql = "delete from users where id =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				// 4. execute
				pstmt.executeUpdate(); // insert, update, delete
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
		if(isCheck){
			session.invalidate();
			jspFile = "main.jsp";// jsp : get
		}else{
			jspFile = "delete.jsp";// jsp : get
		}
		
		// 4. move
		
		String path = request.getContextPath() +"/userservlet/"+jspFile;
	    response.sendRedirect(path);
	}

}
