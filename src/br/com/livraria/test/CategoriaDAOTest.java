package br.com.livraria.test;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import br.com.livraria.dao.CategoriaDAO;
import br.com.livraria.domain.Categoria;

public class CategoriaDAOTest {
	@Test
	public void salvar() throws ParseException {
		Categoria cat1 = new Categoria();
		cat1.setNome("Categoria 1");
		CategoriaDAO dao = new CategoriaDAO();
		dao.salvar(cat1);
		Categoria cat2 = new Categoria();
		cat2.setNome("Categoria 2");
		dao.salvar(cat2);
	}

	@Test
	public void listar() {
		CategoriaDAO dao = new CategoriaDAO();
		List<Categoria> categorias = dao.listar();
		for (Categoria categoria : categorias) {
			System.out.println(categoria);
		}
	}

	@Test
	public void buscarCodigo() {
		CategoriaDAO dao = new CategoriaDAO();
		Categoria cat1 = dao.buscarCodigo(1L);
		System.out.println(cat1);
	}

	@Test
	public void excluir() {
		CategoriaDAO dao = new CategoriaDAO();
		Categoria cat1 = dao.buscarCodigo(4L);
		dao.excluir(cat1);
	}
	
	@Test
	public void editar() {		
		CategoriaDAO dao = new CategoriaDAO();
		Categoria categoria = dao.buscarCodigo(3l);
		categoria.setNome("Categoria Alterada");
		dao.editar(categoria);
	}
}
