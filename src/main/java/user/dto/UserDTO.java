package user.dto;

public class UserDTO {
	int idx;
	String id;
	String password;
	String name;
	String role;
	String regDate;
	
	public UserDTO() {
	}
	
	public UserDTO(String id, String password, String name, String role) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.role = role;
	}
	
	public UserDTO(int idx, String id, String password, String name, String role, String regDate) {
		super();
		this.idx = idx;
		this.id = id;
		this.password = password;
		this.name = name;
		this.role = role;
		this.regDate = regDate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "userDTO [idx=" + idx + ", id=" + id + ", password=" + password + ", name=" + name + ", role=" + role
				+ ", regDate=" + regDate + "]";
	}
	
	
}
