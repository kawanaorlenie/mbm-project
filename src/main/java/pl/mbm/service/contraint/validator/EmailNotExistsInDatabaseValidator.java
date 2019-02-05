package pl.mbm.service.contraint.validator;

import pl.mbm.service.contraint.EmailNotExistsInDatabase;

import javax.validation.ConstraintValidator;

public interface EmailNotExistsInDatabaseValidator extends ConstraintValidator<EmailNotExistsInDatabase, Object>{

}
