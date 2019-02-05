package pl.mbm.converter;

import org.springframework.core.convert.converter.Converter;
import pl.mbm.constant.Roles;
import pl.mbm.entity.Role;
import pl.mbm.entity.User;
import pl.mbm.service.builder.UserJTableBuilder;
import pl.mbm.service.dto.UserJTable;

public class UserJTableConverter implements Converter<User, UserJTable> {

	@Override
	public UserJTable convert(User user) {
		UserJTableBuilder userJTableBuilder = new UserJTableBuilder()
				.id(user.getId()).name(user.getName()).email(user.getEmail())
				.enabled(user.isEnabled()).password(user.getPassword())
				.roleAdmin(false).roleUser(false);
		for (Role role : user.getRoles()) {
			if (Roles.ROLE_ADMIN.equals(role.getName()))
				userJTableBuilder.roleAdmin(true);
			if (Roles.ROLE_USER.equals(role.getName()))
				userJTableBuilder.roleUser(true);
		}
		UserJTable userJTable = userJTableBuilder.build();
		return userJTable;
	}
}
