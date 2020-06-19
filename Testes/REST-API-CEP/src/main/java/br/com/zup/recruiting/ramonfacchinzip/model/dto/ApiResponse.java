package br.com.zup.recruiting.ramonfacchinzip.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class ApiResponse implements Serializable {
	
	private static final long serialVersionUID = 2376178144808476397L;

	@JsonProperty("status")
	private String status;
	
	@JsonProperty("mensagem")
	private String message;

}
