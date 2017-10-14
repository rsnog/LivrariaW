package br.com.livraria.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import br.com.livraria.dao.ItemVendaDAO;
import br.com.livraria.dao.ProdutoDAO;
import br.com.livraria.dao.VendaDAO;
import br.com.livraria.domain.ItemVenda;
import br.com.livraria.domain.Produto;
import br.com.livraria.domain.Venda;

public class ItemVendaDAOTest {
	@Test
	public void salvar() throws ParseException {
		ItemVenda itemVenda = new ItemVenda();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarCodigo(1L);
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarCodigo(1L);
		venda = vendaDAO.buscarCodigo(1L);
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(6);
		itemVenda.setPreco(new BigDecimal(9.90));
		itemVenda.setTotal(new BigDecimal(99.90));
		itemVenda.setVenda(venda);
		ItemVendaDAO dao = new ItemVendaDAO();
		dao.salvar(itemVenda);
	}

	@Test
	public void listar() {
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		List<ItemVenda> itemVendas = itemVendaDAO.listar();
		for (ItemVenda itemVenda : itemVendas) {
			System.out.println(itemVenda);
		}
	}

	@Test
	public void buscarCodigo() {
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscarCodigo(1L);
		System.out.println(itemVenda);
	}

	@Test
	public void excluir() {
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscarCodigo(3L);
		itemVendaDAO.excluir(itemVenda);
	}

	@Test
	public void editar() {
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscarCodigo(4l);
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarCodigo(2L);
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarCodigo(2L);
		venda = vendaDAO.buscarCodigo(1L);
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(1);
		itemVenda.setPreco(new BigDecimal(6.90));
		itemVenda.setTotal(new BigDecimal(1.90));
		itemVenda.setVenda(venda);
		itemVendaDAO.editar(itemVenda);
	}
}
