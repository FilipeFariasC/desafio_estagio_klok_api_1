package tech.klok.challenge.exception;

public class ProductNotFoundException extends Exception {
	private static final long serialVersionUID = 800448799293459476L;

	public ProductNotFoundException(Long id) {
		super(String.format("O produto com o identificador %d não foi encontrado.", id));
	}
}
