package br.com.livraria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.livraria.domain.Fornecedor;
import br.com.livraria.util.HibernateUtil;

public class FornecedorDAO {
	public void salvar(Fornecedor fornecedor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(fornecedor);
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

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Fornecedor> fornecedores = null;
		try {
			Query consulta = sessao.getNamedQuery("Fornecedor.listar");
			fornecedores = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return fornecedores;
	}

	public Fornecedor buscarCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Fornecedor fornecedor = null;
		try {
			Query consulta = sessao.getNamedQuery("Fornecedor.buscarCodigo");
			consulta.setLong("idFornecedor", codigo);
			fornecedor = (Fornecedor) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return fornecedor;
	}

	public void excluir(Fornecedor fornecedor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(fornecedor);
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

	public void editar(Fornecedor fornecedor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(fornecedor);
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
