package pl.mbm.util;

import pl.mbm.builder.UserBuilder;
import pl.mbm.model.entity.User;

public class TestUtil {

	public static User getUserToSendMail() {
		return new UserBuilder().email("matrom.java@gmail.com")
				.name("matrom_java").build();
	}

}
