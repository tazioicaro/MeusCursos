package com.sisfin.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sisfin.financeiro.model.Pessoa;
import com.sisfin.financeiro.repository.Pessoas;
import com.sisfin.financeiro.util.Repositorios;

//Usando o ForClasse para informar que o conversor 
//irá funcionar quando a classe Pessoa for chamamada pro qualquer método 

@FacesConverter(forClass=Pessoa.class)
public class PessoaConverter  implements Converter{
	
	private Repositorios repositorios = new Repositorios();
	
	//Converter de String para Objeto -> vem do página para o Managebean
	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
		Pessoa retorno = null;		
		
		
		if(valor != null){
			Pessoas pessoas = this.repositorios.getPessoas();		
			
			/*
			 * Utilizando a classe repositorios para instanciar pessoas , pois essa classe detém uma Session
			 * Utilizando a classe Pessoas que é um repositório de pessoas que detém os comandos "DAOs" que
			 * são codificados na classe PessoasHibernate
			 * 
			 */
			
			retorno = pessoas.porCodigo(Integer.valueOf(valor));						
			
			
		}
		
		return retorno;
	}

	
	//Converter de Objeto para String  -> vai do managebean para a página
	@Override
	public String getAsString(FacesContext contexto, UIComponent componente, Object valor) {
	
		
		//Usando o conversor do Objeto para pessoa e chamando o objeto pessoa com seu valor, buscando o codigo da pessoa e 
		//convertendo em string
		
		if(valor !=null){			

		return ((Pessoa) valor).getCodigo().toString();
		
		}
		
		return null;
	}

}
