package br.com.livraria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.livraria.domain.ItemVenda;
import br.com.livraria.util.HibernateUtil;

public class ItemVendaDAO {
	public void salvar(ItemVenda itemVenda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(itemVenda);
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
	public List<ItemVenda> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ItemVenda> itemVenda = null;
		try {
			Query consulta = sessao.getNamedQuery("ItemVenda.listar");
			itemVenda = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return itemVenda;
	}

	public ItemVenda buscarCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		ItemVenda itemVenda = null;
		try {
			Query consulta = sessao.getNamedQuery("ItemVenda.buscarCodigo");
			consulta.setLong("idVenda", codigo);
			itemVenda = (ItemVenda) consulta.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return itemVenda;
	}
	
	public void excluir(ItemVenda itemVenda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(itemVenda);
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
	
	public void editar(ItemVenda itemVenda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(itemVenda);
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
