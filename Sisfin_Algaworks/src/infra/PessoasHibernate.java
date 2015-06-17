package infra;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.sisfin.financeiro.model.Pessoa;
import com.sisfin.financeiro.repository.Pessoas;

public class PessoasHibernate implements Pessoas {
	
	private Session session;
		

	public PessoasHibernate(Session session) {
		super();
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> todas() {
		
		//Retornando todas as pessoas por ordem crescente por nome
		return session.createCriteria(Pessoa.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public Pessoa porCodigo(Integer codigo) {
		
		return (Pessoa) session.get(Pessoa.class, codigo);
	}

}
