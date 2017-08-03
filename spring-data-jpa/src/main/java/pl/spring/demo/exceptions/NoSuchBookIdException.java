package pl.spring.demo.exceptions;

public class NoSuchBookIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchBookIdException(String message) {
		super(message);
	}
}