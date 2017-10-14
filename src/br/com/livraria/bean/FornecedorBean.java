package br.com.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.dao.FornecedorDAO;
import br.com.livraria.domain.Fornecedor;
import br.com.livraria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FornecedorBean {
	private Fornecedor fornecedorCadastro;
	private List<Fornecedor> listaFornecedor;
	private List<Fornecedor> listaFornecedorFiltrada;
	private String acao;
	private Long codigo;

	public Fornecedor getFornecedorCadastro() {
		return fornecedorCadastro;
	}

	public void setFornecedorCadastro(Fornecedor fornecedorCadastro) {
		this.fornecedorCadastro = fornecedorCadastro;
	}

	public List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}

	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public List<Fornecedor> getListaFornecedorFiltrada() {
		return listaFornecedorFiltrada;
	}

	public void setListaFornecedorFiltrada(List<Fornecedor> listaFornecedorFiltrada) {
		this.listaFornecedorFiltrada = listaFornecedorFiltrada;
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
		fornecedorCadastro = new Fornecedor();
	}

	public void salvar() {
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.salvar(fornecedorCadastro);
			fornecedorCadastro = new Fornecedor();
			FacesUtil.addMsgInfo("Fornecedor salvo com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar! " + e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			listaFornecedor = fornecedorDAO.listar();
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro na pesquisa! " + e.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				FornecedorDAO fornecedorDAO = new FornecedorDAO();
				fornecedorCadastro = fornecedorDAO.buscarCodigo(codigo);
			} else {
				fornecedorCadastro = new Fornecedor();
			}
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao obter os dados! " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.excluir(fornecedorCadastro);
			FacesUtil.addMsgInfo("Fornecedor excluido com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir! " + e.getMessage());
		}
	}

	public void editar() {
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.editar(fornecedorCadastro);
			FacesUtil.addMsgInfo("Fornecedor editado com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar! " + e.getMessage());
		}
	}
}
