package tech.klok.challenge.dto;

import java.util.Date;

import tech.klok.challenge.model.categories.MaritalStatus;
import tech.klok.challenge.model.categories.Sex;

public class UserDto {
	
	private Long id;
	
	private String username;
	
	private String cpf;
	
	private Date birthDate;

	private Sex sex;
	
	private MaritalStatus maritalStatus;

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
