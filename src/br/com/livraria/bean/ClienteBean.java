package br.com.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.dao.ClienteDAO;
import br.com.livraria.domain.Cliente;
import br.com.livraria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ClienteBean {
	private Cliente clienteCadastro;
	private List<Cliente> listaCliente;
	private List<Cliente> listaClienteFiltrada;
	private String acao;
	private Long codigo;

	public Cliente getClienteCadastro() {
		return clienteCadastro;
	}

	public void setClienteCadastro(Cliente clienteCadastro) {
		this.clienteCadastro = clienteCadastro;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public List<Cliente> getListaClienteFiltrada() {
		return listaClienteFiltrada;
	}

	public void setListaClienteFiltrada(List<Cliente> listaClienteFiltrada) {
		this.listaClienteFiltrada = listaClienteFiltrada;
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
		clienteCadastro = new Cliente();
	}

	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.salvar(clienteCadastro);
			clienteCadastro = new Cliente();
			FacesUtil.addMsgInfo("Cliente salvo com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar! " + e.getMessage());
		}

	}

	public void carregarPesquisa() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			listaCliente = clienteDAO.listar();
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro na pesquisa! " + e.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				ClienteDAO clienteDAO = new ClienteDAO();
				clienteCadastro = clienteDAO.buscarCodigo(codigo);
			} else {
				clienteCadastro = new Cliente();
			}
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao obter os dados! " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(clienteCadastro);
			FacesUtil.addMsgInfo("Cliente excluido com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir! " + e.getMessage());
		}
	}

	public void editar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.editar(clienteCadastro);
			FacesUtil.addMsgInfo("Cliente editado com sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar! " + e.getMessage());
		}
	}
}
