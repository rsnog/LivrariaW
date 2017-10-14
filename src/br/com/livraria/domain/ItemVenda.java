package br.com.livraria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "itemvenda")
@NamedQueries({ @NamedQuery(name = "ItemVenda.listar", query = "select itemvenda from ItemVenda itemvenda"),
		@NamedQuery(name = "ItemVenda.buscarCodigo", query = "select itemvenda from ItemVenda itemvenda where itemvenda.idVenda = :idVenda") })
public class ItemVenda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_item_venda")
	private Long idVenda;

	@NotNull(message = "O campo 'QUANTIDADE' é obrigatório")
	@Min(value = 0, message = "Digite um valor maior ou igual a zero")
	@Max(value = 100, message = "Digite um valor menor ou igual a cem")
	@Column(name = "quantidade", nullable = true)
	private Integer quantidade;

	@NotNull(message = "O campo 'PREÇO' é obrigatório")
	@DecimalMin(value = "0.00", message = "Informe um valor maior ou igua a zero")
	@DecimalMax(value = "99999.99", message = "Informe um valor menor que 10000,00")
	@Column(name = "total_parcial", precision = 7, scale = 2, nullable = false)
	private BigDecimal preco;

	@NotNull(message = "O campo 'TOTAL' é obrigatório")
	@DecimalMin(value = "0.00", message = "Informe um valor maior ou igua a zero")
	@DecimalMax(value = "99999.99", message = "Informe um valor menor que 10000,00")
	@Column(name = "total", precision = 7, scale = 2, nullable = false)
	private BigDecimal total;

	@NotNull(message = "O campo 'VENDA' é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_venda", referencedColumnName = "id_venda", nullable = false)
	private Venda venda;

	@NotNull(message = "O campo 'PRODUTO' é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_produto", referencedColumnName = "id_produto", nullable = false)
	private Produto produto;

	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ItemVenda [idVenda=" + idVenda + ", quantidade=" + quantidade + ", preco=" + preco + ", total=" + total
				+ ", venda=" + venda + ", produto=" + produto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVenda == null) ? 0 : idVenda.hashCode());
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
		ItemVenda other = (ItemVenda) obj;
		if (idVenda == null) {
			if (other.idVenda != null)
				return false;
		} else if (!idVenda.equals(other.idVenda))
			return false;
		return true;
	}

}
