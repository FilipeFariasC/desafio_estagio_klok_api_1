package tech.klok.challenge.dto;

public class ReplyDto {

	private Long id;
	
	private FieldDto field;
	
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FieldDto getField() {
		return field;
	}

	public void setField(FieldDto field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
