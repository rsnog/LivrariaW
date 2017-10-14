package br.com.livraria.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.livraria.dao.FuncionarioDAO;
import br.com.livraria.dao.LocarDAO;
import br.com.livraria.domain.Funcionario;
import br.com.livraria.domain.Locar;

public class LocarDAOTest {
	@Test
	public void salvar() throws ParseException {
		Locar locar = new Locar();
		LocarDAO dao = new LocarDAO();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarCodigo(3L);
		locar.setFuncionario(funcionario);
		locar.setHorario(new Date());
		locar.setTotal(new BigDecimal(13.20));
		dao.salvar(locar);
	}

	@Test
	public void listar() {
		LocarDAO dao = new LocarDAO();
		List<Locar> locars = dao.listar();
		for (Locar locar : locars) {
			System.out.println(locar);
		}
	}

	@Test
	public void buscarCodigo() {
		LocarDAO dao = new LocarDAO();
		Locar locar = dao.buscarCodigo(1L);
		System.out.println(locar);
	}

	@Test
	public void excluir() {
		LocarDAO dao = new LocarDAO();
		Locar locar = dao.buscarCodigo(4L);
		dao.excluir(locar);
	}

	@Test
	public void editar() {
		LocarDAO dao = new LocarDAO();
		Locar locar = dao.buscarCodigo(3l);
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarCodigo(1L);
		locar.setFuncionario(funcionario);
		locar.setHorario(new Date());
		locar.setTotal(new BigDecimal(9.90));
		dao.editar(locar);
	}
}
