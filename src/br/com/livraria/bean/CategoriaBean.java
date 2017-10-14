package br.com.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.dao.CategoriaDAO;
import br.com.livraria.domain.Categoria;
import br.com.livraria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class CategoriaBean {
	private Categoria categoriaCadastro;
	private List<Categoria> listaCategoria;
	private List<Categoria> listaCategoriaFiltrada;
	private String acao;
	private Long codigo;

	public Categoria getCategoriaCadastro() {
		return categoriaCadastro;
	}

	public void setCategoriaCadastro(Categoria categoriaCadastro) {
		this.categoriaCadastro = categoriaCadastro;
	}

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public List<Categoria> getListaCategoriaFiltrada() {
		return listaCategoriaFiltrada;
	}

	public void setListaCategoriaFiltrada(List<Categoria> listaCategoriaFiltrada) {
		this.listaCategoriaFiltrada = listaCategoriaFiltrada;
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
		categoriaCadastro = new Categoria();
	}

	public void salvar() {
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.salvar(categoriaCadastro);
			categoriaCadastro = new Categoria();
			FacesUtil.addMsgInfo("Categoria salva com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar! " + e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			listaCategoria = categoriaDAO.listar();
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro na pesquisa! " + e.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				categoriaCadastro = categoriaDAO.buscarCodigo(codigo);
			} else {
				categoriaCadastro = new Categoria();
			}
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao obter os dados! " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.excluir(categoriaCadastro);
			FacesUtil.addMsgInfo("Categoria excluida com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir! " + e.getMessage());
		}
	}

	public void editar() {
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.editar(categoriaCadastro);
			FacesUtil.addMsgInfo("Categoria editada com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar! " + e.getMessage());
		}
	}
}
