package pl.mbm.service.contraint;

import pl.mbm.service.contraint.validator.impl.NameNotExistsInDatabaseValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameNotExistsInDatabaseValidatorImpl.class)
@Documented
public @interface NameNotExistsInDatabase {
	String message() default "{constraints.notexistsindatabase}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
