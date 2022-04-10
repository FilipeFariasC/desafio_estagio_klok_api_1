package tech.klok.challenge.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import tech.klok.challenge.model.Field;

public class ProductDto {
	private Long id;

	private String name;
	
	private Set<FieldDto> fields = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
