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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "locar")
@NamedQueries({ @NamedQuery(name = "Locar.listar", query = "select locar from Locar locar"),
		@NamedQuery(name = "Locar.buscarCodigo", query = "select locar from Locar locar where locar.idLocar = :idLocar") })
public class Locar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_locar")
	private Long idLocar;

	@NotEmpty(message = "O campo 'HORÁRIO' é obrigatório")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "horario")
	private Date horario;

	@NotNull(message = "O campo 'TOTAL' é obrigatório")
	@DecimalMin(value = "0.00", message = "Informe um valor maior ou igua a zero")
	@DecimalMax(value = "99999.99", message = "Informe um valor menor que 10000,00")
	@Column(name = "total", precision = 7, scale = 2, nullable = false)
	private BigDecimal total;

	@NotEmpty(message = "O campo 'FUNCIONÁRIO' é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_funcionario", referencedColumnName = "id_funcionario", nullable = false)
	private Funcionario funcionario;

	public Long getIdLocar() {
		return idLocar;
	}

	public void setIdLocar(Long idLocar) {
		this.idLocar = idLocar;
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

	@Override
	public String toString() {
		return "Locar [idLocar=" + idLocar + ", horario=" + horario + ", total=" + total + ", funcionario="
				+ funcionario + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLocar == null) ? 0 : idLocar.hashCode());
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
		Locar other = (Locar) obj;
		if (idLocar == null) {
			if (other.idLocar != null)
				return false;
		} else if (!idLocar.equals(other.idLocar))
			return false;
		return true;
	}

}
