package br.com.livraria.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.livraria.dao.ClienteDAO;
import br.com.livraria.domain.Cliente;

public class ClienteDAOTest {
	@Test
	public void salvar() throws ParseException {
		Cliente cli1 = new Cliente();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date troca = df.parse("13/02/1971");
		cli1.setNome("Cliente 1");
		cli1.setDataNasc(troca);
		ClienteDAO dao = new ClienteDAO();
		dao.salvar(cli1);
		Cliente cli2 = new Cliente();
		cli2.setNome("Cliente 2");
		cli2.setDataNasc(troca);
		dao.salvar(cli2);
	}

	@Test
	public void listar() {
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> clientes = dao.listar();
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	}

	@Test
	public void buscarCodigo() {
		ClienteDAO dao = new ClienteDAO();
		Cliente cli1 = dao.buscarCodigo(1L);
		System.out.println(cli1);
	}

	@Test
	public void excluir() {
		ClienteDAO dao = new ClienteDAO();
		Cliente cli1 = dao.buscarCodigo(4L);
		dao.excluir(cli1);
	}

	@Test
	public void editar() throws ParseException {
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.buscarCodigo(3l);
		cliente.setNome("Cliente Alterado");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date troca = df.parse("13/02/1971");
		cliente.setDataNasc(troca);
		dao.editar(cliente);
	}
}
