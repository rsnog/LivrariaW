package br.com.livraria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.livraria.domain.Venda;
import br.com.livraria.util.HibernateUtil;

public class VendaDAO {
	public Long salvar(Venda venda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		try {
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(venda);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
		return codigo;
	}

	@SuppressWarnings("unchecked")
	public List<Venda> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Venda> venda = null;
		try {
			Query consulta = sessao.getNamedQuery("Venda.listar");
			venda = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return venda;
	}

	public Venda buscarCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Venda venda = null;
		try {
			Query consulta = sessao.getNamedQuery("Venda.buscarCodigo");
			consulta.setLong("idVenda", codigo);
			venda = (Venda) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return venda;
	}

	public void excluir(Venda venda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(venda);
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

	public void editar(Venda venda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(venda);
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
