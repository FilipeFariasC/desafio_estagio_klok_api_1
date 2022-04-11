package tech.klok.challenge.dto.post;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

public class AdhesionPostDto {
	@NotNull
	private Long productId;
	
	@NotNull
	private LocalDate aquisitionDate;
	@NotNull
	private Integer chargingDay;
	
	@NotNull
	private Double amount;
	
	@NotNull
	private Set<ReplyPostDto> replies = new HashSet<>();

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

	public Integer getChargingDay() {
		return chargingDay;
	}

	public void setChargingDay(Integer chargingDay) {
		this.chargingDay = chargingDay;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Set<ReplyPostDto> getReplies() {
		return replies;
	}

	public void setReplies(Set<ReplyPostDto> replies) {
		this.replies = replies;
	}

	
	
}
