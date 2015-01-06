package pl.mbm.service.validator;

import pl.mbm.model.dto.UserRegistrationForm;

public interface RegistrationValidator extends Validator<UserRegistrationForm> {

	boolean emailFormatCorrect(String email);

	boolean nameFormatCorrect(String name);

	boolean emailExists(String email);

	boolean passwordsMismatch(String password, String confirmPassword);

	boolean nameExists(String name);

	boolean passwordFormatCorrect(String password);

}
