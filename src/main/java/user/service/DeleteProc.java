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

@WebServlet("/DeleteProc")
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
		// 비번 체크
		UserDAO dao = new UserDAO();
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto = dao.getUser(dto);
		if(dto != null) {
			if(password.equals(dto.getPassword())) {
				isCheck = true;
				dao.delete(dto);
			}
		}			
		
		// 4. move
		String jspFile = "";		
		if(isCheck){
			session.invalidate();
			jspFile = "main.jsp";// jsp : get
		}else{
			jspFile = "delete.jsp";// jsp : get
		}
		String path = request.getContextPath() +"/userdao/"+jspFile;
	    response.sendRedirect(path);
	}

}
