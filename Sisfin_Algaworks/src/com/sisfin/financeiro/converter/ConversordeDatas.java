package com.sisfin.financeiro.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.sisfin.ConversodeDatas")
public class ConversordeDatas implements Converter{
	
	private static final DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
	
	private Date getDataHoje(){
		Calendar dataHoje = Calendar.getInstance();
		dataHoje.set(Calendar.HOUR_OF_DAY, 0);
		dataHoje.set(Calendar.MINUTE,0);
		dataHoje.set(Calendar.SECOND, 0);
		dataHoje.set(Calendar.MILLISECOND, 0);
		return dataHoje.getTime();
	}

	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
		
		Date dataConvertida = null;
		
		if(valor !=null && !valor.equals("")){
			
			if(valor.equalsIgnoreCase("hoje")){
				dataConvertida = getDataHoje();
			}else{
				try {
					dataConvertida = formatador.parse(valor);
				} catch (ParseException e) {
					
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data Incorreta", "informe uma data correta");
				 //Inserindo no contexto do erro uma possível excessão
					throw new ConverterException(msg);
				}
			}
			
		}
		return dataConvertida;
	}

	public String getAsString(FacesContext contexto, UIComponent componente, Object valor) {
		//Formantando do tipo Date para uma String
		return formatador.format((Date)valor);
	}

}
