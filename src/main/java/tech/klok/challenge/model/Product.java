package tech.klok.challenge.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="t_product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 9040174852423129173L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private Long id;
	
	@Column(name="name")
	@NotEmpty
	@NotBlank
	@NotNull
	private String name;
	
}
