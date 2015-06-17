package com.sisfin.financeiro.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sisfin.financeiro.bd.HibernateUtil;

/*
 * Utilizando o parametro faces Servlet, que existe dentro do web.xml, para informar que esses servlet ira iniciar 
 * junto com o filter do faces que recebe todas as requisições
 */
@WebFilter(servletNames={"Faces Servlet"})
public class HibernateSessionFilter implements Filter {



	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 Session session = HibernateUtil.getSession();
		 
		 Transaction trx = null;
		
		 
		try {
			
			trx = session.beginTransaction();
			//para capturar sessao no scope request
			request.setAttribute("session", session);
			
			//Executar o proximo passo que é o Faces Servlet
			chain.doFilter(request, response);
			
			trx.commit();
			
			
			
			
		} catch (Exception e) {
			if (trx != null){
				trx.rollback();
			}
		}finally{
			session.close();
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
