package pl.mbm.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mbm.dao.UserDao;
import pl.mbm.model.dto.UserJTable;
import pl.mbm.model.dto.UserRegistrationForm;
import pl.mbm.model.entity.User;
import pl.mbm.service.UserService;
import pl.mbm.validator.Validator;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ConversionService conversionService;
	@Autowired
	private Validator<UserRegistrationForm> registrationValidator;

	@Override
	@Transactional
	public User createUser(User user) {
		return userDao.save(user);
	}

	@Override
	@Transactional
	public UserJTable registerUser(UserRegistrationForm userRegistrationForm) {
		registrationValidator.validate(userRegistrationForm);
		User user = conversionService.convert(userRegistrationForm, User.class);
		User savedUser = createUser(user);
		return conversionService.convert(savedUser, UserJTable.class);
	}

	@Override
	@Transactional
	public List<UserJTable> listUsers() {
		Set<User> users = userDao.findAllWithRoles();
		List<UserJTable> usersJTable = convertUsers(users);
		return usersJTable;
	}

	private List<UserJTable> convertUsers(Set<User> users) {
		return (List<UserJTable>) conversionService.convert(
				userDao.findAllWithRoles(),
				TypeDescriptor.forObject(users),
				TypeDescriptor.collection(List.class,
						TypeDescriptor.valueOf(UserJTable.class)));
	}

}
