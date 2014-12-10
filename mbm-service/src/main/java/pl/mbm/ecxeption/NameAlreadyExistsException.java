package pl.mbm.ecxeption;

public class NameAlreadyExistsException extends ValidationException {

	private static final long serialVersionUID = 8002005786837283262L;

	public NameAlreadyExistsException() {
		super(1004, "Name already exists");
	}

}
