package pl.mbm.converter;

import org.springframework.core.convert.converter.Converter;

import pl.mbm.builder.UserJTableBuilder;
import pl.mbm.constant.Roles;
import pl.mbm.model.dto.UserJTable;
import pl.mbm.model.entity.Role;
import pl.mbm.model.entity.User;

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
