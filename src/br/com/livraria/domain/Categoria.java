package br.com.livraria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categoria")
@NamedQueries({ @NamedQuery(name = "Categoria.listar", query = "select categoria from Categoria categoria"),
		@NamedQuery(name = "Categoria.buscarCodigo", query = "select categoria from Categoria categoria where categoria.idCategoria = :idCategoria") })
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_Categoria")
	private Long idCategoria;

	@NotEmpty(message = "O campo 'NOME' é obrigatório")
	@Size(min = 3, max = 100, message = "Tamanho requerido (3 - 100)")
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idForncedor) {
		this.idCategoria = idForncedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
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
		Categoria other = (Categoria) obj;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		return true;
	}

}
