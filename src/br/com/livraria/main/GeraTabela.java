package br.com.livraria.main;

import org.hibernate.Session;

import br.com.livraria.util.HibernateUtil;

public class GeraTabela {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.close();
		HibernateUtil.getSessionFactory().close();
	}
}
