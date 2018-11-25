package pl.mbm.service.contraint;

import pl.mbm.service.contraint.validator.ExistsInDatabaseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsInDatabaseValidator.class)
@Documented
public @interface ExistsInDatabase {
	String message() default "{constraints.existsindatabase}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}