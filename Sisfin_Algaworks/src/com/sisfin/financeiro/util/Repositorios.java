package com.sisfin.financeiro.util;

import infra.LancamentosHibernate;
import infra.PessoasHibernate;

import java.io.Serializable;

import org.hibernate.Session;

import com.sisfin.financeiro.repository.Lancamentos;
import com.sisfin.financeiro.repository.Pessoas;

/*
 * Classe responsável por estanciar os objetos e a session 
 * do Hibernate. Fazendo com que as classes view (ManagerBean)
 * fiquem sem as regras de negócios e/ou codificações mais 
 * internsa 
 */
public class Repositorios implements Serializable{
	private static final long serialVersionUID = 1L;
	

	private Session getSession(){
		//Ver a a classe FacesUtil para explicação
		return (Session) FacesUtil.getResquetAttribute("session");
	}
	
	
	
	public Pessoas getPessoas(){
		
		return new PessoasHibernate(getSession());
	}
	
	public Lancamentos getLancamentos(){
		
		return new LancamentosHibernate(getSession());
	}
	
	

}
