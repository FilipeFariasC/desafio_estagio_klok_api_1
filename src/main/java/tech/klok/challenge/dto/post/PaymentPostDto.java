package tech.klok.challenge.dto.post;

import java.time.LocalDate;

public class PaymentPostDto {
	private Long id;
	private LocalDate payday;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getPayday() {
		return payday;
	}
	public void setPayday(LocalDate payday) {
		this.payday = payday;
	}
	
}
