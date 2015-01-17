package pl.mbm.service.contraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.mbm.service.contraint.validator.NameNotExistsInDatabaseValidator;
import pl.mbm.service.contraint.validator.impl.NameNotExistsInDatabaseValidatorImpl;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameNotExistsInDatabaseValidatorImpl.class)
@Documented
public @interface NameNotExistsInDatabase {
	String message() default "{constraints.notexistsindatabase}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
