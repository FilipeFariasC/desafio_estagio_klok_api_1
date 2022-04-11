package tech.klok.challenge.dto.post;

import javax.validation.constraints.NotNull;

import tech.klok.challenge.model.Field;

public class ReplyPostDto {
	
	@NotNull
	private Long fieldId;
	
	@NotNull
	private String value;


	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
