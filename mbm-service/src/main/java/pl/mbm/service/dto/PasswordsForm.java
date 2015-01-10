package pl.mbm.service.dto;

import pl.mbm.service.contraint.FieldMatch;

@FieldMatch(first = "passwrod", second = "confirmPassword", message = "Passwords must be the same")
public class PasswordsForm {

	private String oldPassword;
	private String password;
	private String confirmPassword;
	private String email;
	private String uuid;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
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

}
