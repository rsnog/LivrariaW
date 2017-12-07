package br.com.livraria.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import br.com.livraria.dao.FuncionarioDAO;
import br.com.livraria.dao.ItemVendaDAO;
import br.com.livraria.dao.ProdutoDAO;
import br.com.livraria.dao.VendaDAO;
import br.com.livraria.domain.Categoria;
import br.com.livraria.domain.Fornecedor;
import br.com.livraria.domain.Funcionario;
import br.com.livraria.domain.ItemVenda;
import br.com.livraria.domain.Produto;
import br.com.livraria.domain.Venda;
import br.com.livraria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {
	private List<Venda> listaVenda;
	private List<Venda> listaVendaFiltrada;
	private List<Produto> listaProduto;
	private List<Produto> listaProdutoFiltrada;
	private List<Categoria> listaCategoria;
	private List<Fornecedor> listaFornecedor;
	private List<ItemVenda> listaItem;
	private Venda vendaCadastro;
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	public List<Venda> getListaVenda() {
		return listaVenda;
	}

	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}

	public List<Venda> getListaVendaFiltrada() {
		return listaVendaFiltrada;
	}

	public void setListaVendaFiltrada(List<Venda> listaVendaFiltrada) {
		this.listaVendaFiltrada = listaVendaFiltrada;
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

	public List<ItemVenda> getListaItem() {
		if (listaItem == null) {
			listaItem = new ArrayList<>();
		}
		return listaItem;
	}

	public void setListaItem(List<ItemVenda> listaItem) {

		this.listaItem = listaItem;
	}

	public Venda getVendaCadastro() {
		if (vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setHorario(new Date());
			vendaCadastro.setQuantidadeTotal(0);
			vendaCadastro.setTotal(new BigDecimal("0.00"));
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public void carregarProduto() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProduto = produtoDAO.listar();
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro na pesquisa! " + e.getMessage());
		}
	}

	public void adicionar(Produto produtoSelecionado) {
		int posicaoEncontrada = -1;
		for (int pos = 0; pos < listaItem.size() && posicaoEncontrada < 0; pos++) {
			ItemVenda itemTemp = listaItem.get(pos);
			if (itemTemp.getProduto().equals(produtoSelecionado)) {
				posicaoEncontrada = pos;
			}
		}
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setProduto(produtoSelecionado);
		if (posicaoEncontrada < 0) {
			itemVenda.setQuantidade(1);
			itemVenda.setPreco(produtoSelecionado.getValor());
			itemVenda.setTotal(produtoSelecionado.getValor());
			listaItem.add(itemVenda);
		} else {
			ItemVenda itemTemp = listaItem.get(posicaoEncontrada);
			itemVenda.setPreco(produtoSelecionado.getValor());
			itemVenda.setQuantidade(itemTemp.getQuantidade() + 1);
			itemVenda.setTotal(produtoSelecionado.getValor().multiply(new BigDecimal(itemVenda.getQuantidade())));
			listaItem.set(posicaoEncontrada, itemVenda);
		}
		vendaCadastro.setTotal(vendaCadastro.getTotal().add(produtoSelecionado.getValor()));
		vendaCadastro.setQuantidadeTotal(vendaCadastro.getQuantidadeTotal() + 1);
	}

	public void remover(ItemVenda item) {
		int posicaoEncontrada = -1;
		for (int pos = 0; pos < listaItem.size() && posicaoEncontrada < 0; pos++) {
			ItemVenda itemTemp = listaItem.get(pos);
			if (itemTemp.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = pos;
			}
		}
		if (posicaoEncontrada > -1) {
			listaItem.remove(posicaoEncontrada);
			vendaCadastro.setTotal(vendaCadastro.getTotal().subtract(item.getTotal()));
			vendaCadastro.setQuantidadeTotal(vendaCadastro.getQuantidadeTotal() - item.getQuantidade());
		}
	}

	public void carregarDadosVenda() {
		vendaCadastro.setHorario(new Date());
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarCodigo(autenticacaoBean.getFuncionarioLogado().getnRegistro());
		vendaCadastro.setFuncionario(funcionario);
	}
	
	public void carregarPesquisa() {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			listaVenda = vendaDAO.listar();
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro na pesquisa! " + e.getMessage());
		}
	}

	public void salvar() {
		try {
			if (!vendaCadastro.getTotal().equals(new BigDecimal("0.00"))) {
				VendaDAO vendaDAO = new VendaDAO();
				Long codigoVenda = vendaDAO.salvar(vendaCadastro);
				Venda vendaFK = vendaDAO.buscarCodigo(codigoVenda);
				for (ItemVenda itemVenda : listaItem) {
					itemVenda.setVenda(vendaFK);
					ItemVendaDAO itemDAO = new ItemVendaDAO();
					itemDAO.salvar(itemVenda);
				}
				vendaCadastro = new Venda();
				vendaCadastro.setTotal(new BigDecimal("0.00"));
				listaItem = new ArrayList<>();
				FacesUtil.addMsgInfo("Venda Salva! ");
			} else {
				FacesUtil.addMsgInfo("Nenhum item encontrado!");
			}
		} catch (Exception e) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar! " + e.getMessage());
		}
	}

	public void limparVenda() {
		vendaCadastro = new Venda();
		vendaCadastro.setTotal(new BigDecimal("0.00"));
		vendaCadastro.setQuantidadeTotal(0);
		listaItem = new ArrayList<>();
		FacesUtil.addMsgInfo("Venda Cancelada! ");
	}
}
