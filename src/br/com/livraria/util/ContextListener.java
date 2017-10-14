package br.com.livraria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent e) {
		HibernateUtil.getSessionFactory().openSession();
	}

	public void contextDestroyed(ServletContextEvent e) {
		HibernateUtil.getSessionFactory().close();
		;
	}
}
