package com.sisfin.financeiro.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.sisfin.financeiro.model.Lancamento;
import com.sisfin.financeiro.repository.Lancamentos;
import com.sisfin.financeiro.services.GestaoLancamentos;
import com.sisfin.financeiro.services.RegraNegocioException;
import com.sisfin.financeiro.util.FacesUtil;
import com.sisfin.financeiro.util.Repositorios;

//Classe view. Poderá conter o JSF , mas não o Hibernate

@ManagedBean(name="consultaL")
@ViewScoped
public class ConsultaLancamentoBean  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private String nome;
	private String escolas;
	private double valor;
	
	private Lancamento lancamentoSelecionado;
	private Repositorios repositorios = new Repositorios();
	
	
	@PostConstruct
	public void inicializar() {
		Lancamentos lancamentos = this.repositorios.getLancamentos();		
	     	this.lancamentos = lancamentos.todos();
		
	
		

	}
	
	
	

	
	public void excluir(){
		
		GestaoLancamentos gestaoLancamentos = new GestaoLancamentos(this.repositorios.getLancamentos());
		
		
		try {
			gestaoLancamentos.excluir(this.lancamentoSelecionado);
			
			//Chamando o método para recarregar a lista de lançamentos com uma novo consulta do DB.
			this.inicializar();
			
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Excluído com sucesso", "Lancamento Excluído com sucesso");
			
			
		} catch (RegraNegocioException e) {
			
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
		}		
				
		
	}
	
	
//gets
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}


	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	
	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getEscola() {
		return escolas;
	}




	public void setEscola(String escola) {
		this.escolas = escola;
	}




	public double getValor() {
		return valor;
	}




	public void setValor(double valor) {
		this.valor = valor;
	}




	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}




	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	

}
