package pl.mbm.dao.util;

import java.util.UUID;

import pl.mbm.builder.UserBuilder;
import pl.mbm.builder.UserJTableBuilder;
import pl.mbm.builder.UserRegistrationFormBuilder;
import pl.mbm.model.dto.UserJTable;
import pl.mbm.model.dto.UserRegistrationForm;
import pl.mbm.model.entity.ActivationCode;
import pl.mbm.model.entity.Role;
import pl.mbm.model.entity.User;

public class TestUtils {

	public static final String CODE = UUID.randomUUID().toString();

	public static final String USER_EMAIL = "user00@mbm.pl";
	public static final String USER_NAME = "user00";
	public static final boolean USER_ENABLED = false;
	public static final String USER_PASSWORD = "password";
	public static final Role USER_ROLE_TEST = new Role("ROLE_TEST");
	public static final Long USER_ID = 1L;
	// TODO na razie nie uzywam zakowodwanego hasla
	public static final String USER_ENCODED_PASSWORD = null;

	// nie wiem czy zmieniac wszystkie metody, ktore mozna na stale?
	public static final User USER = new UserBuilder().email(USER_EMAIL)
			.name(USER_NAME).enabled(USER_ENABLED).password(USER_PASSWORD)
			.role(USER_ROLE_TEST).build();

	public static ActivationCode getActivationCode() {
		ActivationCode activationCode = new ActivationCode();
		activationCode.setCode(CODE);
		activationCode.setUser(USER);
		return activationCode;
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

	public static User getUserWithId() {
		return new UserBuilder().email(USER_EMAIL).name(USER_NAME)
				.enabled(USER_ENABLED).password(USER_PASSWORD)
				.role(USER_ROLE_TEST).id(USER_ID).build();
	}

	public static ActivationCode getActivationCodeWithId() {
		ActivationCode activationCode = new ActivationCode();
		activationCode.setCode(CODE);
		activationCode.setUser(getUserWithId());
		return activationCode;
	}
}
