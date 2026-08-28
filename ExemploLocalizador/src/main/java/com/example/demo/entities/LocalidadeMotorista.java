package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_localidade_motorista")
public class LocalidadeMotorista implements Serializable {

    private static final long serialVersionUID = -7829565942340792203L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificadorString;
    private String coordenada;
    private Integer valorDiferencial;
    private boolean ativo;

    public LocalidadeMotorista() {
    }

    public LocalidadeMotorista(Long id, String identificadorString, String coordenada, Integer valorDiferencial, boolean ativo) {
        this.id = id;
        this.identificadorString = identificadorString;
        this.coordenada = coordenada;
        this.valorDiferencial = valorDiferencial;
        this.ativo = ativo;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocalidadeMotorista other = (LocalidadeMotorista) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
