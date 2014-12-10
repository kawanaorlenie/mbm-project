package pl.mbm.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mbm.dao.UserDao;
import pl.mbm.ecxeption.EmailAlreadyExistsException;
import pl.mbm.ecxeption.EmailIncorrectFormatException;
import pl.mbm.ecxeption.NameAlreadyExistsException;
import pl.mbm.ecxeption.NameIcorrectFormatException;
import pl.mbm.ecxeption.PasswordsMismatchException;
import pl.mbm.model.dto.UserRegistrationForm;

@Service
public class RegistrationValidator implements Validator<UserRegistrationForm> {

	private static final String REGEX_NAME = "^[a-z]{1}[a-z0-9._]{4,18}[a-z0-9]{1}$";
	private static final String REGEX_EMAIL = "^[a-z0-9]*@[a-z]*.[a-z]*";

	@Autowired
	private UserDao userDao;

	@Override
	public boolean validate(UserRegistrationForm userRegistrationForm) {
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
		return false;
	}

	public boolean emailFormatCorrect(String email) {
		if (email.matches(REGEX_EMAIL))
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
