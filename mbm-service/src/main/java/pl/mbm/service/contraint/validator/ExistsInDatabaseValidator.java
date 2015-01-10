package pl.mbm.service.contraint.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.mbm.dao.UserDao;
import pl.mbm.service.contraint.ExistsInDatabase;

public class ExistsInDatabaseValidator implements
		ConstraintValidator<ExistsInDatabase, Object> {

	@Autowired
	private UserDao userDao;

	@Override
	public void initialize(ExistsInDatabase constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (userDao.findByEmail((String) value) != null)
			return true;
		return false;
	}

}
