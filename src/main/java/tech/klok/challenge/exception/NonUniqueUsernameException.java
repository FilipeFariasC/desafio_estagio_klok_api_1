package tech.klok.challenge.exception;

public class NonUniqueUsernameException extends Exception {
	
	private static final long serialVersionUID = 4244901278941370208L;

	public NonUniqueUsernameException(String username) {
		super(String.format("O username %s não é único", username));
	}
	
}
