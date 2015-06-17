package com.sisfin.financeiro.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.sisfin.financeiro.model.Lancamento;
import com.sisfin.financeiro.model.Pessoa;
import com.sisfin.financeiro.model.TipoLancamento;
import com.sisfin.financeiro.repository.Pessoas;
import com.sisfin.financeiro.services.GestaoLancamentos;
import com.sisfin.financeiro.services.RegraNegocioException;
import com.sisfin.financeiro.util.FacesUtil;
import com.sisfin.financeiro.util.Repositorios;

//Ver o método setLancamento, que 'implementa' clonable 

@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private Lancamento lancamento = new Lancamento();
	private Repositorios repositorios = new Repositorios();
	

	
	//Método será criando sempre que esse managerBean for criado

	/*
	 * Hpa criado um método no facesUtil para que as frases em português possam ter traduzidas para qualquer idioma 
	 * confirugado aqui no sistema. 
	 */
	
	@PostConstruct
	private void init() {				
		
		//repositorio de pessoas recebendo valores do repositorios
		Pessoas  pessoas = this.repositorios.getPessoas();
		
		//Atribuindo as pessoas da classe Pessoas a variável local pessoas
		this.pessoas = pessoas.todas();	
	
	}
	
	
	
public void salvar(){
	GestaoLancamentos gestaoLancamento = new GestaoLancamentos(this.repositorios.getLancamentos());
	
	try {
		gestaoLancamento.salvar(lancamento);
		//para zerar a fila	
		this.lancamento = new Lancamento();
		
		FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, FacesUtil.gerMessagemI18n("entry_saved"), FacesUtil.gerMessagemI18n("entry_saved"));
	} catch (RegraNegocioException e) {
		//Se houver erros
		FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, FacesUtil.gerMessagemI18n(e.getMessage()), FacesUtil.gerMessagemI18n(e.getMessage()));
	
	}
	
			
	
		
		/*
		 * Inserinbdo mensagem no contexto, sendo que o Null é porque não está associado a nenhum objeto da página
		*  seja ele botão ou qualquer coisa. depois instancia um FacesMassae e inseri o tipo de erro, depois 
		*  		*  a mensagem completa e a simplificada
		*/		
		//FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, msg,msg));
	}




	
	public TipoLancamento [] getTiposLancamentos(){
		
		//Retorna as opções do array de enuns
		return TipoLancamento.values();
	}
	
	
	public void LancamentoPagoModificado(ValueChangeEvent evt){
		/*
		 *  há um valueChangeListener que será acionado quando houver cliques no checkbox e este método será chamado
		 *  Este evento será executado antes da validação do ciclo de vida do Face, antes do submit do forme
		 */
		
		
		//Pegando a informação setado no check box e atribuindo ao setPago
		this.lancamento.setPago((Boolean)evt.getNewValue());
		this.lancamento.setDataPagamento(null);
		
		/*
		 * Processo de fazer o Face pular as outras fazes e ir diretamente para rendered, afim de não submeter 
		 * o formulário sem ter todos os campos preenchidos e ao mesmo tempo possobilitar renderizar o campo
		 * data de pagamento
		 */
		
		FacesContext.getCurrentInstance().renderResponse(); //Pula a outras fazes de validações e etc.
		
		
	}
	
	
	
	//está editando quando lancamento codigo não for nulo, se não for nulo é prq está editando.
	//Se o código for nulo , é um lancamento novo, sem codigo ainda
	
	public boolean isEditando(){
		
		return this.lancamento.getCodigo() !=null;
	}
	
	
	//Gt St

	public Lancamento getLancamento() {
		return lancamento;
	}

	
	//Instanciando um novo lancamento se ele for nulo, ou seja, se não for uma edição
	public void setLancamento(Lancamento lancamento) throws CloneNotSupportedException {
		this.lancamento = lancamento;
		
		if(this.lancamento == null){
			
			this.lancamento= new Lancamento();
			
		}else{
			
			/* O Objeto clonado será remetido ao processo comum, passa pelas classes de serviços (Gestão lancamento), é 
			 * constatado ou não que há uma entidade semelhante no banco e, se houver, joga-se uma excessão e cancela a operação ANTES do 
			 * Hibernate commitar o objeto no banco. 			 
			 */
		
			
			this.lancamento = (Lancamento )lancamento.clone();
		}
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	

}
