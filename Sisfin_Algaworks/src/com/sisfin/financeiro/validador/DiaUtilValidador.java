package com.sisfin.financeiro.validador;

import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.sisfin.validadorData")
public class DiaUtilValidador implements Validator{

	public void validate(FacesContext contexto, UIComponent componente, Object valor)
			throws ValidatorException {
		Calendar data = Calendar.getInstance();
		data.setTime((Date) valor);
		
		int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
		
		if(diaDaSemana == Calendar.SUNDAY || diaDaSemana == Calendar.SATURDAY){
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data informada inválida", "Insira uma nova data válida");
			throw new ValidatorException(msg);
		}
		
		
	}

}
