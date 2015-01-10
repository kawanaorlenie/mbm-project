package pl.mbm.service.dto;

import pl.mbm.service.contraint.ExistsInDatabase;

public class PasswordRecoveryForm {

	@ExistsInDatabase
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
