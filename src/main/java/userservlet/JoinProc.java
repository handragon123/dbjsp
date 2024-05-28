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

@WebServlet("/joinProc")
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
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      String driver = "com.mysql.cj.jdbc.Driver";
      String url = "jdbc:mysql://localhost:3306/kdigital?serverTimezone=UTC";
      String user = "root";
      String pw = "rpass";
      
      try {
         // 1. driver 
         Class.forName(driver);
         
         // 2. conn
         conn = DriverManager.getConnection(url,user, pw);
         
         // 3. sql + 쿼리창
         String sql = "insert into users(id,password,name,role) values(?,?,?,?)";
         pstmt = conn.prepareStatement(sql);
         
         // 4. ? 세팅
         pstmt.setString(1, id);
         pstmt.setString(2, password);
         pstmt.setString(3, name);
         pstmt.setString(4, role);
         
         // 5. execute 실행
         pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         try {
            pstmt.close();
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      String path = request.getContextPath() +"/userservlet/login.jsp";
      response.sendRedirect(path);
   }// doPost End

}
