package tech.klok.challenge.model.categories;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum MaritalStatus {
	SINGLE("Single", 0L),
	MARRIED("Married", 1L),
	DIVORCED("Divorced", 2L),
	WIDOW("Widow", 3L),
	WIDOWER("Widower", 4L);
	
	private String status;
	private Long id;
	
	private MaritalStatus(String status, Long id) {
		this.status = status;
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	
	public Long getId() {
		return id;
	}
	
	public static MaritalStatus fromName(String name) {
		return switch (name) {
        case "Single" -> MaritalStatus.SINGLE;
        case "Married" -> MaritalStatus.MARRIED;
        case "Divorced" -> MaritalStatus.DIVORCED;
        case "Widow" -> MaritalStatus.WIDOW;
        case "Widower" -> MaritalStatus.WIDOWER;
        default -> throw new IllegalArgumentException("Nome: [" + name
                    + "] não é suportado.");
        };
	}
}

@Converter(autoApply = true)
class MaritalStatusConverter implements AttributeConverter<MaritalStatus, String> {

	@Override
	public String convertToDatabaseColumn(MaritalStatus attribute) {
		
		return attribute.getStatus();
	}

	@Override
	public MaritalStatus convertToEntityAttribute(String dbData) {
		return MaritalStatus.fromName(dbData);
	}
 
}
