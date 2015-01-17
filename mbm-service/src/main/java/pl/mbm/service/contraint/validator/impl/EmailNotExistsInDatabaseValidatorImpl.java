package pl.mbm.service.contraint.validator.impl;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mbm.dao.UserDao;
import pl.mbm.service.contraint.EmailNotExistsInDatabase;
import pl.mbm.service.contraint.validator.EmailNotExistsInDatabaseValidator;

@Service
public class EmailNotExistsInDatabaseValidatorImpl implements EmailNotExistsInDatabaseValidator{

	@Autowired
	private UserDao userDao;

	@Override
	public void initialize(EmailNotExistsInDatabase constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (userDao.findByEmail((String) value) != null)
			return false;
		return true;
	}
}