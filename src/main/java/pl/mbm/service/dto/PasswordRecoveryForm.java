package pl.mbm.service.dto;

import pl.mbm.service.contraint.ExistsInDatabase;

public class PasswordRecoveryForm {

	@ExistsInDatabase
	private String email;

	public PasswordRecoveryForm() {
		super();
	}

	public PasswordRecoveryForm(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
