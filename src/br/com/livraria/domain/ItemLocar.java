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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "itemlocar")
@NamedQueries({ @NamedQuery(name = "ItemLocar.listar", query = "select itemLocar from ItemLocar itemLocar"),
		@NamedQuery(name = "ItemLocar.buscarCodigo", query = "select itemLocar from ItemLocar itemLocar where itemLocar.idItemL = :idItemL") })
public class ItemLocar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_item_locar")
	private Long idItemL;

	@NotNull(message = "O campo 'QUANTIDADE' é obrigatório")
	@Min(value = 0, message = "Digite um valor maior ou igual a zero")
	@Max(value = 5, message = "Digite um valor menor ou igual a cinco")
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;

	@NotNull(message = "O campo 'TOTAL' é obrigatório")
	@DecimalMin(value = "0.00", message = "Informe um valor maior ou igua a zero")
	@DecimalMax(value = "99999.99", message = "Informe um valor menor que 10000,00")
	@Column(name = "total_parcial", precision = 7, scale = 2, nullable = false)
	private BigDecimal total;

	@NotEmpty(message = "O campo 'LOCAÇÃO' é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_locar", referencedColumnName = "id_locar", nullable = false)
	private Locar locar;

	@NotEmpty(message = "O campo 'LIVRO' é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_livro", referencedColumnName = "id_livro", nullable = false)
	private Livro livro;

	public Long getIdLocar() {
		return idItemL;
	}

	public void setIdLocar(Long idVenda) {
		this.idItemL = idVenda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Locar getLocar() {
		return locar;
	}

	public void setLocar(Locar locar) {
		this.locar = locar;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	@Override
	public String toString() {
		return "ItemLocar [idItemL=" + idItemL + ", quantidade=" + quantidade + ", total=" + total + ", locar=" + locar
				+ ", livro=" + livro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idItemL == null) ? 0 : idItemL.hashCode());
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
		ItemLocar other = (ItemLocar) obj;
		if (idItemL == null) {
			if (other.idItemL != null)
				return false;
		} else if (!idItemL.equals(other.idItemL))
			return false;
		return true;
	}

}
