package pl.mbm.exception;

public class NameIcorrectFormatException extends ValidationException {

	private static final String NAME_INCORRECT_MESSAGE = "Incorrect name format";
	private static final int NAME_INCORRECT_CODE = 1002;

	private static final long serialVersionUID = 4291212914541705037L;

	public NameIcorrectFormatException() {
		super(NAME_INCORRECT_CODE, NAME_INCORRECT_MESSAGE);
	}
}
