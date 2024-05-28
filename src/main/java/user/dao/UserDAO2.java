package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.dto.UserDTO;

public class UserDAO2 {
	
	public List<UserDTO> getUsers(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/kdigital?serverTimezone=UTC";
		String user = "root";
		String pw = "rpass";

		List<UserDTO> userList = new ArrayList<>();

		try{
			// 1. 드라이버 로딩
			Class.forName(driver);
			// 2. connection
			conn = DriverManager.getConnection(url, user, pw);
			// 3. sql창
			String sql = "select idx,id,password,name,role,regdate from users";
			pstmt = conn.prepareStatement(sql);
			// 4. execute
			rs = pstmt.executeQuery();
			// 5. rs처리 : id값만 list에 저장
			while(rs.next()) {
				int idx = rs.getInt("idx");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String role = rs.getString("role");
				String regDate = rs.getString("regdate");
				UserDTO dto = new UserDTO(idx, id, password, name, role, regDate);
				userList.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userList;
	}
	
	public UserDTO getUser(UserDTO dto) {
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
			pstmt.setString(1, dto.getId());
			// 4. execute
			rs = pstmt.executeQuery(); // select

			// 있는지 판단
			dto = null;
			if (rs.next()) { // id 존재
				int idx = rs.getInt("idx");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String role = rs.getString("role");
				String regDate = rs.getString("regdate");
				dto = new UserDTO(idx, id, password, name, role, regDate);
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
		return dto;
	}

	
	public void insertUser(UserDTO dto){
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
	       pstmt.setString(1, dto.getId());
	       pstmt.setString(2, dto.getPassword());
	       pstmt.setString(3, dto.getName());
	       pstmt.setString(4, dto.getRole());
	       
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
	}
	
	
	public int update(UserDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0; 

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
			pstmt.setString(1,dto.getPassword());
			pstmt.setString(2,dto.getName());
			pstmt.setString(3,dto.getRole());
			pstmt.setString(4,dto.getId());
			// 4. execute
			rs = pstmt.executeUpdate();	// insert, update, delete
			
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
		return rs;
	}

	
	
	public int delete(UserDTO dto) {
		int result = 0;
		
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
			// 3. sql 창,  탙퇴 처리
			String	sql = "delete from users where id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			// 4. execute
			pstmt.executeUpdate(); // insert, update, delete


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}

	

}
