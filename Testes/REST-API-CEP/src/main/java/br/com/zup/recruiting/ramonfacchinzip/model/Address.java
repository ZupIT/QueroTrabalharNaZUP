package br.com.zup.recruiting.ramonfacchinzip.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address {

	@Id
	@Column(name = "uuid", unique = true, nullable = false)
	public String uuid;
	
	@NotBlank
	@Digits(integer = 20, fraction = 0)
	@Column(name = "zip", unique = true, nullable = false)
	public String zip;
	
	@NotBlank
	@Column(name = "address", nullable = false)
	public String address;
	
	@Column(name = "district")
	public String district;
	
	@NotBlank
	@Column(name = "city", nullable = false)
	public String city;
	
	@NotBlank
	@Column(name = "state", nullable = false)
	public String state;
	
	@PrePersist
	public void beforePersist() {
		setUuid(UUID.randomUUID().toString());
	}

}
