package tech.klok.challenge.exception;

public class FieldNotExistException extends Exception {
	public FieldNotExistException(Long id) {
		super(String.format("O campo com id %d não existe", id));
	}
}
