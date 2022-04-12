package tech.klok.challenge.model.categories;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Sex {
	MALE("Male", 0L),
	FEMALE("Female", 1L);
	
	private String sex;
	private Long id;
	
	private Sex(String sex, Long id) {
		this.sex = sex;
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	@JsonValue
	public String getSex() {
		return sex;
	}
	public static Sex fromName(String name) {
		return switch (name) {
        case "Male" -> Sex.MALE;
        case "Female" -> Sex.FEMALE;
        default -> throw new IllegalArgumentException("Nome: [" + name
                    + "] não é suportado.");
        };
	}
}

@Converter(autoApply = true)
class SexConverter implements AttributeConverter<Sex, String> {

	@Override
	public String convertToDatabaseColumn(Sex attribute) {
		return attribute.getSex();
	}

	@Override
	public Sex convertToEntityAttribute(String dbData) {
		return Sex.fromName(dbData);
	}

 
}