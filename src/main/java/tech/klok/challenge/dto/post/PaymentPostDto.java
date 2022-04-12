package tech.klok.challenge.dto.post;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentPostDto {
	
	@NotNull
	private Long idCharge;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@NotNull
	private LocalDate payday;
	
	public Long getIdCharge() {
		return idCharge;
	}
	public void setIdCharge(Long id) {
		this.idCharge = id;
	}
	public LocalDate getPayday() {
		return payday;
	}
	public void setPayday(LocalDate payday) {
		this.payday = payday;
	}
	
}
