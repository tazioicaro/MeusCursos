package infra;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sisfin.financeiro.model.Lancamento;
import com.sisfin.financeiro.repository.Lancamentos;

public class LancamentosHibernate implements Lancamentos {

	private Session session;
		
	
	public LancamentosHibernate(Session session) {
		super();
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> todos() {
		
		return session.createCriteria(Lancamento.class).addOrder(Order.asc("dataVencimento")).list();
	}

	@Override
	public Lancamento porCodigo(Integer codigo) {
		
		return (Lancamento) session.get(Lancamento.class, codigo);
	}

	@Override
	public Lancamento guardar(Lancamento lancamento) {
		
		/* Se o objeto já existir ele atualiza, se não ele cria. */			
		return (Lancamento) session.merge(lancamento);
	}

	@Override
	public void remover(Lancamento lancmento) {
		this.session.delete(lancmento);
		
	}

	@Override
	public Lancamento comDadosIguais(Lancamento lancamento) {
		
		/* Strings referen-se ao bean
		 * Adicionando uma restrição no qual o "tipo" seja igual o valor do lancamento.getTipo() passado como parâmetro do metodo
		 * que , além disso, o campo pessoa deverá ser igual ao lancamento.getPessoa() e assim por diante.
		 * O .uniqueResult() informa que haverá apenas um único resultado.
		 */
		
		return (Lancamento) this.session.createCriteria(Lancamento.class).add(Restrictions.eq("tipo", lancamento.getTipo()))
				.add(Restrictions.eq("pessoa", lancamento.getPessoa())).add(Restrictions.like("descricao", lancamento.getDescricao()))
				.add(Restrictions.eq("valor", lancamento.getValor())).add(Restrictions.eq("dataVencimento", lancamento.getDataVencimento())).uniqueResult();
	}

}
