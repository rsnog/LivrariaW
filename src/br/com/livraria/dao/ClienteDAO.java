package br.com.livraria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.livraria.domain.Cliente;
import br.com.livraria.util.HibernateUtil;

public class ClienteDAO {
	public void salvar(Cliente cliente) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(cliente);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			System.out.println(cliente);
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Cliente> clientes = null;
		try {
			Query consulta = sessao.getNamedQuery("Cliente.listar");
			clientes = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return clientes;
	}

	public Cliente buscarCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Cliente cliente = null;
		try {
			Query consulta = sessao.getNamedQuery("Cliente.buscarCodigo");
			consulta.setLong("nCliente", codigo);
			cliente = (Cliente) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return cliente;
	}

	public void excluir(Cliente cliente) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(cliente);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}

	public void editar(Cliente cliente) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(cliente);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}
}
