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
@Table(name = "livro")
@NamedQueries({ @NamedQuery(name = "Livro.listar", query = "select livro from Livro livro"),
		@NamedQuery(name = "Livro.buscarCodigo", query = "select livro from Livro livro where livro.idLivro = :idLivro") })
public class Livro extends ProdutoBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_livro")
	private Long idLivro;

	@NotEmpty(message = "O campo 'AUTOR' é obrigatório")
	@Size(min = 3, max = 200, message = "Tamanho requerido (3 - 100)")
	@Column(name = "autor", length = 200, nullable = false)
	private String autor;

	@NotEmpty(message = "O campo 'EDITORA' é obrigatório")
	@Size(min = 3, max = 100, message = "Tamanho requerido (3 - 100)")
	@Column(name = "editora", length = 100, nullable = false)
	private String editora;

	public Long getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Long idProduto) {
		this.idLivro = idProduto;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	@Override
	public String toString() {
		return "Livro [idLivro=" + idLivro + ", autor=" + autor + ", editora=" + editora + ", getNome()=" + getNome()
				+ ", getQuantidade()=" + getQuantidade() + ", getValor()=" + getValor() + ", getCategoria()="
				+ getCategoria() + ", getFornecedor()=" + getFornecedor() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((idLivro == null) ? 0 : idLivro.hashCode());
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
		Livro other = (Livro) obj;
		if (idLivro == null) {
			if (other.idLivro != null)
				return false;
		} else if (!idLivro.equals(other.idLivro))
			return false;
		return true;
	}

}
