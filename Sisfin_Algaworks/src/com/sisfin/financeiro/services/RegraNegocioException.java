package com.sisfin.financeiro.services;

//Classe excecão personaçisada
public class RegraNegocioException extends Exception {
	private static final long serialVersionUID = 1L;

	public RegraNegocioException(String mensagem){
		super(mensagem);
	}

}
