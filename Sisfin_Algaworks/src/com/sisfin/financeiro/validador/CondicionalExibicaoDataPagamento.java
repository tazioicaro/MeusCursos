package com.sisfin.financeiro.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.sisfin.financeiro.util.FacesUtil;
import com.sun.faces.util.MessageFactory;

/*Classe desevolvida para analisar se o botão do checkbox 
 * de pagamento efetuado foi selecionado para exibir o campo
 *  efetuar pagamento
 * 
 */

@FacesValidator("exibicaoPagamento")
public class CondicionalExibicaoDataPagamento implements Validator{

	@Override
	public void validate(FacesContext contexto, UIComponent componente, Object valor)
			throws ValidatorException {
		
		/*Boolean e não boolean, visto que trata-se da classe raper
		 * O compotende é o imputText da tag que recebe o validador 
		 * Necessário um cast , pois o retonro no getAttribute é um objeto
		 */
			
		Boolean checado = (Boolean)componente.getAttributes().get("checado");
		
		if(checado != null && checado && valor == null )	 {
			//Pegando o label do campo
			Object label = MessageFactory.getLabel(contexto, componente);
			
			String descricaoErro =  FacesUtil.gerMessagemI18n("fill_the_field") + " " + label +".";
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			
			throw new ValidatorException(message);			

		}
		
	}

}
