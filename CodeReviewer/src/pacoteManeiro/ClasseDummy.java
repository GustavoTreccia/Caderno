package pacoteManeiro;

import java.util.Objects;

public class ClasseDummy {
	
	private String Nome;
	private Integer Idade;
	private Boolean taVivo;
	
	public ClasseDummy() {}

	public ClasseDummy(String nome, Integer idade, Boolean taVivo) {
		Nome = nome;
		Idade = idade;
		this.taVivo = taVivo;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public Integer getIdade() {
		return Idade;
	}

	public void setIdade(Integer idade) {
		Idade = idade;
	}

	public Boolean getTaVivo() {
		return taVivo;
	}

	public void setTaVivo(Boolean taVivo) {
		this.taVivo = taVivo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Idade, Nome, taVivo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClasseDummy other = (ClasseDummy) obj;
		return Objects.equals(Idade, other.Idade) && Objects.equals(Nome, other.Nome)
				&& Objects.equals(taVivo, other.taVivo);
	}
	
	

}
