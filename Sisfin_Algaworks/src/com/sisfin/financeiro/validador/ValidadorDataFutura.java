package com.sisfin.financeiro.validador;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.sisfin.financeiro.util.FacesUtil;
import com.sun.faces.util.MessageFactory;

/*
 * 
 * Valida se a data inserida no campo Data de pagamento é igual ou menor o dia atual do preenchimento
 */


@FacesValidator("dataFutura")
public class ValidadorDataFutura implements Validator{
	

	@Override
	public void validate(FacesContext contexto, UIComponent componente, Object valor)
			throws ValidatorException {
		//Convertendo data que vem no Object valor para Date
		Date data = (Date)valor;
		
		//Se da data não for nula  e for posterior a data de hoje
		if(data != null && data.after(new Date())){
			
			//Pegando o label do campo ( classe no Mojarra)
			Object label = MessageFactory.getLabel(contexto, componente);
			
			//Atribuindo uma chave que conterá  uma mensagem internacionalizada com o label 
			String descricaoErro = label + " " + FacesUtil.gerMessagemI18n("cannot_be_a_future_date");
			
			//Estanciando um message de erro do FacesMessagem com Severity_error que sai em vermelho
			//e colocando as mensagens
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			
			
			//Adicionando a mensagem no conteine de mensagens de erro do Faces
			throw new ValidatorException(message);
			
			
		}
		
		
		
		
	}
	

}
