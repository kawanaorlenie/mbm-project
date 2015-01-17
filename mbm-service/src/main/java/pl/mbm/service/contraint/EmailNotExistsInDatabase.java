package pl.mbm.service.contraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.mbm.service.contraint.validator.EmailNotExistsInDatabaseValidator;
import pl.mbm.service.contraint.validator.impl.EmailNotExistsInDatabaseValidatorImpl;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailNotExistsInDatabaseValidatorImpl.class)
@Documented
public @interface EmailNotExistsInDatabase {
	String message() default "{constraints.notexistsindatabase}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
