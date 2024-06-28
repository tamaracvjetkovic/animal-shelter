package domain.model;

import domain.enums.UserState;

public class UserAccount{
	private Integer id;
	private String username;
	private String password;
	private UserState userState;
	
	public UserAccount(Integer id, String username, String password, UserState userState) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.userState = userState;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserState getUserType() {
		return userState;
	}
	public void setUserType(UserState userType) {
		this.userState = userType;
	}
	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", username=" + username + ", password=" + password + ", userType=" + userType
				+ "]";
	}
	
}
