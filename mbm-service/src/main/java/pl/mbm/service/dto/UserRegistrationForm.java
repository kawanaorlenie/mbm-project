package pl.mbm.service.dto;

import javax.validation.constraints.Pattern;

import pl.mbm.service.contraint.EmailNotExistsInDatabase;
import pl.mbm.service.contraint.FieldMatch;
import pl.mbm.service.contraint.NameNotExistsInDatabase;

@FieldMatch(first = "password", second = "confirmPassword", message = "Passwords must be the same")
public class UserRegistrationForm {
	
	private static final String REGEX_NAME = "^[a-z]{1}[a-z0-9._]{4,18}[a-z0-9]{1}$";
	private static final String REGEX_EMAIL = "^[a-z0-9._-]*@[a-z]*.[a-z]*";
	private static final String REGEX_PASSWORD = ".{8,20}";

	@Pattern(regexp=REGEX_NAME,message="Wrong name format")
	@NameNotExistsInDatabase
	private String name;
	@Pattern(regexp=REGEX_EMAIL,message="Wrong format of email")
	@EmailNotExistsInDatabase
	private String email;
	@Pattern(regexp=REGEX_PASSWORD,message="Wrong password format")
	private String password;
	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRegistrationForm other = (UserRegistrationForm) obj;
		if (confirmPassword == null) {
			if (other.confirmPassword != null)
				return false;
		} else if (!confirmPassword.equals(other.confirmPassword))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	

}
