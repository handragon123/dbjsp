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

@WebServlet("/UpdateProc")
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
		UserDAO dao = new UserDAO();
		UserDTO dto = new UserDTO(id, password, name, role);
		int rs = dao.update(dto);

		session.setAttribute("name",name);
		
		// 4. move
		
		String path = request.getContextPath() +"/userdao/update.jsp";
		response.sendRedirect(path);
	}

}
