package br.com.livraria.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.livraria.dao.FuncionarioDAO;
import br.com.livraria.domain.Funcionario;

public class FuncionarioDAOTest {
	@Test
	public void salvar() throws ParseException {
		FuncionarioDAO dao = new FuncionarioDAO();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date troca = df.parse("13/02/1971");
		Funcionario f1 = new Funcionario();
		f1.setNome("F1x");
		f1.setFuncao("Gerente");
		f1.setnRegistro((long) 12);
		f1.setSenha("123");
		f1.setDataNasc(troca);
		dao.salvar(f1);
		Funcionario f2 = new Funcionario();
		f2.setNome("Funcionário 2");
		f2.setFuncao("Gerente");
		f2.setnRegistro((long) 12);
		f2.setSenha("123");
		f2.setDataNasc(troca);
		dao.salvar(f2);
	}

	@Test
	public void listar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios = dao.listar();
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
		}
	}

	@Test
	public void buscarCodigo() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f1 = dao.buscarCodigo(4L);
		System.out.println(f1);
	}

	@Test
	public void excluir() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f1 = dao.buscarCodigo(4L);
		dao.excluir(f1);
	}

	@Test
	public void editar() throws ParseException {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f1 = dao.buscarCodigo(3l);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date troca = df.parse("13/02/1971");
		f1.setDataNasc(troca);
		f1.setNome("Funcionario Alterado");
		dao.editar(f1);
		System.out.println(f1.getDataNasc());
	}

	@Test
	public void autenticar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.autenticar("F1x", "123");
		System.out.println(funcionario);
	}
}
