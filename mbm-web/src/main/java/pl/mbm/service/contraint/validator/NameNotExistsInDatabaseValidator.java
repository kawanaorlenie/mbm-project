package pl.mbm.service.contraint.validator;

import pl.mbm.service.contraint.NameNotExistsInDatabase;

import javax.validation.ConstraintValidator;

public interface NameNotExistsInDatabaseValidator extends ConstraintValidator<NameNotExistsInDatabase, Object>{

}
