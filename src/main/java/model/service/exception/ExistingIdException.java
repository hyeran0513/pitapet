package model.service.exception;

public class ExistingIdException extends Exception {
	private static final long serialVersionUID = 1L;

	public ExistingIdException() {
		super();
	}

	public ExistingIdException(String arg0) {
		super(arg0);
	}
}
