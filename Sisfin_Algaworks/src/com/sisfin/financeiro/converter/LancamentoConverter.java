package com.sisfin.financeiro.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.sisfin.financeiro.model.Lancamento;
import com.sisfin.financeiro.repository.Lancamentos;
import com.sisfin.financeiro.util.FacesUtil;
import com.sisfin.financeiro.util.Repositorios;

/*
 * Configurando para que esse conversor seja chamado diretamento quando for instanciado
 * uma classe do tipo lancamento
 */

@FacesConverter(forClass=Lancamento.class)
public class LancamentoConverter implements Converter{
	
	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
		
		Lancamento retorno = null;
		
		Lancamentos lancamentos = this.repositorios.getLancamentos();
		
		if(valor !=null && !valor.equals("")){			
			
			retorno = lancamentos.porCodigo(new Integer (valor));
			
			
			if(retorno == null){
				
				//Inserindo a mensagem ou chave do arquivo sistema_xx.properties para internacionalizar
				String descricaoErro = FacesUtil.gerMessagemI18n("entry_does_not_exist");
			     FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
				
				throw new ConverterException(msg);
			}
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent componente, Object valor) {
		
		if(valor != null){
			Integer codigo = ((Lancamento) valor).getCodigo();
			
			return codigo == null ? "": codigo.toString();
		}
		return null;
	}

}
