package br.com.livraria.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import br.com.livraria.dao.ItemLocarDAO;
import br.com.livraria.dao.LivroDAO;
import br.com.livraria.dao.LocarDAO;
import br.com.livraria.domain.ItemLocar;
import br.com.livraria.domain.Livro;
import br.com.livraria.domain.Locar;

public class ItemLocarDAOTest {
	@Test
	public void salvar() throws ParseException {
		ItemLocar itemLocar = new ItemLocar();
		LivroDAO livroDAO = new LivroDAO();
		Livro livro = livroDAO.buscarCodigo(3L);
		LocarDAO locarDAO = new LocarDAO();
		Locar locar = locarDAO.buscarCodigo(3L);
		itemLocar.setLivro(livro);
		itemLocar.setLocar(locar);
		itemLocar.setQuantidade(3);
		itemLocar.setTotal(new BigDecimal(17.77));
		ItemLocarDAO dao = new ItemLocarDAO();
		dao.salvar(itemLocar);
	}

	@Test
	public void listar() {
		ItemLocarDAO dao = new ItemLocarDAO();
		List<ItemLocar> itemLocars = dao.listar();
		for (ItemLocar itemLocar : itemLocars) {
			System.out.println(itemLocar);
		}
	}

	@Test
	public void buscarCodigo() {
		ItemLocarDAO dao = new ItemLocarDAO();
		ItemLocar itemLocar = dao.buscarCodigo(1L);
		System.out.println(itemLocar);
	}

	@Test
	public void excluir() {
		ItemLocarDAO dao = new ItemLocarDAO();
		ItemLocar itemLocar = dao.buscarCodigo(4L);
		dao.excluir(itemLocar);
	}

	@Test
	public void editar() {
		ItemLocarDAO dao = new ItemLocarDAO();
		ItemLocar itemLocar = dao.buscarCodigo(3l);
		LivroDAO livroDAO = new LivroDAO();
		Livro livro = livroDAO.buscarCodigo(1L);
		LocarDAO locarDAO = new LocarDAO();
		Locar locar = locarDAO.buscarCodigo(3L);
		itemLocar.setLivro(livro);
		itemLocar.setLocar(locar);
		itemLocar.setQuantidade(9);
		itemLocar.setTotal(new BigDecimal(12.22));
		dao.editar(itemLocar);
	}
}
