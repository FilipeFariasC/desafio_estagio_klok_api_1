package tech.klok.challenge.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import tech.klok.challenge.model.categories.ChargeStatus;

public class ChargeDto {
	
	private Long id;
	
	private Double amount;
	
	private Date chargingDate;
	
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

	public Date getChargingDate() {
		return chargingDate;
	}

	public void setChargingDate(Date chargingDate) {
		this.chargingDate = chargingDate;
	}

	public ChargeStatus getStatus() {
		return status;
	}

	public void setStatus(ChargeStatus status) {
		this.status = status;
	}
	
	
}
