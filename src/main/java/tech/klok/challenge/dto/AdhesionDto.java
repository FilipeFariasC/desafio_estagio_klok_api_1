package tech.klok.challenge.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AdhesionDto {
	
	private Long id;
	
	private Long productId;
	
	private Date aquisitionDate;

	private Integer chargingDay;
	
	private Long amount;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Date getAquisitionDate() {
		return aquisitionDate;
	}

	public void setAquisitionDate(Date aquisitionDate) {
		this.aquisitionDate = aquisitionDate;
	}

	public Integer getChargingDay() {
		return chargingDay;
	}

	public void setChargingDay(Integer chargingDay) {
		this.chargingDay = chargingDay;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
