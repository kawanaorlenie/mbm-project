package pl.mbm.service.contraint.validator;

import javax.validation.ConstraintValidator;

import org.springframework.stereotype.Service;

import pl.mbm.service.contraint.EmailNotExistsInDatabase;
import pl.mbm.service.contraint.ExistsInDatabase;

public interface EmailNotExistsInDatabaseValidator extends ConstraintValidator<EmailNotExistsInDatabase, Object>{

}
