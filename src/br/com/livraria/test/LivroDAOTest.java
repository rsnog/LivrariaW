package br.com.livraria.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

import br.com.livraria.dao.CategoriaDAO;
import br.com.livraria.dao.FornecedorDAO;
import br.com.livraria.dao.LivroDAO;
import br.com.livraria.domain.Categoria;
import br.com.livraria.domain.Fornecedor;
import br.com.livraria.domain.Livro;

public class LivroDAOTest {
	@Test
	public void salvar() throws ParseException {
		Livro livro = new Livro();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria categoria = categoriaDAO.buscarCodigo(3L);
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		Fornecedor fornecedor = fornecedorDAO.buscarCodigo(3L);
		livro.setAutor("Autor 1");
		livro.setCategoria(categoria);
		livro.setEditora("Editora 1");
		livro.setFornecedor(fornecedor);
		livro.setNome("Livro 1");
		livro.setQuantidade(2);
		livro.setValor(new BigDecimal(9.90));
		LivroDAO dao = new LivroDAO();
		dao.salvar(livro);
	}

	@Test
	public void listar() {
		LivroDAO dao = new LivroDAO();
		List<Livro> livros = dao.listar();
		for (Livro livro : livros) {
			System.out.println(livro);
		}
	}

	@Test
	public void buscarCodigo() {
		LivroDAO dao = new LivroDAO();
		Livro livro = dao.buscarCodigo(1L);
		System.out.println(livro);
	}

	@Test
	@Ignore
	public void excluir() {
		LivroDAO dao = new LivroDAO();
		Livro livro = dao.buscarCodigo(4L);
		dao.excluir(livro);
	}

	@Test
	public void editar() {
		LivroDAO dao = new LivroDAO();
		Livro livro = dao.buscarCodigo(3l);
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria categoria = categoriaDAO.buscarCodigo(2L);
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		Fornecedor fornecedor = fornecedorDAO.buscarCodigo(3L);
		livro.setAutor("Autor Alterado");
		livro.setCategoria(categoria);
		livro.setEditora("Editora Alterada");
		livro.setFornecedor(fornecedor);
		livro.setNome("Livro Alterado");
		livro.setQuantidade(5);
		livro.setValor(new BigDecimal(29.90));
		dao.editar(livro);
	}
}
