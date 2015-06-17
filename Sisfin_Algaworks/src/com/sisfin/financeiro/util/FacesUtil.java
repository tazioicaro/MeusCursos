package com.sisfin.financeiro.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class FacesUtil {
	
	/*
	 * Instanciando FacesContext e adicionando mensagem ao contexto utilizando o método FacesMessage
	 */
	
	public static void adicionarMensagem(Severity tipo, String msg, String msg2){
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, msg, msg2));
		
		
	}
	
	
	/*
	 * receben a chave como parametro chama o resourcebundle e pega a variável que foi setado no sistema_en ou pt
	 * e passar a mensagem ou chave (String) corretamente .
	 * 
	 * Chave que representa a frase no arquivo sistema_en ou pt
	 */
	
	
	public static String gerMessagemI18n(String chave){
		
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = context.getApplication().getResourceBundle(context, "msg").getString(chave);
		return msg;
	}
	
	
	
	
	
	
	
	/*
	 * Instanciando um facesContext
	 * Atribuindo o contexto externo ao atributo ExternalContext
	 * Atribuindo o método getResquest a variável request da classe servlet
	 * Por fim , pegando o atributo session dentro da requisição do contexto externo, que foi atribuído 
	 * no HibernateSessionFilter
	 */
	
	
	public static Object getResquetAttribute(String name){
		
	FacesContext facesContext = FacesContext.getCurrentInstance();
		
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();	
		
		return request.getAttribute(name);
	}

}
