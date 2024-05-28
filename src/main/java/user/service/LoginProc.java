package user.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.dao.UserDAO;
import user.dto.UserDTO;

@WebServlet("/LoginProc")
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
		//LoginDAO dao = new LoginDAO();
		//isLogin = dao.login(id, password);		
		UserDAO dao = new UserDAO();
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto = dao.getUser(dto);
		if(dto != null) {
			if(password.equals(dto.getPassword())) {
				isLogin = true;
			}
		}		
		
		String jspFile = "";
		if (isLogin){ // 꼭 써야하는거 
			
			HttpSession session =  request.getSession();
			session.setAttribute("idx", dto.getIdx());
			session.setAttribute("id", id);
			session.setAttribute("name", dto.getName());
			session.setAttribute("role", dto.getRole());
			jspFile = "main.jsp";
		}else {
			jspFile = "login.jsp";
		}
		
		// 3. move
		String path = request.getContextPath() +"/userdao/" + jspFile;
		response.sendRedirect(path);

	}

}
