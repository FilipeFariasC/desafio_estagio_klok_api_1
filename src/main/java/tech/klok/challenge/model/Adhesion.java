package tech.klok.challenge.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;


@Entity
@Table(name="adhesion")
public class Adhesion implements Serializable{

	private static final long serialVersionUID = 4541908573080844821L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="aquisition_date")
	private LocalDate aquisitionDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="charging_date")
	private LocalDate chargingDate;
	
	@Column(name="amount")
	@Min(value=0)
	private Long amount;
	
	private Set<Charge> charges = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getAquisitionDate() {
		return aquisitionDate;
	}

	public void setAquisitionDate(LocalDate aquisitionDate) {
		this.aquisitionDate = aquisitionDate;
	}

	public LocalDate getChargingDate() {
		return chargingDate;
	}

	public void setChargingDate(LocalDate chargingDate) {
		this.chargingDate = chargingDate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Set<Charge> getCharges() {
		return charges;
	}

	public void setCharges(Set<Charge> charges) {
		this.charges = charges;
	}
	
}
