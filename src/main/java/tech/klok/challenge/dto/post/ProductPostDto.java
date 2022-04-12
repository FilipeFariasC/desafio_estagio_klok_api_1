package tech.klok.challenge.dto.post;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductPostDto {
	
	@NotEmpty
	@NotBlank
	@NotNull
	private String name;
	
	@NotNull
	private Set<@Valid FieldPostDto> fields = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<FieldPostDto> getFields() {
		return fields;
	}

	public void setFields(Set<FieldPostDto> fields) {
		this.fields = fields;
	}
	
}
