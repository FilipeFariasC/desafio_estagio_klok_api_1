package tech.klok.challenge.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;


@Entity
@Table(name="t_adhesion")
public class Adhesion implements Serializable{

	private static final long serialVersionUID = 4541908573080844821L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="adhesion_id")
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="aquisition_date")
	private Date aquisitionDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="charging_date")
	private Date chargingDate;
	
	@Column(name="amount")
	@Min(value=0)
	private Long amount;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="adhesion_id_fk")
	private Set<Charge> charges = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getAquisitionDate() {
		return aquisitionDate;
	}

	public void setAquisitionDate(Date aquisitionDate) {
		this.aquisitionDate = aquisitionDate;
	}

	public Date getChargingDate() {
		return chargingDate;
	}

	public void setChargingDate(Date chargingDate) {
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
