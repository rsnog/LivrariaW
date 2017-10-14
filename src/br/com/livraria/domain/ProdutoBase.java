package br.com.livraria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class ProdutoBase {

	@NotEmpty(message = "O campo 'NOME' é obrigatório")
	@Size(min = 3, max = 200, message = "Tamanho requerido (3 - 200)")
	@Column(name = "nome", length = 200, nullable = false)
	private String nome;

	@NotNull(message = "O campo 'QUANTIDADE' é obrigatório")
	@Min(value = 0, message = "Digite um valor maior ou igual a zero")
	@Max(value = 100, message = "Digite um valor menor ou igual a cem")
	@Column(name = "quantidade", nullable = true)
	private Integer quantidade;

	@NotNull(message = "O campo 'PREÇO' é obrigatório")
	@DecimalMin(value = "0.00", message = "Informe um valor maior ou igua a zero")
	@DecimalMax(value = "99999.99", message = "Informe um valor menor que 10000,00")
	@Digits(integer = 5, fraction = 2, message="Informe um valor válido")
	@Column(name = "valor", precision = 7, scale = 2, nullable = false)
	private BigDecimal valor;

	@NotNull(message = "O campo 'CATEGORIA' é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_categoria", referencedColumnName = "id_categoria", nullable = false)
	private Categoria categoria;

	@NotNull(message = "O campo 'FORNECEDOR' é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_fornecedor", referencedColumnName = "id_fornecedor", nullable = false)
	private Fornecedor fornecedor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		ProdutoBase other = (ProdutoBase) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

}
