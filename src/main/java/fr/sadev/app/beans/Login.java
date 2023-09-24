package fr.sadev.app.beans;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Login {

	private String email;
	private String password;

	public Login() {
		super();
	}

	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static String getToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		return userSession.getAccessToken();
	}
	
	public static String getRefreshToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		return userSession.getRefreshToken();
	}

	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}

}
