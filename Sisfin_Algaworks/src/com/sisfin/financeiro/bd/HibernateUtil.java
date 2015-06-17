package com.sisfin.financeiro.bd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@SuppressWarnings("deprecation")
public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	
	
	static{
		
		try {
			Configuration configuration = new Configuration();
			//Ler o arquivo xml do hibernate.cfg
			configuration.configure();
			
			//Busca as congiurações do mapeamento e passa para o ServiceRegistryBuikd
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
			
		}
		
		
	}
	
	public static Session getSession(){
		
		return sessionFactory.openSession();
	}

}
