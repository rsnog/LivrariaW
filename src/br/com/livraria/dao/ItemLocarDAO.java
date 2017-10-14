package br.com.livraria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.livraria.domain.ItemLocar;
import br.com.livraria.util.HibernateUtil;

public class ItemLocarDAO {
	public void salvar(ItemLocar itemLocar) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(itemLocar);
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
	public List<ItemLocar> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ItemLocar> itemLocar = null;
		try {
			Query consulta = sessao.getNamedQuery("ItemLocar.listar");
			itemLocar = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return itemLocar;
	}

	public ItemLocar buscarCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		ItemLocar itemLocar = null;
		try {
			Query consulta = sessao.getNamedQuery("ItemLocar.buscarCodigo");
			consulta.setLong("idItemL", codigo);
			itemLocar = (ItemLocar) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return itemLocar;
	}
	
	public void excluir(ItemLocar itemLocar) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(itemLocar);
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
	
	public void editar(ItemLocar itemLocar) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(itemLocar);
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
