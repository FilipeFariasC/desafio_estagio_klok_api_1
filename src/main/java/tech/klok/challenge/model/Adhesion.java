package tech.klok.challenge.model;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="t_adhesion")
public class Adhesion implements Serializable{

	private static final long serialVersionUID = 4541908573080844821L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="adhesion_id")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id_fk")
	private Product product;
	
	@Column(name="aquisition_date", columnDefinition = "DATE")
	private LocalDate aquisitionDate;
	
	@NotNull
	@Column(name="charging_day")
	private Integer chargingDay;
	
	@Column(name="amount")
	@Min(value=0)
	private Double amount;
	
	@Column(name="number_of_installments")
	private Integer numberOfInstallments;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="adhesion_id_fk")
	@NotEmpty
	private Set<@Valid Reply> replies = new HashSet<>();
	
	// Já que as cobranças não são armazenadas nesse service precisei colocar o atributo como @Transient, ou seja, não é pra persistir
	@Transient
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

	public Set<Charge> getCharges() {
		return charges;
	}

	public void setCharges(Set<Charge> charges) {
		this.charges = charges;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public void addReply(Reply reply) {
		if(!replies.contains(reply))
			replies.add(reply);
	}
	
}
