package user.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.UserDAO;
import user.dto.UserDTO;

@WebServlet("/JoinProc")
public class JoinProc extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public JoinProc() {
        super();
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("doPost");
      // 1. 값을 받음
      request.setCharacterEncoding("utf-8");
      String id = request.getParameter("id");
      String password = request.getParameter("password");
      String name = request.getParameter("name");
      String role = request.getParameter("role");
      
      // 2. 받은 값 찍어보기
      //System.out.printf("%s,%s,%s,%s",id,password,name,role);
      
      // 3. db 처리
      UserDAO dao = new UserDAO();
      UserDTO dto = new UserDTO(id, password, name, role);
      dao.insertUser(dto);
      
      // 4. move
      String path = request.getContextPath() +"/userdao/login.jsp";
      response.sendRedirect(path);
   }// doPost End

}
