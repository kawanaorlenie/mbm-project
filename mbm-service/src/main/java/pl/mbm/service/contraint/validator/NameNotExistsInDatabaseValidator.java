package pl.mbm.service.contraint.validator;

import javax.validation.ConstraintValidator;

import org.springframework.stereotype.Service;

import pl.mbm.service.contraint.NameNotExistsInDatabase;

public interface NameNotExistsInDatabaseValidator extends ConstraintValidator<NameNotExistsInDatabase, Object>{

}
