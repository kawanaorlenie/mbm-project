package pl.mbm.exception;

public class IncorrectCodeException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7243216096682379981L;

	public IncorrectCodeException() {
		super(1000, "Account has not been activated");
		// TODO Auto-generated constructor stub
	}

}
