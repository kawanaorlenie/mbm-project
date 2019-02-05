package pl.mbm.service.contraint;

import pl.mbm.service.contraint.validator.impl.EmailNotExistsInDatabaseValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailNotExistsInDatabaseValidatorImpl.class)
@Documented
public @interface EmailNotExistsInDatabase {
	String message() default "{constraints.notexistsindatabase}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
