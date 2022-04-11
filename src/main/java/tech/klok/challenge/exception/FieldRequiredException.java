package tech.klok.challenge.exception;

import tech.klok.challenge.model.Field;

public class FieldRequiredException extends Exception{
	public FieldRequiredException() {
		
	}
	public FieldRequiredException(String msg) {
		super(msg);
	}
	public FieldRequiredException(Field field) {
		super(String.format("Esse campo %s é obrigatório!", field.getName()));
	}
}
