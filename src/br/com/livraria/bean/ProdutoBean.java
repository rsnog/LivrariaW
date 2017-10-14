package br.com.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.dao.CategoriaDAO;
import br.com.livraria.dao.FornecedorDAO;
import br.com.livraria.dao.ProdutoDAO;
import br.com.livraria.domain.Categoria;
import br.com.livraria.domain.Fornecedor;
import br.com.livraria.domain.Produto;
import br.com.livraria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {
	private Produto produtoCadastro;
	private List<Produto> listaProduto;
	private List<Produto> listaProdutoFiltrada;
	private String acao;
	private Long codigo;
	private List<Categoria> listaCategoria;
	private List<Fornecedor> listaFornecedor;

	public Produto getProdutoCadastro() {
		return produtoCadastro;
	}

	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public List<Produto> getListaProdutoFiltrada() {
		return listaProdutoFiltrada;
	}

	public void setListaProdutoFiltrada(List<Produto> listaProdutoFiltrada) {
		this.listaProdutoFiltrada = listaProdutoFiltrada;
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
		produtoCadastro = new Produto();
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
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produtoCadastro);
			produtoCadastro = new Produto();
			FacesUtil.addMsgInfo("Produto salvo com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar! " + e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProduto = produtoDAO.listar();
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro na pesquisa! " + e.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtoCadastro = produtoDAO.buscarCodigo(codigo);
			} else {
				produtoCadastro = new Produto();
			}
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			listaCategoria = categoriaDAO.listar();
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			listaFornecedor = fornecedorDAO.listar();
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao obter os dados! " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produtoCadastro);
			FacesUtil.addMsgInfo("Produto excluido com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir! " + e.getMessage());
		}
	}

	public void editar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produtoCadastro);
			FacesUtil.addMsgInfo("Produto editado com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar! " + e.getMessage());
		}
	}
}
