package bean;

public class User {

	private String userid;
	private String password;
	private String email;
	private String authority;

	public User() {
		this.userid = null;
		this.password = null;
		this.email = null;
		this.authority = null;
	}

	public User(String userid, String password, String email, String authority) {
		this.userid = userid;
		this.password = password;
		this.email = email;
		this.authority = authority;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
