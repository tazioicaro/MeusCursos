package com.sisfin.financeiro.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.sisfin.financeiro.util.FacesUtil;



@ManagedBean
public class SegurancaBean {
	
	private String usuario;
	private String senha;
	
	
	public String logar(){		
		
		try {
			
			//usando o métod getRequest
			this.getRequest().login(this.usuario, this.senha);
			
			//Se der tudo certo redireciona para a página home.
			return "home?faces-redirect=true";
			
		} catch (ServletException e) {
			//Acionanando mensagem de erro para ser exibida no contexto da aplicação
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, FacesUtil.gerMessagemI18n("username_password_does_not_match"), 
					FacesUtil.gerMessagemI18n("username_password_does_not_match"));
			return null;
		}
		
		
	}
	
	
	public String sair() throws ServletException{
		
		this.getRequest().logout();
		
		return "login?faces-redirect=true";
		
		
	}
	
	//Facilitar o reaproveitamento de código
	//Buscando request por um ManagerBean
	@SuppressWarnings("unused")
	private HttpServletRequest getRequest(){
		
		FacesContext context = FacesContext.getCurrentInstance();			
		return (HttpServletRequest) context.getExternalContext().getRequest();
		
		
		
	}
	
	
	
	
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	

}
