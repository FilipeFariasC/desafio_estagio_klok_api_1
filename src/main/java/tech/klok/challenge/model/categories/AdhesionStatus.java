package tech.klok.challenge.model.categories;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AdhesionStatus {
	ACTIVE("Active",0L),
	FINISHED("Finished", 1L);
	
	private Long id;
	private String status;
	
	private AdhesionStatus(String status,Long id) {
		this.status = status;
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	@JsonValue
	public String getStatus() {
		return status;
	}
	public static AdhesionStatus fromName(String name) {
		return switch(name) {
		case "Active" -> AdhesionStatus.ACTIVE;
		case "Finished" -> AdhesionStatus.FINISHED;
		default ->throw new IllegalArgumentException("Nome: [" + name + "] não é suportado.");
		};
	}
}

@Converter(autoApply = true)
class AdhesionStatusConverter implements AttributeConverter<AdhesionStatus, String> {
	public String convertToDatabaseColumn(AdhesionStatus attribute) {
		return attribute.getStatus();
	}
	public AdhesionStatus convertToEntityAttribute(String dbData) {
		return AdhesionStatus.fromName(dbData);
	}
}

