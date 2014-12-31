package pl.mbm.exception;

public class EmailIncorrectFormatException extends ValidationException {

	private static final long serialVersionUID = 364781434681051481L;

	public EmailIncorrectFormatException() {
		super(1005, "Incorrec email format");
	}
}
