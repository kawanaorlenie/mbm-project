package pl.mbm.service.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mbm.dao.UserDao;
import pl.mbm.exception.EmailAlreadyExistsException;
import pl.mbm.exception.EmailIncorrectFormatException;
import pl.mbm.exception.NameAlreadyExistsException;
import pl.mbm.exception.NameIcorrectFormatException;
import pl.mbm.exception.PasswordsMismatchException;
import pl.mbm.model.dto.UserRegistrationForm;
import pl.mbm.service.validator.RegistrationValidator;

@Service
public class RegistrationValidatorImpl implements RegistrationValidator {

	private static final String REGEX_NAME = "^[a-z]{1}[a-z0-9._]{4,18}[a-z0-9]{1}$";
	private static final String REGEX_EMAIL = "^[a-z0-9._-]*@[a-z]*.[a-z]*";
	private static final String REGEX_PASSWORD = ".{8,20}";

	@Autowired
	private UserDao userDao;

	@Override
	public UserRegistrationForm validate(
			UserRegistrationForm userRegistrationForm) {
		if (passwordsMismatch(userRegistrationForm.getPassword(),
				userRegistrationForm.getConfirmPassword()))
			throw new PasswordsMismatchException();
		if (!nameFormatCorrect(userRegistrationForm.getName()))
			throw new NameIcorrectFormatException();
		if (!emailFormatCorrect(userRegistrationForm.getEmail()))
			throw new EmailIncorrectFormatException();
		if (nameExists(userRegistrationForm.getName()))
			throw new NameAlreadyExistsException();
		if (emailExists(userRegistrationForm.getEmail()))
			throw new EmailAlreadyExistsException();
		return userRegistrationForm;
	}

	public boolean emailFormatCorrect(String email) {
		if (email != null && email.matches(REGEX_EMAIL))
			return true;
		return false;
	}

	public boolean nameFormatCorrect(String name) {
		if (!name.matches(REGEX_NAME))
			return false;
		return true;
	}

	@Transactional
	public boolean emailExists(String email) {
		if (userDao.findByEmail(email) != null)
			return true;
		return false;
	}

	public boolean passwordFormatCorrect(String password) {
		if (!password.matches(REGEX_PASSWORD))
			return false;
		return true;
	}

	public boolean passwordsMismatch(String password, String confirmPassword) {
		if (password.equals(confirmPassword))
			return false;
		return true;
	}

	@Transactional
	public boolean nameExists(String name) {
		if (userDao.findByName(name) != null)
			return true;
		return false;
	}

}
