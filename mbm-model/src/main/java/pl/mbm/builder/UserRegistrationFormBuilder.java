package pl.mbm.builder;

import pl.mbm.model.dto.UserRegistrationForm;

public class UserRegistrationFormBuilder {

	private UserRegistrationForm userRegistrationForm = new UserRegistrationForm();

	public UserRegistrationForm build() {
		return userRegistrationForm;
	}

	public UserRegistrationFormBuilder name(String name) {
		userRegistrationForm.setName(name);
		return this;
	}

	public UserRegistrationFormBuilder email(String email) {
		userRegistrationForm.setEmail(email);
		return this;
	}

	public UserRegistrationFormBuilder password(String password) {
		userRegistrationForm.setPassword(password);
		return this;
	}

	public UserRegistrationFormBuilder confirmPassword(String confirmPassword) {
		userRegistrationForm.setConfirmPassword(confirmPassword);
		return this;
	}
}
