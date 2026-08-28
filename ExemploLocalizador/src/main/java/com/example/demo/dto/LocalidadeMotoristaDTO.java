package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.entities.LocalidadeMotorista;

public class LocalidadeMotoristaDTO implements Serializable {

	private static final long serialVersionUID = -6665832153300212918L;

	private Long id;
	private String identificadorString;
	private String coordenada;
	private Integer valorDiferencial;
	private boolean ativo;

	public LocalidadeMotoristaDTO() {
	}

	public LocalidadeMotoristaDTO(Long id, String identificadorString, String coordenada, Integer valorDiferencial, boolean ativo) {
		this.id = id;
		this.identificadorString = identificadorString;
		this.coordenada = coordenada;
		this.valorDiferencial = valorDiferencial;
		this.ativo = ativo;
	}

	public LocalidadeMotoristaDTO(LocalidadeMotorista entity) {
		this.id = entity.getId();
		this.identificadorString = entity.getIdentificadorString();
		this.coordenada = entity.getCoordenada();
		this.valorDiferencial = entity.getValorDiferencial();
		this.ativo = entity.isAtivo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificadorString() {
		return identificadorString;
	}

	public void setIdentificadorString(String identificadorString) {
		this.identificadorString = identificadorString;
	}

	public String getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(String coordenada) {
		this.coordenada = coordenada;
	}

	public Integer getValorDiferencial() {
		return valorDiferencial;
	}

	public void setValorDiferencial(Integer valorDiferencial) {
		this.valorDiferencial = valorDiferencial;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
