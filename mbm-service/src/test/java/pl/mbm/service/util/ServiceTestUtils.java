package pl.mbm.service.util;

import pl.mbm.builder.UserBuilder;
import pl.mbm.dao.util.DaoTestUtils;
import pl.mbm.model.entity.User;
import pl.mbm.service.builder.UserJTableBuilder;
import pl.mbm.service.builder.UserRegistrationFormBuilder;
import pl.mbm.service.dto.PasswordRecoveryForm;
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

	public static UserJTable getUserJTable() {
		return new UserJTableBuilder().name(USER_NAME).email(USER_EMAIL)
				.password(USER_PASSWORD).enabled(USER_ENABLED).id(USER_ID)
				.build();
	}

	public static User getUserToSendMail() {
		return new UserBuilder().email("matrom.java@gmail.com")
				.name("matrom_java").build();
	}
}
