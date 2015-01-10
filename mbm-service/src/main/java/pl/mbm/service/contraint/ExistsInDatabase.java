package pl.mbm.service.contraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.mbm.service.contraint.validator.ExistsInDatabaseValidator;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsInDatabaseValidator.class)
@Documented
public @interface ExistsInDatabase {
	String message() default "{constraints.existsindatabase}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}