package br.com.livraria.domain;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "venda")
@NamedQueries({ @NamedQuery(name = "Venda.listar", query = "select venda from Venda venda"),
		@NamedQuery(name = "Venda.buscarCodigo", query = "select venda from Venda venda where venda.idVenda = :idVenda") })
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_venda")
	private Long idVenda;

	@NotNull(message = "O campo 'CATEGORIA' é obrigatório")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "horario")
	private Date horario;

	@NotNull(message = "O campo 'TOTAL' é obrigatório")
	@DecimalMin(value = "0.00", message = "Informe um valor maior ou igua a zero")
	@DecimalMax(value = "99999.99", message = "Informe um valor menor que 10000,00")
	@Column(name = "total", precision = 7, scale = 2, nullable = false)
	private BigDecimal total;

	@NotNull(message = "O campo 'FUNCIONARIO' é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_funcionario", referencedColumnName = "id_funcionario", nullable = false)
	private Funcionario funcionario;

	@Transient
	private Integer quantidadeTotal;

	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	@Override
	public String toString() {
		return "Venda [idVenda=" + idVenda + ", horario=" + horario + ", total=" + total + ", funcionario="
				+ funcionario + "]";
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
		Venda other = (Venda) obj;
		if (idVenda == null) {
			if (other.idVenda != null)
				return false;
		} else if (!idVenda.equals(other.idVenda))
			return false;
		return true;
	}

}
