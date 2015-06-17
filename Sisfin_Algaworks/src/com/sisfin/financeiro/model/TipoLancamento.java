package com.sisfin.financeiro.model;

public enum TipoLancamento {
	RECEITA("receita"),
	DESPESA("despesa");
	
	
	private String descricao;

	private TipoLancamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
