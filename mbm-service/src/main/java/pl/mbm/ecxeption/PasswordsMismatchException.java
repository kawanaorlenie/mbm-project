package pl.mbm.ecxeption;

public class PasswordsMismatchException extends ValidationException {

	private static final String PASSWORD_MISMATCH_MESSAGE = "Passwords are not the same";
	private static final int PASSWORD_MISMATCH_CODE = 1001;

	private static final long serialVersionUID = 4291212914541705037L;

	public PasswordsMismatchException() {
		super(PASSWORD_MISMATCH_CODE, PASSWORD_MISMATCH_MESSAGE);
	}

}
