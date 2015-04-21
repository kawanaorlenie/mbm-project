package pl.mbm.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mbm.dao.UserDao;
import pl.mbm.model.entity.User;
import pl.mbm.service.MailService;
import pl.mbm.service.UserService;
import pl.mbm.service.dto.UserJTable;
import pl.mbm.service.dto.UserRegistrationForm;
import pl.mbm.service.util.UUIDGenerator;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ConversionService conversionService;
	@Autowired
	private MailService mailService;
	@Autowired
	private UUIDGenerator uuidGenerator;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User createUser(User user) {
		return userDao.save(user);
	}

	@Override
	@Transactional
	public UserJTable registerUser(UserRegistrationForm userRegistrationForm) {
		userRegistrationForm.setPassword(passwordEncoder
				.encode(userRegistrationForm.getPassword()));
		User user = conversionService.convert(userRegistrationForm, User.class);
		String activationCode = uuidGenerator.generate();
		user.setActivationCode(activationCode);
		User savedUser = userDao.save(user);
		mailService.sendActivationMail(user, activationCode);
		return conversionService.convert(savedUser, UserJTable.class);
	}

	@Override
	@Transactional
	public List<UserJTable> listUsers() {
		Set<User> users = userDao.findAll();
		List<UserJTable> usersJTable = convertUsers(users);
		return usersJTable;
	}

	private List<UserJTable> convertUsers(Set<User> users) {
		return (List<UserJTable>) conversionService.convert(
				userDao.findAll(),
				TypeDescriptor.forObject(users),
				TypeDescriptor.collection(List.class,
						TypeDescriptor.valueOf(UserJTable.class)));
	}

}
