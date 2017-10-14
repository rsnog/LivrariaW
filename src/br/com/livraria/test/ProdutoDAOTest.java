package br.com.livraria.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.livraria.dao.CategoriaDAO;
import br.com.livraria.dao.FornecedorDAO;
import br.com.livraria.dao.ProdutoDAO;
import br.com.livraria.domain.Categoria;
import br.com.livraria.domain.Fornecedor;
import br.com.livraria.domain.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		Produto produto = new Produto();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria categoria = categoriaDAO.buscarCodigo(3l);
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		Fornecedor fornecedor = fornecedorDAO.buscarCodigo(3l);
		produto.setCategoria(categoria);
		produto.setFornecedor(fornecedor);
		produto.setNome("Produto 1");
		produto.setQuantidade(3);
		produto.setValor(new BigDecimal(8.80));
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(produto);
	}

	@Test
	public void listar() {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.listar();
		for (Produto produto : produtos) {
			System.out.println(produto);
		}
	}

	@Test
	public void buscarCodigo() {
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.buscarCodigo(1L);
		System.out.println(produto);
	}

	@Test
	public void excluir() {
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.buscarCodigo(4L);
		dao.excluir(produto);
	}

	@Test
	public void editar() {
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.buscarCodigo(3l);
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria categoria = categoriaDAO.buscarCodigo(1l);
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		Fornecedor fornecedor = fornecedorDAO.buscarCodigo(3l);
		produto.setCategoria(categoria);
		produto.setFornecedor(fornecedor);
		produto.setNome("Produto Alterado");
		produto.setQuantidade(7);
		produto.setValor(new BigDecimal(22.30));
		dao.editar(produto);
	}
}
