package br.com.livraria.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.livraria.dao.FuncionarioDAO;
import br.com.livraria.dao.VendaDAO;
import br.com.livraria.domain.Funcionario;
import br.com.livraria.domain.Venda;

public class VendaDAOTest {
	@Test
	public void salvar() throws ParseException {
		Venda venda = new Venda();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarCodigo(3L);
		venda.setFuncionario(funcionario);
		venda.setHorario(new Date());
		venda.setTotal(new BigDecimal(33.10));
		VendaDAO dao = new VendaDAO();
		dao.salvar(venda);
	}

	@Test
	public void listar() {
		VendaDAO dao = new VendaDAO();
		List<Venda> vendas = dao.listar();
		for (Venda venda : vendas) {
			System.out.println(venda);
		}
	}

	@Test
	public void buscarCodigo() {
		VendaDAO dao = new VendaDAO();
		Venda venda = dao.buscarCodigo(1L);
		System.out.println(venda);
	}

	@Test
	public void excluir() {
		VendaDAO dao = new VendaDAO();
		Venda venda = dao.buscarCodigo(4L);
		dao.excluir(venda);
	}

	@Test
	public void editar() {
		VendaDAO dao = new VendaDAO();
		Venda venda = dao.buscarCodigo(3l);
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarCodigo(1L);
		venda.setFuncionario(funcionario);
		venda.setHorario(new Date());
		venda.setTotal(new BigDecimal(55.10));
		dao.editar(venda);
	}
}
