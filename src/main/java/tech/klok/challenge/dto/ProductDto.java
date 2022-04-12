package tech.klok.challenge.dto;

import java.util.HashSet;
import java.util.Set;

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
