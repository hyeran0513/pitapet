package model.service.exception;

public class PasswordCkMismatchException extends Exception{
	private static final long serialVersionUID = 1L;

	public PasswordCkMismatchException() {
		super();
	}

	public PasswordCkMismatchException(String arg0) {
		super(arg0);
	}
}
