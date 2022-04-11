package tech.klok.challenge.dto;

import java.time.LocalDate;

import tech.klok.challenge.model.categories.ChargeStatus;

public class ChargeDto {
	
	private Long id;
	
	private Double amount;
	
	private LocalDate chargingDate;
	
	private ChargeStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getChargingDate() {
		return chargingDate;
	}

	public void setChargingDate(LocalDate chargingDate) {
		this.chargingDate = chargingDate;
	}

	public ChargeStatus getStatus() {
		return status;
	}

	public void setStatus(ChargeStatus status) {
		this.status = status;
	}
	
	
}
