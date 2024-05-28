package userservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/updateProc")
public class UpdateProc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateProc() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		// 1. 값고 찍어본다 받는다. 꼭 !! 

		HttpSession session = request.getSession();

		request.setCharacterEncoding("utf-8"); // 한글 처리
		String id = (String)session.getAttribute("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String role = request.getParameter("role");

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
			System.out.print("conn ok!!");
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
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		session.setAttribute("name",name);
		
		// 4. move
		
		String path = request.getContextPath() +"/userservlet/update.jsp";
		response.sendRedirect(path);
	}

}
