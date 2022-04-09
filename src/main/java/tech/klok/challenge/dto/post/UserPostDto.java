package tech.klok.challenge.dto.post;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import tech.klok.challenge.model.categories.MaritalStatus;
import tech.klok.challenge.model.categories.Sex;

public class UserPostDto {
	@NotEmpty
	@NotBlank
	@NotNull
	private String username;
	
	@NotEmpty
	@NotBlank
	@NotNull
	private String password;
	
	@NotEmpty
	@NotBlank
	@NotNull
	@CPF
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	
}
