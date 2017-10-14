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
@Table(name = "fornecedor")
@NamedQueries({ @NamedQuery(name = "Fornecedor.listar", query = "select fornecedor from Fornecedor fornecedor"),
		@NamedQuery(name = "Fornecedor.buscarCodigo", query = "select fornecedor from Fornecedor fornecedor where fornecedor.idFornecedor = :idFornecedor") })
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_fornecedor")
	private Long idFornecedor;

	@NotEmpty(message = "O campo 'NOME' é obrigatório")
	@Size(min=3, max=100, message="Tamanho requerido (3 - 100)")
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Fornecedor [idFornecedor=" + idFornecedor + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFornecedor == null) ? 0 : idFornecedor.hashCode());
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
		Fornecedor other = (Fornecedor) obj;
		if (idFornecedor == null) {
			if (other.idFornecedor != null)
				return false;
		} else if (!idFornecedor.equals(other.idFornecedor))
			return false;
		return true;
	}

}
