package pl.mbm.service.util;

import pl.mbm.dao.util.DaoTestUtils;
import pl.mbm.entity.User;
import pl.mbm.entity.UserBuilder;
import pl.mbm.service.builder.UserJTableBuilder;
import pl.mbm.service.builder.UserRegistrationFormBuilder;
import pl.mbm.service.dto.PasswordRecoveryForm;
import pl.mbm.service.dto.PasswordsForm;
import pl.mbm.service.dto.UserJTable;
import pl.mbm.service.dto.UserRegistrationForm;

public class ServiceTestUtils extends DaoTestUtils {

	public static PasswordRecoveryForm getPasswordRecoveryForm() {
		return new PasswordRecoveryForm(USER_EMAIL);
	}

	public static PasswordRecoveryForm getPasswordRecoveryFormWithEmptyMail() {
		return new PasswordRecoveryForm("");
	}

	public static UserRegistrationForm getUserRegistrationForm() {
		return new UserRegistrationFormBuilder().name(USER_NAME)
				.email(USER_EMAIL).password(USER_PASSWORD)
				.confirmPassword(USER_PASSWORD).build();
	}
	
	public static UserRegistrationForm getUserRegistrationFormWithWrongFields() {
		return new UserRegistrationFormBuilder().name("a")
				.email("a").password("a")
				.confirmPassword("another").build();
	}
	

	public static UserJTable getUserJTable() {
		return new UserJTableBuilder().name(USER_NAME).email(USER_EMAIL)
				.password(USER_PASSWORD).enabled(USER_ENABLED).id(USER_ID)
				.build();
	}

	public static User getUserToSendMail() {
		return new UserBuilder().email("matrom.java@gmail.com")
				.name("matrom_java").build();
	}

	public static PasswordsForm getPasswordsForm() {
		PasswordsForm passwordsForm = new PasswordsForm();
		passwordsForm.setPassword(USER_PASSWORD);
		passwordsForm.setConfirmPassword(USER_PASSWORD);
		passwordsForm.setUuid(USER_ACTIVATION_CODE);
		passwordsForm.setEmail(USER_EMAIL);
		return passwordsForm;
	}
	
	public static PasswordsForm getPasswordsFormWithDifferentPasswords() {
		PasswordsForm passwordsForm = new PasswordsForm();
		passwordsForm.setPassword(USER_PASSWORD);
		passwordsForm.setConfirmPassword("somepassword");
		passwordsForm.setUuid(USER_ACTIVATION_CODE);
		passwordsForm.setEmail(USER_EMAIL);
		return passwordsForm;
	}
}
