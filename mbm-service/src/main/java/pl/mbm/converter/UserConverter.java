package pl.mbm.converter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import pl.mbm.builder.UserBuilder;
import pl.mbm.dao.RoleDao;
import pl.mbm.model.dto.UserRegistrationForm;
import pl.mbm.model.entity.Role;
import pl.mbm.model.entity.User;

@Service
public class UserConverter implements Converter<UserRegistrationForm, User> {

	private static final Logger logger = Logger.getLogger(UserConverter.class);

	@Autowired
	private RoleDao roleDao;

	@Override
	public User convert(UserRegistrationForm userRegisterForm) {
		Role role = roleDao.findOneByName("ROLE_USER");
		if (role == null)
			role = new Role("ROLE_USER");
		return new UserBuilder().name(userRegisterForm.getName())
				.email(userRegisterForm.getEmail())
				.password(userRegisterForm.getPassword()).enabled(false)
				.role(role).build();
	}

}
