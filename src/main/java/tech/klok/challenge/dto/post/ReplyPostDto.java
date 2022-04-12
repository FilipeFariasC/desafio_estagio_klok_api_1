package tech.klok.challenge.dto.post;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReplyPostDto {
	
	@NotNull
	private Long fieldId;
	
	@NotNull
	@NotEmpty
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
