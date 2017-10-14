package br.com.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.dao.CategoriaDAO;
import br.com.livraria.dao.FornecedorDAO;
import br.com.livraria.dao.LivroDAO;
import br.com.livraria.domain.Categoria;
import br.com.livraria.domain.Fornecedor;
import br.com.livraria.domain.Livro;
import br.com.livraria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class LivroBean {
	private Livro livroCadastro;
	private List<Livro> listaLivro;
	private List<Livro> listaLivroFiltrada;
	private String acao;
	private Long codigo;
	private List<Categoria> listaCategoria;
	private List<Fornecedor> listaFornecedor;

	public Livro getLivroCadastro() {
		return livroCadastro;
	}

	public void setLivroCadastro(Livro livroCadastro) {
		this.livroCadastro = livroCadastro;
	}

	public List<Livro> getListaLivro() {
		return listaLivro;
	}

	public void setListaLivro(List<Livro> listaLivro) {
		this.listaLivro = listaLivro;
	}

	public List<Livro> getListaLivroFiltrada() {
		return listaLivroFiltrada;
	}

	public void setListaLivroFiltrada(List<Livro> listaLivroFiltrada) {
		this.listaLivroFiltrada = listaLivroFiltrada;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void novo() {
		livroCadastro = new Livro();
	}

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}

	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public void salvar() {
		try {
			LivroDAO livroDAO = new LivroDAO();
			livroDAO.salvar(livroCadastro);
			livroCadastro = new Livro();
			FacesUtil.addMsgInfo("Livro salvo com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar! " + e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			LivroDAO livroDAO = new LivroDAO();
			listaLivro = livroDAO.listar();
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro na pesquisa! " + e.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				LivroDAO livroDAO = new LivroDAO();
				livroCadastro = livroDAO.buscarCodigo(codigo);
			} else {
				livroCadastro = new Livro();
			}
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			listaCategoria = categoriaDAO.listar();
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			listaFornecedor = fornecedorDAO.listar();
		} catch (

		Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao obter os dados! " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			LivroDAO livroDAO = new LivroDAO();
			livroDAO.excluir(livroCadastro);
			FacesUtil.addMsgInfo("Livro excluido com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir! " + e.getMessage());
		}
	}

	public void editar() {
		try {
			LivroDAO livroDAO = new LivroDAO();
			livroDAO.editar(livroCadastro);
			FacesUtil.addMsgInfo("Livro editado com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar! " + e.getMessage());
		}
	}
}
