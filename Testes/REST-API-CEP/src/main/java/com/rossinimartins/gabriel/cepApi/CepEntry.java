package com.rossinimartins.gabriel.cepApi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class CepEntry {
	
	@Id
	private int cep;
	
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	
	public int getCep() {
		return cep;
	}
	
	public void setCep(int cep) {
		this.cep = cep;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "CepEntry [cep=" + cep + ", endereco=" + endereco + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", estado=" + estado + "]";
	}

}
