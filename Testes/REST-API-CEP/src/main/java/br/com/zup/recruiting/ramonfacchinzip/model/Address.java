package br.com.zup.recruiting.ramonfacchinzip.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.recruiting.ramonfacchinzip.util.Constants;
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
public class Address implements Serializable {

	private static final long serialVersionUID = 7842519982943444031L;

	@Id
	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;
	
	@NotBlank
	@Pattern(regexp = Constants.ZIP_CODE_PATTERN)
	@Column(name = "zip", unique = true, nullable = false)
	@JsonProperty("cep")
	private String zip;
	
	@NotBlank
	@Column(name = "address", nullable = false)
	@JsonProperty("endereco")
	private String address;
	
	@Column(name = "district")
	@JsonProperty("bairro")
	private String district;
	
	@NotBlank
	@Column(name = "city", nullable = false)
	@JsonProperty("cidade")
	private String city;
	
	@NotBlank
	@Column(name = "state", nullable = false)
	@JsonProperty("estado")
	private String state;
	
	@Transient
	private String status;
	
	@PrePersist
	public void beforePersist() {
		setUuid(UUID.randomUUID().toString());
	}

}
