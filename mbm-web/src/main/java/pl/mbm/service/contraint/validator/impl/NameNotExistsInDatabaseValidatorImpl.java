package pl.mbm.service.contraint.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mbm.dao.UserDao;
import pl.mbm.service.contraint.NameNotExistsInDatabase;
import pl.mbm.service.contraint.validator.NameNotExistsInDatabaseValidator;

import javax.validation.ConstraintValidatorContext;

@Service
public class NameNotExistsInDatabaseValidatorImpl implements NameNotExistsInDatabaseValidator {

	@Autowired
	private UserDao userDao;

	@Override
	public void initialize(NameNotExistsInDatabase constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (userDao.findByName((String) value) != null)
			return false;
		return true;
	}
}