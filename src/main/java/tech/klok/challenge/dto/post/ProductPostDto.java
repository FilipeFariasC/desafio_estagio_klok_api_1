package tech.klok.challenge.dto.post;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import tech.klok.challenge.dto.FieldDto;

public class ProductPostDto {
	
	@NotEmpty
	@NotBlank
	@NotNull
	private String name;
	
	@NotNull
	private Set<FieldDto> fields = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<FieldDto> getFields() {
		return fields;
	}

	public void setFields(Set<FieldDto> fields) {
		this.fields = fields;
	}
	
	
	
}
