package fr.sadev.app.beans;

import java.util.Set;

public class UpdateUserRequest {
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String confirmPassword;
	private Set<String> role;

	public UpdateUserRequest() {
		super();
	}


	public UpdateUserRequest(String firstname, String lastname, String email, String password, String confirmPassword,
			Set<String> role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = role;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UpdateUserRequest [email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", role=" + role + "]";
	}

}
