package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.entities.Passageiro;

public class PassageiroDTO implements Serializable {

	private static final long serialVersionUID = 1313123214L;
	private Long id;
	private String name;
	private String coordenada;
	private boolean ativo;

	public PassageiroDTO() {
	}

	public PassageiroDTO(Long id, String name, String coordenada, boolean ativo) {
		this.id = id;
		this.name = name;
		this.coordenada = coordenada;
		this.ativo = ativo;
	}

	public PassageiroDTO(Passageiro entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.coordenada = entity.getCoordenada();
		this.ativo = entity.isAtivo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(String coordenada) {
		this.coordenada = coordenada;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
