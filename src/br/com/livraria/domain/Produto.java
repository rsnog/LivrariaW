package br.com.livraria.domain;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@ManagedBean
@Entity
@Table(name = "produto")
@NamedQueries({ @NamedQuery(name = "Produto.listar", query = "select produto from Produto produto"),
	@NamedQuery(name = "Produto.buscarCodigo", query = "select produto from Produto produto where produto.idProduto = :idProduto") })

public class Produto extends ProdutoBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_produto")
	private Long idProduto;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", getNome()=" + getNome() + ", getQuantidade()=" + getQuantidade()
				+ ", getValor()=" + getValor() + ", getCategoria()=" + getCategoria() + ", getFornecedor()="
				+ getFornecedor() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
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
		Produto other = (Produto) obj;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		return true;
	}

}
