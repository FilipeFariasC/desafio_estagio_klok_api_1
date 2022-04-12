package tech.klok.challenge.dto.post;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AdhesionPostDto {
	
	@NotNull
	private Long productId;
	
	@NotNull
	private Integer chargingDay;
	
	@NotNull
	private Double amount;
	
	@NotNull
	private Set<@Valid ReplyPostDto> replies = new HashSet<>();
	
	@NotNull
	private Integer numberOfInstallments;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}

	
	
	
}
