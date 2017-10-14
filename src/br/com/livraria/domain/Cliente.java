package br.com.livraria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
@NamedQueries({ @NamedQuery(name = "Cliente.listar", query = "select cliente from Cliente cliente"),
		@NamedQuery(name = "Cliente.buscarCodigo", query = "select cliente from Cliente cliente where cliente.nCliente = :nCliente") })
public class Cliente extends PessoaBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nCliente")
	private Long nCliente;

	public Long getnCliente() {
		return nCliente;
	}

	public void setnCliente(Long nCliente) {
		this.nCliente = nCliente;
	}

	@Override
	public String toString() {
		return "Cliente [nCliente=" + nCliente + ", getNome()=" + getNome() + ", getDataNasc()=" + getDataNasc() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nCliente == null) ? 0 : nCliente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (nCliente == null) {
			if (other.nCliente != null)
				return false;
		} else if (!nCliente.equals(other.nCliente))
			return false;
		return true;
	}

}
