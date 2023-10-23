package model.service.exception;

public class UnavailableReservationTimeException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnavailableReservationTimeException() {
		super();
	}

	public UnavailableReservationTimeException(String arg0) {
		super(arg0);
	}
}
