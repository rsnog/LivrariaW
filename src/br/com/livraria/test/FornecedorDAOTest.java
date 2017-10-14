package br.com.livraria.test;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import br.com.livraria.dao.FornecedorDAO;
import br.com.livraria.domain.Fornecedor;

public class FornecedorDAOTest {
	@Test
	public void salvar() throws ParseException {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Fornecedor 1");
		FornecedorDAO dao = new FornecedorDAO();
		dao.salvar(fornecedor);
		Fornecedor fornecedor2 = new Fornecedor();
		fornecedor2.setNome("Fornecedor 2");
		dao.salvar(fornecedor2);
	}

	@Test
	public void listar() {
		FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> fornecedores = dao.listar();
		for (Fornecedor fornecedor : fornecedores) {
			System.out.println(fornecedor);
		}
	}

	@Test
	public void buscarCodigo() {
		FornecedorDAO dao = new FornecedorDAO();
		Fornecedor fornecedor = dao.buscarCodigo(1L);
		System.out.println(fornecedor);
	}

	@Test
	public void excluir() {
		FornecedorDAO dao = new FornecedorDAO();
		Fornecedor fornecedor = dao.buscarCodigo(4L);
		dao.excluir(fornecedor);
	}

	@Test
	public void editar() throws ParseException {
		FornecedorDAO dao = new FornecedorDAO();
		Fornecedor fornecedor = dao.buscarCodigo(3l);
		fornecedor.setNome("Fornecedor Alterado");
		dao.editar(fornecedor);
	}
}
