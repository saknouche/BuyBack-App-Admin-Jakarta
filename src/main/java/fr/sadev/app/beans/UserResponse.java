package fr.sadev.app.beans;

import java.util.HashSet;
import java.util.Set;

public class UserResponse {

	    private Long id;

	    private String email;

	    private String password;
	    private String firstname;

	    private String lastname;

	    private Set<Role> roles = new HashSet<>();



	    public UserResponse() {
	    }

	    public UserResponse(String firstname, String lastname, String email) {
	        this.firstname = firstname;
	        this.lastname = lastname;
	        this.email = email;
	    }

	    public UserResponse(String firstname, String lastname, String email, String password) {
	        this.firstname = firstname;
	        this.lastname = lastname;
	        this.email = email;
	        this.password = password;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
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

	    public Set<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }

	    @Override
	    public String toString() {
	        return "User{" +
	            "id=" + id +
	            ", email='" + email + '\'' +
	            ", password='" + password + '\'' +
	            ", firstname='" + firstname + '\'' +
	            ", lastname='" + lastname + '\'' +
	            ", roles=" + roles +
	            '}';
	    }
	

}
