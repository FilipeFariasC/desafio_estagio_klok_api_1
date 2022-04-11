package tech.klok.challenge.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import tech.klok.challenge.model.Reply;

public class AdhesionDto {
	
	private Long id;
	
	private Long productId;
	
	private LocalDate aquisitionDate;

	private Integer chargingDay;
	
	private Long amount;
	
	private Set<ReplyDto> replies = new HashSet<>();

	
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

	

	public LocalDate getAquisitionDate() {
		return aquisitionDate;
	}

	public void setAquisitionDate(LocalDate aquisitionDate) {
		this.aquisitionDate = aquisitionDate;
	}

	public Set<ReplyDto> getReplies() {
		return replies;
	}

	public void setReplies(Set<ReplyDto> replies) {
		this.replies = replies;
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
