package fr.sadev.app.beans;

import java.util.Set;

public class UserRequest {

	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String confirmPassword;
	private Set<String> roles;
	
	public UserRequest() {
		super();
	}

	public UserRequest(String firstname, String lastname, String email, String password, String confirmPassword,
			Set<String> roles) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.roles = roles;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Set<String> getRole() {
		return this.roles;
	}

	public void setRole(Set<String> role) {
		this.roles = role;
	}
}
