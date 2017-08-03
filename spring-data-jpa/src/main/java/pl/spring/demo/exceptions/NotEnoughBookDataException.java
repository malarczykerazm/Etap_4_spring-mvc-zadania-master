package pl.spring.demo.exceptions;

public class NotEnoughBookDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotEnoughBookDataException(String message) {
		super(message);
	}
}