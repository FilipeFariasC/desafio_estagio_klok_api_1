package tech.klok.challenge.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 6682407327488904100L;
	
	public UserNotFoundException(Long id) {
		super(String.format("O usuário com o identificador %d não foi encontrado", id));
	}
	public UserNotFoundException(String username) {
		super(String.format("O usuário com o username %s não foi encontrado", username));
	}

}
