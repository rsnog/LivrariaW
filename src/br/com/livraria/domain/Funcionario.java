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
@Table(name = "funcionario")
@NamedQueries({ @NamedQuery(name = "Funcionario.listar", query = "select funcionario from Funcionario funcionario"),
		@NamedQuery(name = "Funcionario.buscarCodigo", query = "select funcionario from Funcionario funcionario where funcionario.nRegistro = :nRegistro"),
		@NamedQuery(name = "Funcionario.autenticar", query = "select funcionario from Funcionario funcionario where funcionario.nome = :nome and funcionario.senha = :senha") })
public class Funcionario extends PessoaBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_funcionario")
	private Long nRegistro;

	@NotEmpty(message = "O campo 'SENHA' é obrigatório")
	@Size(min = 3, max = 32, message = "Tamanho requerido (3 - 8)")
	@Column(name = "senha", length = 32, nullable = false)
	private String senha;

	@NotEmpty(message = "O campo 'FUNÇÃO' é obrigatório")
	@Column(name = "funcao", length = 50, nullable = false)
	private String funcao;

	public Long getnRegistro() {
		return nRegistro;
	}

	public void setnRegistro(Long nRegistro) {
		this.nRegistro = nRegistro;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return "Funcionario [nRegistro=" + nRegistro + ", senha=" + senha + ", funcao=" + funcao + ", getNome()="
				+ getNome() + ", getDataNasc()=" + getDataNasc() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nRegistro == null) ? 0 : nRegistro.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (nRegistro == null) {
			if (other.nRegistro != null)
				return false;
		} else if (!nRegistro.equals(other.nRegistro))
			return false;
		return true;
	}

}
