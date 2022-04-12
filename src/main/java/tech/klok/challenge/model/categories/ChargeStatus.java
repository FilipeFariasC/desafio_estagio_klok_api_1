package tech.klok.challenge.model.categories;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ChargeStatus {
	PENDING("Pending", 0L),
	PAID("Paid", 1L);
	
	private String status;
	private Long id;
	
	ChargeStatus(String status, Long id) {
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
	public static ChargeStatus fromName(String name) {
		return switch(name) {
		case "Pending" -> ChargeStatus.PENDING;
		case "Paid" -> ChargeStatus.PAID;
		default ->throw new IllegalArgumentException("Nome: [" + name + "] não é suportado.");
		};
	}
}

@Converter(autoApply = true)
class ChargeStatusConverter implements AttributeConverter<ChargeStatus, String> {
	public String convertToDatabaseColumn(ChargeStatus attribute) {
		return attribute.getStatus();
	}
	public ChargeStatus convertToEntityAttribute(String dbData) {
		return ChargeStatus.fromName(dbData);
	}
}
