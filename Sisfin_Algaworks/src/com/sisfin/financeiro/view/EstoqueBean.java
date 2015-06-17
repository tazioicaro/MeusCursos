package com.sisfin.financeiro.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.sisfin.financeiro.model.ItemEstoque;

@ManagedBean
@ViewScoped
public class EstoqueBean  implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private ItemEstoque itemEstoque =  new ItemEstoque();
	private List<ItemEstoque> listaEstoque = new ArrayList<ItemEstoque>();
	
	
	public void incluir (){
		
		
		listaEstoque.add(this.itemEstoque);
		this.itemEstoque = new ItemEstoque();
		
		
	}
	
	public Collection<ItemEstoque> itensEstoque(){			
		
		return this.listaEstoque;		
		
	}
	
	public void limpar(){
		
		this.listaEstoque.clear();
	}
	
	
	//Get's e Set's	

	public ItemEstoque getItemEstoque() {
		return itemEstoque;
	}

	public void setItemEstoque(ItemEstoque itemEstoque) {
		this.itemEstoque = itemEstoque;
	}

	public List<ItemEstoque> getListaEstoque() {
		return listaEstoque;
	}

	public void setListaEstoque(List<ItemEstoque> listaEstoque) {
		this.listaEstoque = listaEstoque;
	}
	
	
	

}
