package pl.mbm.dao.util;

import java.util.UUID;

import pl.mbm.builder.UserBuilder;
import pl.mbm.model.entity.ResetPassword;
import pl.mbm.model.entity.Role;
import pl.mbm.model.entity.User;

public class DaoTestUtils {

	public static final String USER_ACTIVATION_CODE = UUID.randomUUID()
			.toString();

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
			.role(USER_ROLE_TEST).activationCode(USER_ACTIVATION_CODE).build();

	public static User getUserWithId() {
		return new UserBuilder().email(USER_EMAIL).name(USER_NAME)
				.enabled(USER_ENABLED).password(USER_PASSWORD)
				.role(USER_ROLE_TEST).activationCode(USER_ACTIVATION_CODE)
				.id(USER_ID).build();
	}

	public static User getActivatedUserWithId() {
		return new UserBuilder().email(USER_EMAIL).name(USER_NAME)
				.enabled(true).password(USER_PASSWORD).role(USER_ROLE_TEST)
				.activationCode(USER_ACTIVATION_CODE).id(USER_ID).build();
	}

	public static ResetPassword getResetPassword() {
		return new ResetPassword(USER_EMAIL, USER_ACTIVATION_CODE);
	}
}
