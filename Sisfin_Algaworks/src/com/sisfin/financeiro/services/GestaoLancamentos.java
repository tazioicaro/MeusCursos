package com.sisfin.financeiro.services;

import com.sisfin.financeiro.model.Lancamento;
import com.sisfin.financeiro.repository.Lancamentos;

//Classe(Camada) de negócio, não deve ter APIs como FacesContex ou Hibernate
public class GestaoLancamentos {
	private Lancamentos lancamentos;
	
	
	public GestaoLancamentos(Lancamentos lancamentos){
		this.lancamentos=lancamentos;
	}
	
	public void excluir(Lancamento lancamento) throws RegraNegocioException{
		//Se tá pago, não poderá ser excluído
		if(lancamento.isPago()){
			
			//Criando uma excessão para setar uma mensagem sem usar apis
			
			//Esta excessão é capturada pelo managerbean CadastroLancamentoBean no método Salvar no catch RegraNecogioException
			//Inserido a chave para representar a frase
			throw new RegraNegocioException("existing_entry");
			
		}
		
		this.lancamentos.remover(lancamento);
		
	}
	
	private boolean existeLancamentoSemelhante(Lancamento lancamento){
		Lancamento lancamentoSemelhantes = this.lancamentos.comDadosIguais(lancamento);
		
		return lancamentoSemelhantes !=null && !lancamentoSemelhantes.equals(lancamento);
			
		}
	
	
	public void salvar(Lancamento lancamento) throws RegraNegocioException{
	
		//Sessão inserida no contexto de excessões que será capturado pelo Managerbean no métodos
		//cadastrar ou salvar.
		
		if(existeLancamentoSemelhante (lancamento)){
			throw new RegraNegocioException("Já existe um lançamento igual a este");
		}
		
		
		this.lancamentos.guardar(lancamento);
	}
	
	

}
