package com.sisfin.financeiro.repository;

import java.util.List;

import com.sisfin.financeiro.model.Pessoa;

public interface Pessoas {
	
	public List<Pessoa> todas();
	public Pessoa porCodigo (Integer codigo);

}
