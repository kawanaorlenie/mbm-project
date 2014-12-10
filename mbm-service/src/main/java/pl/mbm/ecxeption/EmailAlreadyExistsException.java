package pl.mbm.ecxeption;

public class EmailAlreadyExistsException extends ValidationException {

	private static final String EMAIL_ALREADY_EXISTS_MESSAGE = "Email already exists";
	private static final int EMAIL_ALREADY_EXISTS_CODE = 1003;

	private static final long serialVersionUID = -1004152164639231891L;

	public EmailAlreadyExistsException() {
		super(EMAIL_ALREADY_EXISTS_CODE, EMAIL_ALREADY_EXISTS_MESSAGE);
	}
}
