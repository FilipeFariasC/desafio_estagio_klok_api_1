package tech.klok.challenge.exception;

public class AdhesionNotFoundException extends Exception {
	public AdhesionNotFoundException(Long id) {
		super(String.format("Adesão com o identificador %d não encontrada", id));
	}
}
