package com.sisfin.financeiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * O Cloneable serve para fazer com que o Objeto seja clonado para que seja alterado, primeiro, sem alterar no banco de dados
 * e aconteca a regra de negócio que não permite dois objetos no banco iguais. Assim possibilita a comparação antes da alteração 
 * no DB 
 */

@Entity
@Table(name="lancamento")
public class Lancamento  implements Serializable , Cloneable{

	private static final long serialVersionUID = 1L;

	private Integer codigo;	
	private TipoLancamento tipo;
	private Pessoa pessoa;
	private String descricao;
	private BigDecimal valor;
	private Date dataVencimento;
	private boolean pago;
	private Date dataPagamento;
	
	

	
	//Gt e St
	
   @Id
   @GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	//Convertendo de enumerador para String para o banco
	@Enumerated (EnumType.STRING)
	public TipoLancamento getTipo() {
		return tipo;
	}
	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	
	//Muitos lancamento podem ter uma pessoa	
	@ManyToOne
	@JoinColumn(name="codigo_pessoa")
	public Pessoa getPessoa() {
		return pessoa;
	}	

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Column(name="data_vencimento")
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	@Column(name="data_pagamento")
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
	//Necessário gerar para comparar objetos
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getTipo().getDescricao().toString();
	}
	
}
