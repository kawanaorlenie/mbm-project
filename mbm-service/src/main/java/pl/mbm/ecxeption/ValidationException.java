package pl.mbm.ecxeption;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 4093075539779524800L;
	private int code;
	private String customMessage;

	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public ValidationException(int code, String customMessage) {
		super();
		this.code = code;
		this.customMessage = customMessage;
	}

}
