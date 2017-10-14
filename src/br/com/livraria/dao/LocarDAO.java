package br.com.livraria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.livraria.domain.Locar;
import br.com.livraria.util.HibernateUtil;

public class LocarDAO {
	public void salvar(Locar locar) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(locar);
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
	public List<Locar> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Locar> locar = null;
		try {
			Query consulta = sessao.getNamedQuery("Locar.listar");
			locar = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return locar;
	}

	public Locar buscarCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Locar locar = null;
		try {
			Query consulta = sessao.getNamedQuery("Locar.buscarCodigo");
			consulta.setLong("idLocar", codigo);
			locar = (Locar) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return locar;
	}
	
	public void excluir(Locar locar) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(locar);
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
	
	public void editar(Locar locar) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(locar);
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
