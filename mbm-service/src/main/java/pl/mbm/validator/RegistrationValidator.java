package pl.mbm.validator;

public interface RegistrationValidator {

	boolean emailFormatCorrect(String email);

	boolean nameFormatCorrect(String name);

	boolean emailExists(String email);

	boolean passwordsMismatch(String password, String confirmPassword);

	boolean nameExists(String name);

	boolean passwordFormatCorrect(String password);

}
